package com.example.radoslawsubczynski.krokomierz;

import android.support.design.widget.Snackbar;

/**
 * Created by Radek on 2016-12-03.
 */

public class Utils {
    static double lengthStep(String weigh) {
        int number = (Integer.parseInt(weigh));
        if (number < 160) {
            return 40.5;
        } else if (number > 160 && number < 170) {
            return  45.5;
        } else if (number > 170 && number < 210){
            return  50.5;}
        return 0;
    }
}
