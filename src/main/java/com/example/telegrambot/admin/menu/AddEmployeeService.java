package com.example.telegrambot.admin.menu;

import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.entity.Worker;
import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.util.SendMsg;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class AddEmployeeService {


    Worker worker = new Worker();

    private final TelegramBot telegramBot;

    public AddEmployeeService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void getPassword(Message message) {

        worker.setArea(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(), "Xodim bot dan foydalanishi uchun parol o'rnating \n" +
                "*(Eslatma : parol takralanmas bo'lishi kerak)*"));

    }

    public void getDepartment(Message message) {
        worker.setFullName(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Xodim ishlaydigan bo'lim nomini kiriting \n" +
                        "*Masalan : Sotuv bo'limi*"));

    }

    public void getArea(Message message) {
        worker.setPhone(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Xodimga  biriktirilgan hududni kiriting  \n" +
                        "*Masalan : Toshkent shahar Olmazor tumani*"));
    }


    public void finishGetWorkerData(Message message) {

        worker.setPassword(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);


        boolean phone = telegramBot.workerRepository.existsByPhone(worker.getPhone());
        boolean password = telegramBot.workerRepository.existsByPassword(worker.getPassword());

        if (phone) {

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Kechirasiz Siz ushbu xodimni qo'sha olmaysiz ! Chunki ushbu xodim bazada mavjud*",
                    Keyboard.backMenu()));
        } else if (password) {

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Kechirasiz Ushbu " + worker.getPassword() + " paroliga ega xodim mavjud !*",
                    Keyboard.backMenu()));
        } else {
            telegramBot.workerRepository.save(worker);
            worker = new Worker();

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Muvaffaqqiyatli saqlandi* ✅✅✅",
                    Keyboard.backMenu()));
        }

    }

    public void getPosition(Message message) {

        worker.setDepartment(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Xodimning lavozimini kiriting : \n" +
                        "*Masalan : Manager*"));


    }

    public void getWorkerPosition(Message message) {

        worker.setPosition(message.getText());

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);

        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Xodimning telefon raqamini kiriting \n" +
                        "*Masalan :* +998901234567"));

    }


    public void addWorker(Message message) {

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setMessageId(message.getMessageId());
//        deleteMessage1.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage1);


        telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                "Iltimos xodimning ismi va familiyasini kiriting :" +
                        "\n*Masalan : Ali Aliyev * "));

    }


}
