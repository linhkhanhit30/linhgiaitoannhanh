package com.example.linh_minutes.giaitoannhanh1;

import java.util.Random;


public class Question {

    int num1, num2,result;

    public Question(){
        Random rd = new Random();
        this.num1 = rd.nextInt(11);
        this.num2 = rd.nextInt(10-num1+1);
        // Log.e(TAG, num1 +" - "+ num2 );

    }
    public int cong(){
        return num1 + num2;
    }
    public int tru(){
        change();
        return num1-num2;
    }

    public void change() {
        int temp;
        if (num1 < num2) {
            temp=num1;
            num1=num2;
            num2=temp;
        }
    }
    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getResult() {
        return result;
    }


}
