package com.example.telegrambot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    private String fullName;

    private String department;

    private String phone;

    private String position;

    private String area;

    private String password;

    @OneToMany(mappedBy = "worker",cascade = CascadeType.ALL, orphanRemoval = true )
    private  Set<Doctor> doctor;
}
