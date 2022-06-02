package HillelProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 Класс который содениняет ТелеграмБот с Базой Данных.
*/
public class BotApp {
    private static Connect_to_SQL mainJava;
    private static Products products;
    public static String result;


    // Создает объект класса MainJava
    public static void mainJava() {
        mainJava = new Connect_to_SQL();
    }

    // Создает объект класса Products
    public static void products() {
        products = new Products();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String product = scanner.nextLine();
        mainJava();
        String botAnswer = process(product);
        System.out.println(botAnswer);
    }

    /**
     * Метод который создает подключение к базе данных,
     * после того как пользователь напишет свой продукт проверяет базу данных на наличе этого продукта
     * и выводит его. В случае если продукта нет, выводит что такого продукта нет.
     **/
    public static String process(String message) {
        try {
            // комманда для SQL которая выводит базу данных
            String sqlWorker = ("Select * from d58pld23fdkd1a.products.products");

            // Создаем подключение к базе данных
            Statement statement = mainJava.connection.createStatement();

            // Выполняем команду Select для SQL
            ResultSet resultSet = statement.executeQuery(sqlWorker);
            products();


            /* Проверяем всю базу данных на наличие продукта, если такой есть,
              то задаем переменные из таблицы в Класс Products
             */
            while (resultSet.next()) {

                result = resultSet.getString(1);
                if (result.toUpperCase().contains(message.toUpperCase())) {
                    products.setCalories(resultSet.getInt(5));
                    products.setProduct_name(resultSet.getString(1));
                    products.setProtein(resultSet.getDouble(2));
                    products.setCarbohydrates(resultSet.getDouble(3));
                    products.setFats(resultSet.getDouble(4));
                    System.out.println(products.toString());
                } else if (!resultSet.next() && !result.contains(message))
                    return ("Такого продукта нет");


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products.toString();

    }
}