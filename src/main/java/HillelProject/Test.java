package HillelProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String product = scanner.nextLine();
        MainJava mainJava = new MainJava();
        String sqlWorker = ("Select * from d58pld23fdkd1a.products.products");

        try {
            Statement statement = mainJava.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlWorker);
            while (resultSet.next()) {
                Products products = new Products();
                String result = resultSet.getString(1);
                if (result.toUpperCase().contains(product.toUpperCase())) {
                    products.setId(resultSet.getInt(5));
                    products.setProduct_name(resultSet.getString(1));
                    products.setProtein(resultSet.getDouble(2));
                    products.setCarbohydrates(resultSet.getDouble(3));
                    products.setFats(resultSet.getDouble(4));
                    System.out.println(products);}
                else if(!resultSet.next() && !result.contains(product)){
                    System.out.println("Такого продукта нет");

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
