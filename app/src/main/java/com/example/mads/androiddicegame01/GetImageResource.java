package com.example.mads.androiddicegame01;

/**
 * Created by borso on 2016. 03. 03..
 */
public class GetImageResource {

    public static int getResorce(int number){

        switch (number) {
            case 1:
                return R.drawable.die1;
            case 2:
                return R.drawable.die2;
            case 3:
                return R.drawable.die3;
            case 4:
                return R.drawable.die4;
            case 5:
                return R.drawable.die5;
            default:
                return R.drawable.die6;
        }
    }
}
