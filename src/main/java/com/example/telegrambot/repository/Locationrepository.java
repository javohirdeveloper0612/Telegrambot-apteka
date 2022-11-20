package com.example.telegrambot.repository;

import com.example.telegrambot.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Locationrepository extends JpaRepository<Location, Long> {
    boolean existsByLongitudeAndLatitude(Double longitude, Double latitude);
}
