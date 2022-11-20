package com.example.telegrambot.admin.menu;

import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.entity.Doctor;
import com.example.telegrambot.entity.Pharmacy;
import com.example.telegrambot.entity.Worker;
import com.example.telegrambot.repository.PharmacyRepository;
import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.util.SendMsg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Component
public class ListData {

    public final TelegramBot telegramBot;

    public final PharmacyRepository pharmacyRepository;

    public ListData(TelegramBot telegramBot, PharmacyRepository pharmacyRepository) {
        this.telegramBot = telegramBot;
        this.pharmacyRepository = pharmacyRepository;
    }


    public void pharmacyList(Message message) {
//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);

        boolean check = false;

        List<Pharmacy> list = pharmacyRepository.findAll();

        Map<Long, Object[]> pharmacyData = new TreeMap<Long, Object[]>();
        pharmacyData.put(0L, new Object[]{"ID raqami ", "Dorixona nomi", "Dorixona joylashgan hudud",
                "Dorixona rahbari yoki boshqaruvchising ismi va familiyasi", "Dorixona telefon raqami"});

        for (Pharmacy pharmacy : list) {
            if (pharmacy != null) {
                check = true;
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("Dorixonalar ro'yxati");
                XSSFRow row;

                pharmacyData.put(pharmacy.getId(), new Object[]{pharmacy.getId().toString(), pharmacy.getPharmacyName(),
                        pharmacy.getPharmacyArea(), pharmacy.getManagerName(), pharmacy.getOwnerPhoneNumber()});
                Set<Long> keyid = pharmacyData.keySet();

                int rowid = 0;

                for (Long key : keyid) {

                    row = spreadsheet.createRow(rowid++);
                    Object[] objectArr = pharmacyData.get(key);
                    int cellid = 0;

                    for (Object obj : objectArr) {
                        Cell cell = row.createCell(cellid++);
                        cell.setCellValue((String) obj);
                    }

                }

                try {
                    File file = new File("dorixanalar_ro'yxati.xlsx");
                    FileOutputStream out = new FileOutputStream(file);
                    workbook.write(out);
                    out.close();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (!check) {

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Dorixonalar ro'yxati mavjud emas*",
                    Keyboard.backMenu()));

        } else {
            try {
                InputStream inputStream = new FileInputStream("dorixanalar_ro'yxati.xlsx");
                InputFile inputFile = new InputFile();
                inputFile.setMedia(inputStream, "dorixanalar_ro'yxati.xlsx");

                telegramBot.send(SendMsg.sendDc(message.getChatId(), inputFile, Keyboard.backMenu()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void workerList(Message message) {

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);

        List<Worker> workerList = telegramBot.workerRepository.findAll();
        boolean check = false;

        Map<Long, Object[]> workerData = new TreeMap<Long, Object[]>();
        workerData.put(0L, new Object[]{"ID raqami ", "Ismi va Familiyasi", "Bo'lim", "Lavozimi", "Biriktirilgan hudud", "Telefon raqami",
                "Parol"});

        for (Worker worker : workerList) {
            if (worker != null) {
                check = true;
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("Xodimlar ro'yxati");
                XSSFRow row;

                workerData.put(worker.getId(), new Object[]{worker.getId().toString(), worker.getFullName(),
                        worker.getDepartment(), worker.getPosition(), worker.getArea(), worker.getPhone(),
                        worker.getPassword()});
                Set<Long> keyid = workerData.keySet();

                int rowid = 0;

                for (Long key : keyid) {

                    row = spreadsheet.createRow(rowid++);
                    Object[] objectArr = workerData.get(key);
                    int cellid = 0;

                    for (Object obj : objectArr) {
                        Cell cell = row.createCell(cellid++);
                        cell.setCellValue((String) obj);
                    }

                }

                try {
                    File file = new File("xodimlar_royxati.xlsx");
                    FileOutputStream out = new FileOutputStream(file);
                    workbook.write(out);
                    out.close();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }

        if (!check) {
            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Xodimlar ro'yxati mavjud emas*",
                    Keyboard.backMenu()));
        } else {
            try {
                InputStream inputStream = new FileInputStream("xodimlar_royxati.xlsx");
                InputFile inputFile = new InputFile();
                inputFile.setMedia(inputStream, "xodimlar_royxati.xlsx");
                telegramBot.send(SendMsg.sendDc(message.getChatId(),
                        inputFile, Keyboard.backMenu()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void doctorList(Message message) {
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


        List<Doctor> doctorList = telegramBot.doctorRepository.findAll();

        boolean check = false;
        Map<Long, Object[]> doctorDat = new TreeMap<Long, Object[]>();
        doctorDat.put(0L, new Object[]{"ID raqami ", "Ismi va Familiyasi",
                "Shifoxona addressi", "Mutaxasisligi", "Kasalxona nomi", "Holati",
                "Telefon raqami","Xodimnig ID raqami","Xodimning ismi va familiyasi," +
                "Xodimning telefon raqami"});

        for (Doctor doctor : doctorList) {
            if (doctor != null) {
                check = true;
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("Shifokorlar ro'yxati");
                XSSFRow row;

                Worker worker = doctor.getWorker();
                doctorDat.put(doctor.getId(), new Object[]{doctor.getId().toString(), doctor.getFullName(),
                        doctor.getArea(), doctor.getSpeciality(), doctor.getHospitalName(),
                        doctor.getStatus(), doctor.getPhone(),worker.getId(),worker.getFullName(),
                worker.getPhone()});
                Set<Long> keyid = doctorDat.keySet();

                int rowid = 0;

                for (Long key : keyid) {

                    row = spreadsheet.createRow(rowid++);
                    Object[] objectArr = doctorDat.get(key);
                    int cellid = 0;

                    for (Object obj : objectArr) {
                        Cell cell = row.createCell(cellid++);
                        cell.setCellValue((String) obj);
                    }

                }

                try {
                    File file = new File("shifokorlar_ro'yxati.xlsx");
                    FileOutputStream out = new FileOutputStream(file);
                    workbook.write(out);
                    out.close();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (!check) {
            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Shifokorlar ro'yxati mavjud emas*",
                    Keyboard.backMenu()));
        } else {
            try {
                InputStream inputStream = new FileInputStream("shifokorlar_ro'yxati.xlsx");
                InputFile inputFile = new InputFile();
                inputFile.setMedia(inputStream, "shifokorlar_ro'yxati.xlsx");
                telegramBot.send(SendMsg.sendDc(message.getChatId(), inputFile, Keyboard.backMenu()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
