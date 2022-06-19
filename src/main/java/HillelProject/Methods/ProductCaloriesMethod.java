package HillelProject.Methods;

import HillelProject.ConnectionSQL.ConnectToSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 Класс который содениняет ТелеграмБот с Базой Данных.
*/
public class ProductCaloriesMethod {
    private static ConnectToSQL mainJava;
    private static ProductsClass products;

    // Создает объект класса Products
    public static void products() {
        products = new ProductsClass();
    }

    static List<String> productes = new ArrayList<>();

    /**
     * Метод который создает подключение к базе данных,
     * после того как пользователь напишет свой продукт проверяет базу данных на наличе этого продукта
     * и выводит его. В случае если продукта нет, выводит что такого продукта нет.
     **/

    public static void main(String[] args) {
        ConnectToSQL.mainJava();


        System.out.println(ProductCaloriesMethod.checkIfExistTable("547869531"));
    }

    public static void createTable(String ChatId, String message) {


        try {

            // комманда для SQL которая выводит базу данных
            String searchProductFromSQL = ("select * into d1cfnt21boubau.products." + "id" + ChatId + " from d1cfnt21boubau.products.products where \"Products_Name\" ilike " + "\'%" + message + "%\'");


            // Создаем подключение к базе данных
            Statement statement = mainJava.connection.createStatement();

            // Выполняем команду Select для SQL
            int resultSet = statement.executeUpdate(searchProductFromSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTable(String ChatId) {
        try {
            String searchProductFromSQL = ("DROP TABLE d1cfnt21boubau.products." + "id" + ChatId);

            // Создаем подключение к базе данных
            Statement statement = mainJava.connection.createStatement();

            // Выполняем команду Select для SQL
            int resultSet = statement.executeUpdate(searchProductFromSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String checkIfExistTable(String ChatId) {
        String result = "Такого продукта нет";
        try {


            String searchProductFromSQL = ("select "+"id"+ChatId+".\"Products_Name\" from d1cfnt21boubau.products." + "id" + ChatId);
            // Создаем подключение к базе данных
            Statement statement = mainJava.connection.createStatement();

            // Выполняем команду Select для SQL
            ResultSet resultSet = statement.executeQuery(searchProductFromSQL);
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(null)) {
                    result=resultSet.getString(1);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}