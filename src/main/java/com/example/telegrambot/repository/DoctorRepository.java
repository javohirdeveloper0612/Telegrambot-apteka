package com.example.telegrambot.repository;

import com.example.telegrambot.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByFullNameEqualsIgnoreCase(String fullName);

    boolean existsByPhone(String phone);
}
