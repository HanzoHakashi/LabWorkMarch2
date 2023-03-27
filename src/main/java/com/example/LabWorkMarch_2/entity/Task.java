package com.example.LabWorkMarch_2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String header;
    private String description;
    private LocalDate toDate;
    private String usernameOfAuthor;//fk
    private String status;

 }
