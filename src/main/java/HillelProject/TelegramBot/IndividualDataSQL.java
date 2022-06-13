package HillelProject.TelegramBot;


import java.sql.SQLException;
import java.sql.Statement;



public class IndividualDataSQL {

    private static Connect_to_SQL mainJava;
    public static String result;


    // Создает объект класса MainJava
    public static void mainJava() {
        mainJava = new Connect_to_SQL();
    }


    public static void process(String chatId, String number, String value) {

        try {

        // комманда для SQL которая выводит базу данных
        String sqlWorker = ("insert into d58pld23fdkd1a.products.\"individualCalories\" (\"chatId\", number, information)values ("+chatId+","+number+","+value+")");


        // Создаем подключение к базе данных
        Statement statement = mainJava.connection.createStatement();

        // Выполняем команду Select для SQL
        int resultSet = statement.executeUpdate(sqlWorker);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

