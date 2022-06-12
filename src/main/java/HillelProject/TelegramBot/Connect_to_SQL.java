package HillelProject.TelegramBot;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/*
Класс который содержит данные для подключения к SQL
*/

public class Connect_to_SQL {


    private static final String dbUrl = "jdbc:postgresql://ec2-54-75-184-144.eu-west-1.compute.amazonaws.com:5432/d58pld23fdkd1a";
    private static final String username = "psuzbqizgvtxiz";
    private static final String password = "c68e118fccbd4adfc9d6ebc9838595325368b65791256f9ff5197491cc813672";
    static Connection connection;

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void closeConnection()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connect_to_SQL() {
        try {

            connection = DriverManager.getConnection(dbUrl, username, password);


        } catch (SQLException e) {
            System.out.println("Подключение не созданно");
        }

    }

}