package com.example.telegrambot.controller;

import com.example.telegrambot.button.InlineKeyboard;
import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.dto.WorkerDTO;
import com.example.telegrambot.entity.Location;
import com.example.telegrambot.entity.Pharmacy;
import com.example.telegrambot.entity.Worker;
import com.example.telegrambot.repository.WorkerRepository;
import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.util.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@Controller
public class UserController {

    private final TelegramBot telegramBot;
    private final WorkerRepository workerRepository;

    public UserController(@Lazy TelegramBot telegramBot, @Lazy WorkerRepository workerRepository) {
        this.telegramBot = telegramBot;
        this.workerRepository = workerRepository;
    }

    public Worker checkPassword(Message message) {
        boolean password = workerRepository.existsByPassword(message.getText());
        if (!password) {
            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Parol xato ! Itimos qayta dan urinib ko'ring !*"));
            return null;
        } else {
            startMessage(message);

            Optional<Worker> optional = workerRepository.findWorkerByPassword(message.getText());
            Worker worker = optional.get();
            return worker;
        }
    }


    private void startMessage(Message message) {
//            DeleteMessage deleteMessage = new DeleteMessage();
//            deleteMessage.setChatId(message.getChatId());
//            deleteMessage.setMessageId(message.getMessageId() -1);
//            telegramBot.send(deleteMessage);
//
//            DeleteMessage deleteMessage1 = new DeleteMessage();
//            deleteMessage1.setChatId(message.getChatId());
//            deleteMessage1.setMessageId(message.getMessageId());
//            telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "Assalomu alekum " + " *DR BLESS TRADE * korxonasiga tegishli botga xush kelibsiz !" +
                        " Iltimos asosiy saxifaga o'ting",
                InlineKeyboard.goToMenu("Asosiy sahifaga o'tish")));


    }


}
