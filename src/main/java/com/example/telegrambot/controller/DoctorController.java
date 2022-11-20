package com.example.telegrambot.controller;

import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.dto.WorkerDTO;
import com.example.telegrambot.entity.Doctor;
import com.example.telegrambot.entity.Worker;
import com.example.telegrambot.repository.DoctorRepository;
import com.example.telegrambot.repository.WorkerRepository;
import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.util.SendMsg;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DoctorController {

    private Doctor doctor = new Doctor();

    public final TelegramBot telegramBot;

    public final DoctorRepository doctorRepository;

    public final WorkerRepository workerRepository;

    WorkerDTO workerDTO = new WorkerDTO();



    public DoctorController(@Lazy TelegramBot telegramBot, @Lazy DoctorRepository doctorRepository, WorkerRepository workerRepository) {

        this.telegramBot = telegramBot;
        this.doctorRepository = doctorRepository;

        this.workerRepository = workerRepository;
    }


    public void setDoctorPosition(Message message) {

        doctor.setHospitalName(message.getText());
//
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
                "Iltimos shifokor  mutaxasisligi ni kiriting \n" +
                        "*Masalan : Terapevt*"));

    }

    public void setHospitalName(Message message) {
        doctor.setArea(message.getText());

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
                "Iltimos shifokor ishlaydigan shifoxona nomini kiriting \n " +
                        "*Masalan : 1-bolalar shifoxonasi*"));



    }

    public void setArea(Message message) {

        doctor.setFullName(message.getText());

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
                "Iltimos shifokor ishlaydigan hudud nomini kiriting \n" +
                        "*Masalan : Toshkent shahar Olmazor tumani *"));



    }


    public void setDoctorName(Message message) {

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Iltimos shifokorning  ismi va familiyasini kiriting \n" +
                        "*Masalan : Ali Aliyev*"));




    }


    public void getDoctorData(Message message) {

        List<Doctor> allByName = doctorRepository.findAllByFullNameEqualsIgnoreCase(message.getText());
        boolean check = false;

        for (Doctor doctor : allByName) {
            if (doctor != null) {

                telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                        "*Ismi va Familiyasi:* " + doctor.getFullName() +
                                "*\nTelefon raqami :* " + doctor.getPhone() +
                                "*\nKasalxona nomi : *" + doctor.getHospitalName() + "\n" +
                                "*Kasalxona addressi :*" + doctor.getArea() + "\n" +
                                "*Shifokor mutaxasisligi : *" + doctor.getSpeciality() + "\n" +
                                "*Holati :* " + doctor.getStatus(),Keyboard.backMenu()));
                check = true;
            }
        }

        if (!check) {
            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Bunday shifokor mavjud emas !*",
                    Keyboard.backMenu()));

        }

    }


    public void setDoctorStatus(Message message) {
        doctor.setPhone(message.getText());



        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Shifokor bilan muzokara natijasi ni kiriting \n" +
                        "*Masalan : kelishildi yoki kelishilmadi*"));



    }

    public void setDoctorPhoneNumber(Message message) {

        doctor.setSpeciality(message.getText());

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Iltimos shifokorning telefon raqamini kiriting\n" +
                        "*Masalan : +998971234567*"));




    }

    public void finishGetDoctorData(Message message) {

        doctor.setStatus(message.getText());

        boolean exists = doctorRepository.existsByPhone(doctor.getPhone());

        if (exists) {
            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Siz ushbu shifokorni qo'sha olmaysiz ! Ushbu shifokor bazada mavjud*",
                    Keyboard.backMenu()));

            return;
        }

        Worker worker = new Worker();
        worker.setFullName(workerDTO.getFullName());
        worker.setDepartment(workerDTO.getDepartment());
        worker.setArea(workerDTO.getArea());
        worker.setPosition(workerDTO.getPosition());
        worker.setPassword(workerDTO.getPassword());
        worker.setPhone(workerDTO.getPhone());
        doctor.setWorker(worker);

        doctorRepository.save(doctor);

        doctor = new Doctor();

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "*Muvaffaqqiyatli saqlandi* ✅✅✅ ",
                Keyboard.backMenu()));


    }


    public void searchDoctor(Message message) {

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
                "Iltimos  shifokor ismi va familiyasini  kiriting " +
                        "*\nMasalan *: Ali Aliyev "));



    }


    public void scan(Worker worker) {
          workerDTO.setFullName(worker.getFullName());
          workerDTO.setDepartment(worker.getDepartment());
          workerDTO.setPhone(worker.getPhone());
          workerDTO.setPassword(worker.getPassword());
          workerDTO.setPosition(worker.getPosition());
          workerDTO.setArea(worker.getArea());

    }
}
