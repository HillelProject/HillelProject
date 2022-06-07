package HillelProject;

public class IndividualData {
    private double weight;
    private double height;
    private String sex;
    //private double calorieExpenditurePerDay;  //трата калорий в день
    private double activityFactor;

    public double getActivityFactor() {
        return activityFactor;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getSex() {
        return sex;
    }

//    public double getCalorieExpenditurePerDay() {
//        return calorieExpenditurePerDay;
//    }

    public String enterIndividualData() {
        return "Введите по порядку через пробел ваш вес, рост, пол";
    }

    /**
     * @param message разбивается предложение с весом ростом и полом на отдельные слова
     *                инициализация полей
     */
    private void IndividualData(String message) {
        if (weight == 0.0 && height == 0.0 && sex == null) {
            enterIndividualData();
        }
        String mes[] = message.split(" ");
        for (int i = 0; i < mes.length; i++) {
            this.weight = Double.parseDouble(mes[0]);
            this.height = Double.parseDouble(mes[1]);
            this.sex = mes[2];
        }
    }

    public String enterInformationAboutLifeStyle() {
        return "Выберете ваш образ жизни: минимальный уровень активности(без нагрузок) - введите 1,низкий уровень активности(нагрузки 1-3 раза в неделю) - введите 2, средний уровень активности(3-5 дней в неделю) - введите 3, высокий уровень активности(тренировки каждый день или минимум 5 раз в неделю) - введите 4, очень высокий уровень активности (тренировки чаще, чем раз в день) - введите 5";
    }

    /**
     * @param message получаем от пользователя информацию о его бразе жизни
     *                возвращает коэффициент активности типа double
     */
    private double lifeStyle(String message) {
        if (activityFactor == 0) {
            enterInformationAboutLifeStyle();
        }
        message = message.toLowerCase();
        if (message.equals("1")) {
            activityFactor = 1.2;
        }
        if (message.equals("2")) {
            activityFactor = 1.375;
        }
        if (message.equals("3")) {
            activityFactor = 1.55;
        }
        if (message.equals("4")) {
            activityFactor = 1.725;
        }
        if (message.equals("5")) {
            activityFactor = 1.9;
        }
        return activityFactor;
    }
}
