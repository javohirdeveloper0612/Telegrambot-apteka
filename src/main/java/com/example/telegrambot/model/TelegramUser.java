package com.example.telegrambot.model;

import lombok.*;
import org.glassfish.grizzly.http.util.TimeStamp;
import org.telegram.telegrambots.meta.api.objects.Contact;

import javax.persistence.*;
import java.time.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUser {
    private String fullName;
    private String phone;
    private String companyName;
    private LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Tashkent"));
    private LocalTime localTime = LocalTime.now(Clock.tickSeconds(ZoneId.of("Asia/Tashkent")));
    private Contact contact;
}
