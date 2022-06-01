package HillelProject;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class MainJava {

    private static final String dbUrl = "jdbc:postgresql://ec2-52-18-116-67.eu-west-1.compute.amazonaws.com:5432/decsc6nn5nc3am";
    private static final String username = "trjpwbzayodrvw";
    private static final String password = "f4d1cff1b1f5f581ebc159fb819fb7f36f6659a714d263d95af77c9de2f57153";
 Connection connection;

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public MainJava() {
        try {

            connection = DriverManager.getConnection(dbUrl, username, password);

        } catch (SQLException e) {
            System.out.println("Подключение не созданно");
        }
    }

}

