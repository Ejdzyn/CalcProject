package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static TextView answer,hist;

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        MainActivity.process = process;
    }
    public static int LBracket=0;
    public static int RBracket=0;
    public static String process="";
    public static List<Character> Lista = Arrays.asList('+', '-','/','*','×');
    public static boolean isAnswer;
    public static String ans1="";

    public static String getTxt() {
        return txt;
    }

    public static void setTxt(String txt) {
        MainActivity.txt = txt;
    }

    public static String txt;

    public static List<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btnShow = findViewById(R.id.btnShow);


        Button btnEqual = findViewById(R.id.btnEqual);
        Button btnClear = findViewById(R.id.btnClear);

        answer = findViewById(R.id.answer);
        hist = findViewById(R.id.history);

        Functions.del(btnClear);
        Functions.clear(btnClear);

        Functions.showHelp(btnShow,getApplication());

        String start="5%";

        //String p = Functions.changePow(getApplication(),process);
        //Functions.changePow(getApplication());

        for(int i=0;i<start.length();i++){
            history.add(String.valueOf(start.charAt(i)));
        }
        String tmp = "";
        for(int i=0;i<history.size();i++){
            tmp+=history.get(i);
        }
        process=tmp;



        hist.setText(process);
        answer.setText(process);

        Functions.Equals(btnEqual,getApplication());

    }
}
