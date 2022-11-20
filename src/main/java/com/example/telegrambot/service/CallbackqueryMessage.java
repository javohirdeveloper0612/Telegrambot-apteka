package com.example.telegrambot.service;

import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.contstant.Step;
import com.example.telegrambot.util.SendMsg;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CallbackqueryMessage {

    public final TelegramBot telegramBot;

    public CallbackqueryMessage(@Lazy TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }


    public void handle(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();

        if ("go to menu".equals(data)) {
            menu(message);
        }

    }


    public void menu(Message message) {

        DeleteMessage deleteMessage1 = new DeleteMessage();
        deleteMessage1.setChatId(message.getChatId());
        deleteMessage1.setMessageId(message.getMessageId());
        telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsg(message.getChatId(),
                "Siz asosiy sahifadasiz ! Iltimos o'zingizga tegishli bo'lgan ro'yhatni tanlang ! ",
                Keyboard.mainMenuButton()));
    }


    public void claim(Message message) {
//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Iltimos to'liq ism familyangizni jo'nating" +
                        "\n* Masalan : Ali aliyev *"));

    }


}

