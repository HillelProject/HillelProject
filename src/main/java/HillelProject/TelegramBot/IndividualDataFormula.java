package HillelProject.TelegramBot;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class IndividualDataFormula {

    private static Connect_to_SQL mainJava;


    // Создает объект класса MainJava
    public static void mainJava() {
        mainJava = new Connect_to_SQL();
    }


    public static void main(String[] args) {
        mainJava();
        System.out.println(checkIndividualCaloriesCalculation("547869530", "4"));
    }

    // Расчет индивидуальных калорий взятых из базы данных
    public static String individualCaloriesCalculation(String chatId) {

        double womanHeight = 3.1;
        double manHeight = 4.8;
        double manWeight = 13.4;
        double womanWeight = 9.2;
        double manYear = 5.7;
        double womanYear = 4.3;
        double result = 1;


        try {

            // комманда для SQL которая выводит базу данных
            String height = ("select information from d58pld23fdkd1a.products.\"individualCalories\" where \"chatId\"=" + chatId + " and number=2");
            String weight = ("select information from d58pld23fdkd1a.products.\"individualCalories\" where \"chatId\"=" + chatId + " and number=3");
            String age = ("select information from d58pld23fdkd1a.products.\"individualCalories\" where \"chatId\"=" + chatId + " and number=4");
            String sex = ("select information from d58pld23fdkd1a.products.\"individualCalories\" where \"chatId\"=" + chatId + " and number=5");
            String activity = ("select information from d58pld23fdkd1a.products.\"individualCalories\" where \"chatId\"=" + chatId + " and number=6");
            // Создаем подключение к базе данных
            Statement statementHeight = mainJava.connection.createStatement();
            Statement statementWeight = mainJava.connection.createStatement();
            Statement statementAge = mainJava.connection.createStatement();
            Statement statementSex = mainJava.connection.createStatement();
            Statement statementActivity = mainJava.connection.createStatement();

            // Выполняем команду Select для SQL
            ResultSet resultSetHeight = statementHeight.executeQuery(height);
            ResultSet resultSetWeight = statementWeight.executeQuery(weight);
            ResultSet resultSetAge = statementAge.executeQuery(age);
            ResultSet resultSetSex = statementSex.executeQuery(sex);
            ResultSet resultSetActivity = statementActivity.executeQuery(activity);

            resultSetHeight.next();
            resultSetSex.next();
            resultSetActivity.next();
            resultSetAge.next();
            resultSetWeight.next();


            if (Double.parseDouble(resultSetSex.getString(1)) == (88.36)) {
                result = Double.parseDouble(resultSetActivity.getString(1)) * ((Double.parseDouble(resultSetSex.getString(1)) + (manWeight * Double.parseDouble(resultSetWeight.getString(1))) + (manHeight * Double.parseDouble(resultSetHeight.getString(1)))
                        - (manYear * Double.parseDouble(resultSetAge.getString(1)))));
            } else if (Double.parseDouble(resultSetSex.getString(1)) == (447.6)) {
                result = Double.parseDouble(resultSetActivity.getString(1)) * ((Double.parseDouble(resultSetSex.getString(1)) + (womanWeight * Double.parseDouble(resultSetWeight.getString(1))) + (womanHeight * Double.parseDouble(resultSetHeight.getString(1)))
                        - (womanYear * Double.parseDouble(resultSetAge.getString(1)))));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Double.toString(result);
    }

    // Удаляет из базы данных информацию о пользователе
    public static void deleteIndividualCaloriesCalculation(String chatId) {
        try {

            String deleteInfo = ("delete from d58pld23fdkd1a.products.\"individualCalories\" where \"chatId\"=" + chatId);
            Statement statement = mainJava.connection.createStatement();
            int resultSetHeight = statement.executeUpdate(deleteInfo);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkIndividualCaloriesCalculation(String chatId, String number) {
        try {
            boolean result = false;
            String numbers = ("select number from d58pld23fdkd1a.products.\"individualCalories\" where \"chatId\"=" + chatId);

            Statement statement = mainJava.connection.createStatement();
            ResultSet resultSetHeight = statement.executeQuery(numbers);
            while (resultSetHeight.next()) {
                if (resultSetHeight.getString(1).equals(number)) {
                    result = true;
                }


            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
