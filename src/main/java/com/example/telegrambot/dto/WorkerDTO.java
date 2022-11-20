package com.example.telegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.NonLeaked;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {

    private Long id;

    private String fullName;

    private String department;


    private String phone;

    private String position;

    private String area;


    private String password;
}
