package com.apica.assignment.UserManagement.Services;

import com.apica.assignment.UserManagement.Repository.UserRepo;
import com.apica.assignment.UserManagement.exception.UserNotFoundException;
import com.apica.assignment.UserManagement.kafka.UserEventProducer;
import com.apica.assignment.UserManagement.Model.Type;
import com.apica.assignment.UserManagement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserEventProducer userEventProducer;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<User> getAllUser() {
        return userRepo.getUsers();
    }



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = getAllUser();
        User user = null;

        if (users != null) {
            for (User u : users) {
                if (username.equals(u.getUserName())) {
                    user = u;
                    break;
                }
            }
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                Arrays.asList(user.getRole()).stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList())
        );
    }


    public String inputUsers(List<User> u) {
        List<User> users = getAllUser();
        users.addAll(u);

        // Concatenate all usernames
        String concatenatedUsernames = users.stream()
                .map(User::getUserName)
                .collect(Collectors.joining(", "));

        // Send user event with concatenated usernames
        userEventProducer.sendUserEvent("Users registered: " + concatenatedUsernames);

        return "Users added";
    }


    public String inputUser(User u) {
        List<User> users = getAllUser();
        users.add(u);
        return "User added";

    }

    public User getUser(Integer id) {
        List<User> users = getAllUser();
        for (User i : users) {
            if (id.equals(i.getUserId())) {
                userEventProducer.sendUserEvent("User fetched: " + i.getUserName());
                return i;
            }
        }
        throw new UserNotFoundException("User with ID " + id + " not found");
    }

    public String updateUserNumber(Integer iD, String num) {
        List<User> users = getAllUser();
        for (User i : users) {
            if (iD.equals(i.getUserId())) {
                i.setUserContactNo(num);
                userEventProducer.sendUserEvent("User Contact No Updated."+ i.getUserName()+i.getUserContactNo()  );
                return "User Contact No Updated.";
            }
        }
        throw new UserNotFoundException("User with ID " + iD + " not found");
    }

    public String updateUserEmailAddress(Integer iD, String updateUserEmail) {
        List<User> users = getAllUser();
        for (User i : users) {
            if (iD.equals(i.getUserId())) {
                i.setUserEmail(updateUserEmail);
                return "User's Address Updated.";
            }
        }
        throw new UserNotFoundException("User with ID " + iD + " not found");
    }

    public String updateRole(Integer iD, Type updaterole) {
        List<User> users = getAllUser();
        for (User i : users) {
            if (iD.equals(i.getUserId())) {
                i.setType(updaterole);
                return "User's role Updated.";
            }
        }
        throw new UserNotFoundException("User with ID " + iD + " not found");
    }

    public String deleteUser(Integer iD) {
        List<User> users = getAllUser();
        for (User i : users) {
            if (iD.equals(i.getUserId())) {
                users.remove(i);
                return "User removed.";
            }
        }
        throw new UserNotFoundException("User with ID " + iD + " not found");
    }


}
