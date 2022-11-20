package com.example.telegrambot.repository;

import com.example.telegrambot.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    boolean existsByPhone(String phone);

    boolean existsByPassword(String password);

    Optional<Worker> findWorkerByPassword(String password);



}
