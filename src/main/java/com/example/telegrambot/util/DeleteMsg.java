package com.example.telegrambot.util;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

public class DeleteMsg {

    public static DeleteMessage deleteMsg(Long chatId, Integer messageID){
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageID);
        System.out.println(deleteMessage.getMessageId());

        return deleteMessage;
    }
}
