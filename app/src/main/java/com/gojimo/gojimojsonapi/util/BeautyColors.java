package com.gojimo.gojimojsonapi.util;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by SeRoAl on 3/3/2015.
 */
public class BeautyColors {
    public static int getRandColor(){
        float goldenRatio = 0.618033f;
        Random rnd = new Random();
        //
        float h = rnd.nextFloat();
        h += goldenRatio;
        if (h>1) h-=1.0000;
        return Color.HSVToColor(new float[]{h,0.50f,0.95f});
    }

    public static int getMixedRandColor(int mixColor){
        int col = getRandColor();
        int r = Color.red(col)+Color.red(mixColor);
        int g = Color.green(col)+Color.green(mixColor);
        int b = Color.blue(col)+Color.blue(mixColor);
        /*
        r = r%256;
        b = b%256;
        g = g%256;
        */
        return Color.rgb(r,g,b);
    }
}
