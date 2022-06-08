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
        double result=1;
        if (Double.parseDouble(hashMap.get("5"))==88.36) {
            result =Double.parseDouble(hashMap.get("6"))*( Double.parseDouble(hashMap.get("5")) + (manWeight * Double.parseDouble(hashMap.get("3")))+(manHeight*Double.parseDouble(hashMap.get("2")))
                    -(manYear*Integer.parseInt(hashMap.get("4"))));
        }
        else if (Double.parseDouble(hashMap.get("5"))==447.6){
            result = Double.parseDouble(hashMap.get("6"))*((Double.parseDouble(hashMap.get("5")) + (womanWeight * Double.parseDouble(hashMap.get("3")))+(womanHeight*Double.parseDouble(hashMap.get("2")))
                    -(womanYear*Double.parseDouble(hashMap.get("4")))));

        }
        return Double.toString(result);
    }


}
