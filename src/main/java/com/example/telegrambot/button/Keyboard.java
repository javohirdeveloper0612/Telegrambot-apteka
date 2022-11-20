package com.example.telegrambot.button;

import com.example.telegrambot.contstant.Constant;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Keyboard {


    /**
     * This method is used for phone button
     *
     * @return ReplyKeyboardMarkup
     */


    public static ReplyKeyboardMarkup menuButtonForAdmin() {
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Shifokorlar ro'yxati \uD83D\uDCD6\uD83D\uDCD6\uD83D\uDCD6");
        firstRow.add("Xodimlar ro'yxati \uD83D\uDCD6\uD83D\uDCD6\uD83D\uDCD6");

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("Xodimlarni o'chirish \uD83D\uDDD1\uD83D\uDDD1\uD83D\uDDD1");
        secondRow.add("Xodim qo'shish ➕➕");

        KeyboardRow pharmacy = new KeyboardRow();
        pharmacy.add("Dorixonalar ro'yxati \uD83D\uDCD6\uD83D\uDCD6\uD83D\uDCD6");
        pharmacy.add("Dorixona joylashuvini olish \uD83D\uDCCD\uD83D\uDCCD\uD83D\uDCCD");


        List<KeyboardRow> keyboardRows = Arrays.asList(firstRow, pharmacy, secondRow);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setSelective(false);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        return keyboardMarkup;
    }


    public static ReplyKeyboardMarkup phoneButton() {

        KeyboardButton phone = new KeyboardButton();
        phone.setText("Telefon raqam ni ulashish  ↗  ↗  ️↗️️");
        phone.setRequestContact(true);

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(phone);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setOneTimeKeyboard(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);
        return keyboardMarkup;
    }

    /**
     * This method is used for menu button
     *
     * @return ReplyKeyboard
     */
    public static ReplyKeyboardMarkup mainMenuButton() {


        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(Constant.serchingDoctor);
        firstRow.add(Constant.addDoctor);


        KeyboardRow claim = new KeyboardRow();
        claim.add(Constant.claim);

        KeyboardRow pharmacy = new KeyboardRow();
        pharmacy.add(Constant.pharmacy);

        List<KeyboardRow> keyboardRows = Arrays.asList(firstRow, pharmacy, claim);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        return replyKeyboardMarkup;
    }

    /**
     * This method is used for locattion button
     *
     * @return ReplyKeyboard
     */
    public static ReplyKeyboardMarkup location() {

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setRequestLocation(true);
        keyboardButton.setText("Geolokatsiya jo'natish   ↗ ↗ ↗  ");
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(keyboardButton);


        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        return replyKeyboardMarkup;

    }

    /**
     * This method is used for back button
     *
     * @return ReplyKeyboardButton
     */
    public static ReplyKeyboardMarkup backMenu() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(Constant.backMenu);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

}
