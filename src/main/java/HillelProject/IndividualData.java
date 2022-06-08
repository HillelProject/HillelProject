package HillelProject;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndividualData {

    //private double calorieExpenditurePerDay;  //трата калорий в день

    public static String individualCaloriesCalculation(HashMap<String, String> hashMap) {

         double womanHeight = 3.1;
          double manHeight = 4.8;
          double manWeight = 13.4;
          double womanWeight = 9.2;
          double manYear = 5.7;
          double womanYear = 4.3;
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
