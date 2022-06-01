package HillelProject;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class MainJava {


    private static final String dbUrl = "jdbc:postgresql://ec2-54-75-184-144.eu-west-1.compute.amazonaws.com:5432/d58pld23fdkd1a";
    private static final String username = "psuzbqizgvtxiz";
    private static final String password = "c68e118fccbd4adfc9d6ebc9838595325368b65791256f9ff5197491cc813672";
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

