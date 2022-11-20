package com.example.telegrambot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class SendMsg {
    public static SendMessage sendMsg(Long chatID, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);

        return sendMessage;
    }

    public static SendMessage sendMsg(Long chatID, String text, ReplyKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markup);


        return sendMessage;
    }

    public static SendMessage sendMsg(Long chatID, String text, InlineKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markup);


        return sendMessage;
    }

    public static SendMessage sendMsgParse(Long chatID, String text, InlineKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);
        sendMessage.setParseMode("Markdown");
        sendMessage.setReplyMarkup(markup);


        return sendMessage;
    }

    public static SendMessage sendMsgParse(Long chatID, String text, ReplyKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setParseMode("Markdown");

        return sendMessage;
    }

    public static SendMessage sendMsgParse(Long chatID, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);
        sendMessage.setParseMode("Markdown");

        return sendMessage;
    }

    public static SendLocation sendLC(Long chatId, Double latitude, Double longitude) {
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(chatId);
        sendLocation.setLatitude(latitude);
        sendLocation.setLongitude(longitude);

        return sendLocation;
    }

    public static SendLocation sendLC(Long chatId, Double latitude, Double longitude, ReplyKeyboardMarkup markup) {
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(chatId);
        sendLocation.setLatitude(latitude);
        sendLocation.setLongitude(longitude);
        sendLocation.setReplyMarkup(markup);

        return sendLocation;
    }


    public static SendDocument sendDc(Long chatID, InputFile inputFile, ReplyKeyboardMarkup murkup) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatID);
        sendDocument.setDocument(inputFile);
        sendDocument.setReplyMarkup(murkup);

        return sendDocument;

    }


}
