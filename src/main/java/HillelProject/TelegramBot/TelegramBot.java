package HillelProject.TelegramBot;

import HillelProject.Methods.specialOfTheDay;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


// Класс ТелеграмБота, методы для получение и отправки сообщений.
public class TelegramBot extends TelegramLongPollingBot {

    public TelegramBot() {
        BotApp.mainJava();
        BotApp.products();
        IndividualDataSQL.mainJava();
    }

    HashMap<String, String> hashForProducts = new HashMap<>();


    // метод обработки получения и отправки сообщений в телеграмм
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        IndividualDataFormula.mainJava();
        if (message != null && message.hasText()) {

            //Команда "/Start"
            if (message.getText().equals("/start")) {
                message.getChatId();
                sendText(message, "Я работаю ёпта, выбирай из менюшки что надо");
            }

            // Команда Рецепт Дня
            if (message.getText().equals("Рецепт Дня")) {
                message.getChatId();
                String messages = update.getMessage().getText();
                String response = specialOfTheDay.process(messages);
                sendText(message, response);
                hashForProducts.clear();
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message.getChatId()));
                Connect_to_SQL.closeConnection();
            }

            // Если несколько раз нажать команду "Калории продуктов".
            if (message.getText() != null && hashForProducts.containsKey("1") && message.getText().equals("Калории продуктов")) {
                message.getChatId();
                sendText(message, "Блин, да понял я уже, давай вводи продукт");
                hashForProducts.clear();
            }


            // Выводит данные продуктов из базы данных после ввода продукта
            if (message.getText() != null &&  hashForProducts.containsKey("1") && !message.getText().equals("Калории продуктов") && !message.getText().equals("Индивидуальный счетчик калорий") && !message.getText().equals(" Анекдот Дня") && !message.getText().equals(" Рецепт Дня")) {
                message.getChatId();
                String messages = update.getMessage().getText();
                String response = BotApp.process(messages);
                inlineButton2(message, response);

            }

            // Реакция на нажатие кнопки "Калории продуктов"
            if (message.getText().equals("Калории продуктов") && !hashForProducts.containsKey("1") && !message.getText().equals("Индивидуальный счетчик калорий") && !message.getText().equals(" Анекдот Дня") && !message.getText().equals(" Рецепт Дня")) {
                message.getChatId();
                hashForProducts.clear();
                inlineButton1(message, "Введите название продукта: ");
                hashForProducts.put("1", "Калории продуктов");
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message.getChatId()));
                Connect_to_SQL.closeConnection();
            }

            // Выбор физической активности в методе Индивидуальный расчет калорий
            if (message.getText() != null && IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"4") && !IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"5")&& !message.getText().contains("Калории продуктов,Индивидуальный счетчик калорий, Анекдот Дня, Рецепт Дня")) {
                message.getChatId();
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String data = callbackQuery.getData();
                if (data.equals("Мужской") || data.equals("Женский")) {
                    inlineButton4(message, "Выберите степень физической активности из списка:");
                }
            }

            // Выбор пола в методе Индивидуальный расчет калорий
            if (message.getText() != null && IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"3") && !IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"4") && !message.getText().contains("Калории продуктов,Индивидуальный счетчик калорий, Анекдот Дня, Рецепт Дня")) {
                message.getChatId();
                if (!Pattern.matches("[0-9]+[\\.]?[0-9]*", message.getText())) {
                    inlineButton1(message, "Пожайлуста вводите только цифры\nВведите свой возраст(например: 25): ");
                } else if (Double.parseDouble(message.getText()) < 150 && Double.parseDouble(message.getText()) > 0) {
                    inlineButton3(message, "Выберите Ваш пол из списка:");
                    IndividualDataSQL.process(String.valueOf(message.getChatId()), "4", message.getText());
                    Connect_to_SQL.closeConnection();

                } else {
                    inlineButton1(message, "Пожайлуста вводите реальные цифры\nВведите свой возраст(например: 25): ");
                }
            }

            //Выбор возраста в методе Индивидуальный расчет калорий
            if (message.getText() != null && IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"2") && !IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"3") && !message.getText().contains("Калории продуктов,Индивидуальный счетчик калорий, Анекдот Дня, Рецепт Дня")) {
                message.getChatId();
                if (!Pattern.matches("[0-9]+[\\.]?[0-9]*", message.getText())) {
                    inlineButton1(message, "Пожайлуста вводите только цифры\nВведите свой вес(например: 50): ");
                } else if (Double.parseDouble(message.getText()) < 250 && Double.parseDouble(message.getText()) > 0) {
                    inlineButton1(message, "Введите свой возраст(например: 25)");
                    IndividualDataSQL.process(String.valueOf(message.getChatId()), "3", message.getText());
                    Connect_to_SQL.closeConnection();

                } else {
                    inlineButton1(message, "Пожайлуста вводите реальные цифры\nВведите свой вес(например: 50): ");
                }


            }

            //Выбор веса в методе Индивидуальный расчет калорий
            if (message.getText() != null && IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"1") && !IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"2") && !message.getText().contains("Калории продуктов,Индивидуальный счетчик калорий, Анекдот Дня, Рецепт Дня")) {
                message.getChatId();
                if (!Pattern.matches("[0-9]+[\\.]?[0-9]*", message.getText())) {
                    inlineButton1(message, "Пожайлуста вводите только цифры\nВведите свой рост(например: 175): ");
                } else if (Double.parseDouble(message.getText()) < 250 && Double.parseDouble(message.getText()) > 0) {
                    inlineButton1(message, "Введите свой вес(например: 50): ");
                    IndividualDataSQL.process(String.valueOf(message.getChatId()), "2", message.getText());
                    Connect_to_SQL.closeConnection();

                } else {
                    inlineButton1(message, "Пожайлуста вводите реальные цифры\nВведите свой рост(например: 175): ");
                }


            }

            //Выбор роста в методе Индивидуальный расчет калорий
            if (message.getText().equals("Индивидуальный счетчик калорий") && !IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message.getChatId()),"1") && !message.getText().equals("Калории продуктов") && !message.getText().equals(" Анекдот Дня") && !message.getText().equals(" Рецепт Дня")) {
                hashForProducts.clear();
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message.getChatId()));
                message.getChatId();
                inlineButton1(message, "Индивидуальный расчет суточной нормы калорий.\nВведите свой рост (например: 175): ");
                IndividualDataSQL.process(String.valueOf(message.getChatId()), "1", "1");
                Connect_to_SQL.closeConnection();

            }

            // Реакция на Inline меню
        } else if (update.hasCallbackQuery()) {
            Message message1 = update.getCallbackQuery().getMessage();
            IndividualDataFormula.mainJava();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setParseMode(ParseMode.MARKDOWN);
            sendMessage.setChatId(String.valueOf(message1.getChatId()));

            // Реакция на Хватит в Inline меню
            if (data.equals("Хватит")) {
                sendMessage.setChatId(String.valueOf(message1.getChatId()));
                sendMessage.setText("Хорошо");
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message1.getChatId()));
                hashForProducts.clear();
                Connect_to_SQL.closeConnection();
            }
            // Реакция на Cancel в Inline меню
            if (data.equals("Cancel")) {
                sendMessage.setChatId(String.valueOf(message1.getChatId()));
                sendMessage.setText("Хорошо");
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message1.getChatId()));
                hashForProducts.clear();
                Connect_to_SQL.closeConnection();
            }

            // Реакция на выбор пола Мужской в Inline меню
            if (data.equals("Мужской")) {
                inlineButton4(message1, "Выберите степень физической активности из списка:");
                IndividualDataSQL.process(String.valueOf(message1.getChatId()), "5", "88.36");
                Connect_to_SQL.closeConnection();

                // Реакция на выбор пола Женский в Inline меню
            } else if (data.equals("Женский")) {
                inlineButton4(message1, "Выберите степень физической активности из списка:");
                IndividualDataSQL.process(String.valueOf(message1.getChatId()), "5", "447.6");
                Connect_to_SQL.closeConnection();


                // Реакция на выбор "Нет физических нагрузок" в Inline меню
            } else if (data.equals("Нет физических нагрузок")&& IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message1.getChatId()),"5")) {
                IndividualDataSQL.process(String.valueOf(message1.getChatId()), "6", "1.2");
                sendMessage.setText(IndividualDataFormula.individualCaloriesCalculation(String.valueOf(message1.getChatId())));
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message1.getChatId()));
                Connect_to_SQL.closeConnection();

                // Реакция на выбор "Нагрузки 1–3 раза в неделю" в Inline меню
            } else if (data.equals("Нагрузки 1–3 раза в неделю")&& IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message1.getChatId()),"5")) {
                IndividualDataSQL.process(String.valueOf(message1.getChatId()), "6", "1.375");
                sendMessage.setText(IndividualDataFormula.individualCaloriesCalculation(String.valueOf(message1.getChatId())));
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message1.getChatId()));
                Connect_to_SQL.closeConnection();

                // Реакция на выбор "Нагрузки 3–5 раз в неделю" в Inline меню
            } else if (data.equals("Нагрузки 3–5 раз в неделю")&& IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message1.getChatId()),"5")) {
                IndividualDataSQL.process(String.valueOf(message1.getChatId()), "6", "1.55");
                sendMessage.setText(IndividualDataFormula.individualCaloriesCalculation(String.valueOf(message1.getChatId())));
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message1.getChatId()));
                Connect_to_SQL.closeConnection();

                // Реакция на выбор "Нагрузки 6–7 раз в неделю" в Inline меню
            } else if (data.equals("Нагрузки 6–7 раз в неделю")&& IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message1.getChatId()),"5")) {
                IndividualDataSQL.process(String.valueOf(message1.getChatId()), "6", "1.725");
                sendMessage.setText(IndividualDataFormula.individualCaloriesCalculation(String.valueOf(message1.getChatId())));
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message1.getChatId()));
                Connect_to_SQL.closeConnection();

                // Реакция на выбор "Ежедневно более одной тренировки" в Inline меню
            } else if (data.equals("Ежедневно более одной тренировки")&& IndividualDataFormula.checkIndividualCaloriesCalculation(String.valueOf(message1.getChatId()),"5")) {
                IndividualDataSQL.process(String.valueOf(message1.getChatId()), "6", "1.9");
                sendMessage.setText(IndividualDataFormula.individualCaloriesCalculation(String.valueOf(message1.getChatId())));
                IndividualDataFormula.deleteIndividualCaloriesCalculation(String.valueOf(message1.getChatId()));
                Connect_to_SQL.closeConnection();
            }

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();

            }
        }

    }


    // Метод отправки сообщения
    private void sendText(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Рецепт Дня");
        keyboardFirstRow.add("Индивидуальный счетчик калорий");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Калории продуктов");
        keyboardSecondRow.add("Индивидуальный водный баланс");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    // Меню при отправки сообщения "Калории продуктов"
    private void inlineButton1(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        //Inline KeyBoard
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Cancel");
        inlineKeyboardButton.setCallbackData("Cancel");
        inlineKeyboardButtons.add(inlineKeyboardButton);
        inlineButtons.add(inlineKeyboardButtons);
        inlineKeyboardMarkup.setKeyboard(inlineButtons);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);


        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Меню выхода из перечисления калории для продуктов
    void inlineButton2(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        //Inline KeyBoard
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Хватит");
        inlineKeyboardButton.setCallbackData("Хватит");
        inlineKeyboardButtons.add(inlineKeyboardButton);
        inlineButtons.add(inlineKeyboardButtons);
        inlineKeyboardMarkup.setKeyboard(inlineButtons);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);


        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Inline меню для выбора пола в Индивидуальный расчет калорий
    void inlineButton3(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        //Inline KeyBoard
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Мужской");
        inlineKeyboardButton1.setCallbackData("Мужской");
        inlineKeyboardButton2.setText("Женский");
        inlineKeyboardButton2.setCallbackData("Женский");
        inlineKeyboardButtons.add(inlineKeyboardButton1);
        inlineKeyboardButtons.add(inlineKeyboardButton2);
        inlineButtons.add(inlineKeyboardButtons);
        inlineKeyboardMarkup.setKeyboard(inlineButtons);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);


        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    // Inline меню для выбора физической активности в Индивидуальный расчет калорий
    void inlineButton4(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        //Inline KeyBoard
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow2.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton4);
        keyboardButtonsRow3.add(inlineKeyboardButton5);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>(); //Создаём ряд
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);


        inlineKeyboardButton1.setText("Нет физических нагрузок");
        inlineKeyboardButton1.setCallbackData("Нет физических нагрузок");
        inlineKeyboardButton2.setText("Нагрузки 1–3 раза в неделю");
        inlineKeyboardButton2.setCallbackData("Нагрузки 1–3 раза в неделю");
        inlineKeyboardButton3.setText("Нагрузки 3–5 раз в неделю");
        inlineKeyboardButton3.setCallbackData("Нагрузки 3–5 раз в неделю");
        inlineKeyboardButton4.setText("Нагрузки 6–7 раз в неделю");
        inlineKeyboardButton4.setCallbackData("Нагрузки 6–7 раз в неделю");
        inlineKeyboardButton5.setText("Ежедневно более одной тренировки");
        inlineKeyboardButton5.setCallbackData("Ежедневно более одной тренировки");

        inlineKeyboardButtons.add(inlineKeyboardButton1);
        inlineKeyboardButtons.add(inlineKeyboardButton2);
        inlineKeyboardButtons.add(inlineKeyboardButton3);
        inlineKeyboardButtons.add(inlineKeyboardButton4);
        inlineKeyboardButtons.add(inlineKeyboardButton5);
        inlineButtons.add(inlineKeyboardButtons);
        inlineKeyboardMarkup.setKeyboard(rowList);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);


        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "Calories_Products_Bot";
    }

    @Override
    public String getBotToken() {
        return "5415629103:AAHIS7AIxqOOU5bAc58nk1I9w-aqCuyYDG0";
    }
}