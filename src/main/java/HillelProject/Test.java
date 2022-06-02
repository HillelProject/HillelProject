package HillelProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test {
    private static MainJava mainJava;
    private static Products products;
    public static String result;


    public static void mainJava() {
        mainJava = new MainJava();
    }

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


public static String process( String message){
        try {
            String sqlWorker = ("Select * from d58pld23fdkd1a.products.products");
            Statement statement = mainJava.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlWorker);
            products();

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
        }return products.toString();

    }
}
