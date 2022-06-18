package HillelProject.Methods;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/*
Класс который содержит данные для подключения к SQL
*/

public class Connect_to_SQL {


    private static final String dbUrl = "jdbc:postgresql://ec2-52-30-75-37.eu-west-1.compute.amazonaws.com:5432/d1cfnt21boubau";
    private static final String username = "nppbhlsnlrefla";
    private static final String password ="f3de020609870a3288e019f02f0a906c132f793f52329e8fc9c2892a03f0b403";
    static Connection connection;
    private static Connect_to_SQL mainJava;

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
    public static void mainJava() {
        mainJava = new Connect_to_SQL();
    }

    public Connect_to_SQL() {
        try {

            connection = DriverManager.getConnection(dbUrl, username, password);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}