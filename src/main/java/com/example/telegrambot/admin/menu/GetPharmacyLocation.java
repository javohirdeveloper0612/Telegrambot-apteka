package com.example.telegrambot.admin.menu;

import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.entity.Pharmacy;
import com.example.telegrambot.repository.PharmacyRepository;
import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.util.SendMsg;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@Component
public class GetPharmacyLocation {

    public final TelegramBot telegramBot;

    public final PharmacyRepository pharmacyRepository;

    public GetPharmacyLocation(@Lazy TelegramBot telegramBot, PharmacyRepository pharmacyRepository) {
        this.telegramBot = telegramBot;
        this.pharmacyRepository = pharmacyRepository;
    }


    public void findLocationById(Message message) {
//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);

        int count = 0;
        String Id = message.getText();
        for (int i = 0; i < Id.length(); i++) {
            if (!Character.isDigit(Id.charAt(i))) {
                count++;
            }
        }

        if (count > 0) {


            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "*ID da harf yoki belgi bolmasligi kerak*",
                    Keyboard.backMenu()));

            return;
        }

        Long id = Long.valueOf(Id);

        Optional<Pharmacy> optional = pharmacyRepository.findById(id);
        if (optional.isEmpty()) {

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "*Bunday ID raqamli Dorixona mavjud emas*",
                    Keyboard.backMenu()));


        } else {
            Pharmacy pharmacy = optional.get();

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "*ID*" + pharmacy.getId() + "\n" +
                    "*Dorixona nomi :*" + pharmacy.getPharmacyName() + "\n" +
                    "*Dorixona joylashgan hudud : *" + pharmacy.getPharmacyArea() + "\n" +
                    "*Dorixona telefon raqami :* " + pharmacy.getOwnerPhoneNumber()));


            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "*Dorixona joylashuvi*"));

            telegramBot.send(SendMsg.sendLC(message.getChatId(),
                    pharmacy.getLocation().getLatitude(),
                    pharmacy.getLocation().getLongitude(),
                    Keyboard.backMenu()));


        }


    }

    public void getLocation(Message message) {
//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "*Iltimos Dorixonaning ID raqamini kiriting*"));

    }

}
