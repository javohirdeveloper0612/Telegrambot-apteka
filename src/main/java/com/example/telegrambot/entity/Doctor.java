package com.example.telegrambot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@Entity

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    private String fullName;

    private String area;

    private String speciality;

    private String hospitalName;

    private String status;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "worker_id")
    private Worker worker;


}
