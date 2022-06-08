package HillelProject;

import java.util.HashMap;

public class IndividualData {
    private static double womanHeight = 3.1;
    private static double manHeight = 4.8;
    private static double manWeight = 13.4;
    private static double womanWeight = 9.2;
    private static double manYear = 5.7;
    private static double womanYear = 4.3;
    //private double calorieExpenditurePerDay;  //трата калорий в день


    static HashMap<String, String> hashForIndividualCaloriesCalculation = new HashMap<>();


    public static String individualCaloriesCalculation(HashMap<String, String> hashMap) {
        double result=0;
        if (Integer.parseInt(hashMap.get("5"))==88.36) {
             result = Integer.parseInt(hashMap.get("5")) + (manWeight * Integer.parseInt(hashMap.get("3")))+(manHeight*Integer.parseInt(hashMap.get("2")))
                    -(manYear*Integer.parseInt(hashMap.get("4")))* Integer.parseInt(hashMap.get("6"));
        }
        else if (hashMap.get("5").equals("447.6")){
             result = (Integer.parseInt(hashMap.get("5")) + (womanWeight * Integer.parseInt(hashMap.get("3")))+(womanHeight*Integer.parseInt(hashMap.get("2")))
                    -(womanYear*Integer.parseInt(hashMap.get("4")))) * Integer.parseInt(hashMap.get("6"));

        }return Double.toString(result);
    }

    public static void main(String[] args) {
        hashForIndividualCaloriesCalculation.put("1","20");
        hashForIndividualCaloriesCalculation.put("2","20");
        hashForIndividualCaloriesCalculation.put("3","20");
        hashForIndividualCaloriesCalculation.put("4","20");
        hashForIndividualCaloriesCalculation.put("5","447.6");
        hashForIndividualCaloriesCalculation.put("6","20");
        System.out.println(individualCaloriesCalculation(hashForIndividualCaloriesCalculation));
    }
}
