package com.example.telegrambot.admin.menu;

import com.example.telegrambot.button.Keyboard;
import com.example.telegrambot.entity.Worker;
import com.example.telegrambot.repository.WorkerRepository;
import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.util.SendMsg;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@Component
public class DeleteWorker {

    public final TelegramBot telegramBot;
    public final WorkerRepository workerRepository;

    public DeleteWorker(TelegramBot telegramBot, WorkerRepository workerRepository) {
        this.telegramBot = telegramBot;
        this.workerRepository = workerRepository;
    }


    public void deleteWorkerById(Message message) {
        String text = message.getText();

//        DeleteMessage deleteMessage = new DeleteMessage();
//        deleteMessage.setMessageId(message.getMessageId() - 1);
//        deleteMessage.setChatId(message.getChatId());
//        telegramBot.send(deleteMessage);
//
//        DeleteMessage deleteMessage1 = new DeleteMessage();
//        deleteMessage1.setChatId(message.getChatId());
//        deleteMessage1.setMessageId(message.getMessageId());
//        telegramBot.send(deleteMessage1);


        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                count++;
            }
        }

        if (count > 0) {

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*ID raqam da harf yoki belgi bo'lmasligi kerak*",
                    Keyboard.backMenu()));


            return;
        }

        Long id = Long.valueOf(message.getText());
        Optional<Worker> optional = telegramBot.workerRepository.findById(id);
        if (optional.isEmpty()) {

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Bunday id li xodim mavjud emas*",
                    Keyboard.backMenu()));

        } else {
            workerRepository.delete(optional.get());

            telegramBot.send(SendMsg.sendMsgParse(message.getChatId(),
                    "*Muvaffaqqiyatli o'chirildi \uD83D\uDDD1\uD83D\uDDD1\uD83D\uDDD1*",
                    Keyboard.backMenu()));

        }


    }

    public void deleteWorker(Message message) {

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
                "*Iltimos xodim ni o'chirish uchun xodimning Id raqamini kiriting*"));

    }
}
