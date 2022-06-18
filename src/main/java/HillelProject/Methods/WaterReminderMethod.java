package HillelProject.Methods;


import HillelProject.TelegramBot.TelegramBot;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class WaterReminderMethod implements Job {
    private static Connect_to_SQL mainJava;
    TelegramBot telegramBot = new TelegramBot();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        // комманда для SQL которая выводит базу данных
        String sqlWorker = ("select distinct \"ChatId\" from d1cfnt21boubau.products.\"waterReminder\"");

        // Создаем подключение к базе данных

        try {
            Statement statement = mainJava.connection.createStatement();


            // Выполняем команду Select для SQL
            ResultSet resultSet = statement.executeQuery(sqlWorker);



            /* Проверяем всю базу данных на наличие продукта, если такой есть,
              то задаем переменные из таблицы в Класс Products
             */
            while (resultSet.next()) {
               String result = resultSet.getString(1);

                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(result);
                sendMessage.setText("Напоминаю, сейчас было бы не плохо выпить стакан воды");
                try {
                    telegramBot.execute(sendMessage);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } Connect_to_SQL.closeConnection();
    }
}
