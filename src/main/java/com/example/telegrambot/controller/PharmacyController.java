package com.example.telegrambot.controller;

import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.contstant.Step;
import com.example.telegrambot.entity.Location;
import com.example.telegrambot.entity.Pharmacy;
import com.example.telegrambot.repository.DoctorRepository;
import com.example.telegrambot.repository.Locationrepository;
import com.example.telegrambot.repository.PharmacyRepository;
import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.util.SendMsg;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
public class PharmacyController {

    private final Locationrepository locationrepository;
    private final TelegramBot telegramBot;
    public final PharmacyRepository pharmacyRepository;
    private Location location = new Location();

    private Pharmacy pharmacy = new Pharmacy();


    private final DoctorRepository doctorRepository;


    public PharmacyController(@Lazy Locationrepository locationrepository, @Lazy TelegramBot telegramBot, @Lazy PharmacyRepository pharmacyRepository, @Lazy DoctorRepository doctorRepository) {
        this.locationrepository = locationrepository;
        this.telegramBot = telegramBot;
        this.pharmacyRepository = pharmacyRepository;
        this.doctorRepository = doctorRepository;

    }


    public void finishPharmacyData(Message message) {

        Double latitude = message.getLocation().getLatitude();
        Double longitude = message.getLocation().getLongitude();

        location.setLongitude(longitude);
        location.setLatitude(latitude);
        boolean exists = locationrepository.existsByLongitudeAndLatitude(longitude, latitude);

        if (exists) {

//            DeleteMessage deleteMessage = new DeleteMessage();
//            deleteMessage.setChatId(message.getChatId());
//            deleteMessage.setMessageId(message.getMessageId() - 1);
//            telegramBot.send(deleteMessage);
//
//            DeleteMessage deleteMessage1 = new DeleteMessage();
//            deleteMessage1.setChatId(message.getChatId());
//            deleteMessage1.setMessageId(message.getMessageId());
//            telegramBot.send(deleteMessage1);


            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "*Ushbu dorixona ba'zada mavjud*",
                    Keyboard.backMenu()));

            return;
        }
        Location savedLocation = locationrepository.save(location);
        pharmacy.setLocation(savedLocation);
        pharmacyRepository.save(pharmacy);

        pharmacy = new Pharmacy();
        location = new Location();

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);

        telegramBot.send(SendMsg.sendMsg(message.getChatId(), "Muvaffaqqiyatli saqlandi ✅✅✅",
                Keyboard.backMenu()));




    }

    public void getPharmacyAddress(Message message) {

        pharmacy.setOwnerPhoneNumber(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "Iltimos dorixona joylashgan hudud ni kiriting" +
                "\n*Masalan : Toshkent shahar olmazor tumani *"));


    }

    public void getPharmacyLocation(Message message) {
        pharmacy.setPharmacyArea(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "*Iltimos dorixona geolokatsiyasini jo'nating*",
                Keyboard.location()));


    }

    public void getPharmacyOwnerPhone(Message message) {

        pharmacy.setManagerName(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "Dorixona yoki Dorixona rahbarining telefon raqamini kiriting \n" +
                "*Masalan : +998901234567*"));


    }

    public void getPharmacyOwnerName(Message message) {

        pharmacy.setPharmacyName(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "Dorixona rahbarining ismi va familiyasini kiriting\n " +
                "*Masalan : Ali Aliyev*"));



    }

    public void getPharmacyName(Message message) {
//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "Iltimos dorixona nomini kiriting \n" +
                "*Masalan : OOO BILOL PHARM*"));




    }


}
