package com.apica.assignment.UserManagement.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Journal {
    @NotNull
    private Long id;

    private String message;


    // Getters and Setters
}
