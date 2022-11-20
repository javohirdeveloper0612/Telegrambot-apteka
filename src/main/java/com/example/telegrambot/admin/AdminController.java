package com.example.telegrambot.admin;

import com.example.telegrambot.admin.menu.AddEmployeeService;
import com.example.telegrambot.admin.menu.DeleteWorker;
import com.example.telegrambot.admin.menu.GetPharmacyLocation;
import com.example.telegrambot.admin.menu.ListData;
import com.example.telegrambot.button.InlineKeyboard;
import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.contstant.Constant;
import com.example.telegrambot.contstant.Step;
import com.example.telegrambot.entity.Users;
import com.example.telegrambot.entity.Worker;
import com.example.telegrambot.service.CallbackqueryMessage;
import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.util.SendMsg;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminController {
    public final TelegramBot telegramBot;

    public final CallbackqueryMessage callbackqueryMessage;

    public final GetPharmacyLocation getPharmacyLocation;

    public final ListData listData;

    public final AddEmployeeService addEmployeeService;

    public final DeleteWorker deleteWorkerById;

    private List<Users> users = new ArrayList<>();


    public AdminController(@Lazy TelegramBot telegramBot, @Lazy CallbackqueryMessage callbackqueryMessage, GetPharmacyLocation getPharmacyLocation, ListData listData, AddEmployeeService addEmployeeService, DeleteWorker deleteWorkerById) {
        this.telegramBot = telegramBot;

        this.callbackqueryMessage = callbackqueryMessage;
        this.getPharmacyLocation = getPharmacyLocation;
        this.listData = listData;
        this.addEmployeeService = addEmployeeService;
        this.deleteWorkerById = deleteWorkerById;
    }


    public void handle(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            Users users = saveUser(update.getMessage());

            Message message = update.getMessage();
            String text = message.getText();

            switch (text) {
                case "/start" -> {
                    start(message);
                    return;
                }

                case "/about" -> {
                    telegramBot.about(message);
                    return;
                }

                case Constant.doctorList -> {
                    listData.doctorList(message);
                    return;
                }

                case Constant.addworker -> {
                    addEmployeeService.addWorker(message);
                    users.setStep(Step.department);
                    return;
                }
                case Constant.workerList -> {
                    listData.workerList(message);
                    return;
                }
                case Constant.deleteWorker -> {
                    deleteWorkerById.deleteWorker(message);
                    users.setStep(Step.deleteById);
                    return;
                }
                case Constant.backMenu -> {
                    menu(message);
                    return;
                }
                case Constant.pharmacyList -> {
                    listData.pharmacyList(message);

                    return;
                }

                case Constant.location -> {
                    getPharmacyLocation.getLocation(message);
                    users.setStep(Step.getPharmacyID);
                    return;
                }
            }


            switch (users.getStep()) {

                case Step.department -> {
                    addEmployeeService.getDepartment(message);
                    users.setStep(Step.workerFullName);
                }
                case Step.workerFullName -> {
                    addEmployeeService.getPosition(message);
                    users.setStep(Step.getworkerPhone);
                }
                case Step.getworkerPhone -> {
                    addEmployeeService.getWorkerPosition(message);

                    users.setStep(Step.getPosition);
                }
                case Step.getPosition -> {
                    addEmployeeService.getArea(message);
                    users.setStep(Step.getArea);
                }

                case Step.getArea -> {
                    addEmployeeService.getPassword(message);
                    users.setStep(Step.getPassword);
                }
                case Step.getPassword -> {
                    addEmployeeService.finishGetWorkerData(message);
                    users.setStep("null");
                }


                case Step.getPharmacyID -> {
                    getPharmacyLocation.findLocationById(message);
                    users.setStep("null");
                }

                case Step.deleteById -> {
                    deleteWorkerById.deleteWorkerById(message);
                    users.setStep("null");
                }
            }


        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message message = callbackQuery.getMessage();
            String data = callbackQuery.getData();
            if ("go to menu".equals(data)) {
                menu(message);
            }
        }
    }


    private void menu(Message message) {
//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "*Siz asosiy sahifadasiz " +
                        "\nO'zingizga kerakli menu ni tanlang*",
                Keyboard.menuButtonForAdmin()));


    }

    private void start(Message message) {

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setChatId(message.getChatId());
//        deleteMessage.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "*Assalomu alekum umumiy bosharuv paneliga xush kelibsiz*",
                InlineKeyboard.goToMenu("Asosiy sahifaga o'tish")));

    }

    private Users saveUser(Message message) {

        for (Users user : users) {
            if (user.getChatId().equals(message.getChatId())) {
                return user;
            }
        }
        Users user = new Users();

        user.setChatId(message.getChatId());

        users.add(user);
        return user;
    }


}
