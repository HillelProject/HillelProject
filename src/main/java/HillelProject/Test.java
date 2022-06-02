package HillelProject;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {


    private static Map<String, Integer> sex;
    private static Map<String, Integer> dish;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        initSex();
        initDish();

        String botAnswer = process(message);
        System.out.println(botAnswer);
    }

    public static void initSex() {
        sex = new LinkedHashMap<>();

        sex.put("М", 3000);
        sex.put("Ж", 2500);

    }

    public static void initDish() {
        dish = new LinkedHashMap<>();

        dish.put("Борщ", 440);
        dish.put("Вареники", 620);
        dish.put("Голубцы", 480);
        dish.put("Блинчики", 450);
        dish.put("Омлет", 370);
        dish.put("Плов", 690);
    }

    public static String process(String message) {
        if (isHelloMessage(message)) {
            String botName = "твой персональный диетолог";
            return "Приветствую, я - " + botName;
        }

        double sexСalories = find(message, sex);
        double dishCalories = find(message, dish);

        if (sexСalories < 0) {
            return "Введите свой пол одной буквой (М,Ж) и через пробел название блюда";
        }

        if (dishCalories < 0) {
            return "Введите название блюда";
        }

        double dishCount = calculateDishCount(dishCalories, sexСalories);
        String result = String.format("%.0f",dishCount*100);
        return "Вы получили: " + result + "% суточной нормы каллорий";
    }

    public static int find(String message, Map<String, Integer> data) {
        message = message.toLowerCase();

        for(String word: data.keySet()) {
            String lowerCasedWord = word.toLowerCase();

            if (message.contains(lowerCasedWord)) {
                return data.get(word);
            }
        }

        return -1;
    }

    public static double calculateDishCount(double dishCalories, double sexСalories) {
        double dishCount = dishCalories / sexСalories;
        dishCount = validateDishCount(dishCount);

        return dishCount;
    }

    public static double validateDishCount(double dishCount) {
        if (dishCount == 0) {
            return 1;
        }

        return dishCount;
    }

    private static boolean isHelloMessage(String message) {
        message = message.toLowerCase();

        String helloWord1 = "привет";
        String helloWord2 = "hello";
        String helloWord3 = "Добрий вечір";

        return message.contains(helloWord1) || message.contains(helloWord2);
    }
}