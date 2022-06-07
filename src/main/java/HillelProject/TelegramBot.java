package HillelProject;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
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
import java.util.List;

// Класс ТелеграмБота, методы для получение и отправки сообщений.
public class TelegramBot extends TelegramLongPollingBot {

    public TelegramBot() {
        BotApp.mainJava();
        BotApp.products();

    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {

            if (message.getText().equals("Рецепт Дня")) {
                String messages = update.getMessage().getText();
                String response = specialOfTheDay.process("Рецепт Дня");
                sendText(message, response);
            }

            if (message.getText().equals("Калории продуктов")) {
                String messages = update.getMessage().getText();
                String response = BotApp.process(messages);
                inlineButton(message, "Введите продукт");

            }} else if (update.hasCallbackQuery()) {
                String st1= String.valueOf(update.getCallbackQuery().getMessage().getChatId());
                CallbackQuery callbackQuery = new CallbackQuery();
                String data = callbackQuery.getData();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(st1);
                if (data.equals("Сancel")) {
                    sendMessage.setText("Окей");
                } else {
                    sendMessage.setText(BotApp.process("Рецепт Дня"));
                }
                try {
                   execute(sendMessage);
                } catch (RuntimeException e) {

                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }

        }
        }


        private void sendText (Message message, String text){
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
            keyboardSecondRow.add("Анекдот Дня");

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


        private void inlineButton (Message message, String text){
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


        @Override
        public String getBotUsername () {
            return "Calories_Products_Bot";
        }

        @Override
        public String getBotToken () {
            return "5415629103:AAHIS7AIxqOOU5bAc58nk1I9w-aqCuyYDG0";
        }


    }