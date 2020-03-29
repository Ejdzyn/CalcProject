package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
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

    public static String getTxt() {
        return txt;
    }

    public static void setTxt(String txt) {
        MainActivity.txt = txt;
    }

    public static String txt="";
    public static String process="";
    public static int dots=1;
    public static List<Character> Lista = Arrays.asList('+', '-','/','*','×');
    public static int LBracket=0;
    public static int RBracket=0;
    public static boolean isAnswer;
    public static String ans1="";

    public static List<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btnShow = findViewById(R.id.btnShow);
        ImageView btnRotate = findViewById(R.id.btnRotate);

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnAdd = findViewById(R.id.btnPlus);
        Button btnNegate = findViewById(R.id.btnNegate);
        Button btnPercent = findViewById(R.id.btnPercent);
        Button btnLeftBracket = findViewById(R.id.btnLeftBracket);
        Button btnRightBracket = findViewById(R.id.btnRightBracket);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnEqual = findViewById(R.id.btnEqual);
        Button btnDot = findViewById(R.id.btnDot);
        Button btnDiv = findViewById(R.id.btnDiv);
        Button btnX = findViewById(R.id.btnX);
        Button btnMod = findViewById(R.id.btnMod);
        answer = findViewById(R.id.answer);
        hist = findViewById(R.id.history);
        Button btnClear = findViewById(R.id.btnClear);
        Button log = findViewById(R.id.btnLog);
        Button fact = findViewById(R.id.btnFact);
        Button sqrt = findViewById(R.id.btnSqrt);
        Button x2 = findViewById(R.id.btnX2);
        Button x3 = findViewById(R.id.btnX3);

        answer.setText(process);
        hist.setText(process);

        Functions.del(btnClear);
        Functions.clear(btnClear);

        Functions.num0(btn0);
        Functions.numb(btn1,'1');
        Functions.numb(btn2,'2');
        Functions.numb(btn3,'3');
        Functions.numb(btn4,'4');
        Functions.numb(btn5,'5');
        Functions.numb(btn6,'6');
        Functions.numb(btn7,'7');
        Functions.numb(btn8,'8');
        Functions.numb(btn9,'9');
        Functions.dot(btnDot);

        Functions.LBracket(btnLeftBracket);
        Functions.RBracket(btnRightBracket);
        Functions.FillRBrackets(btnRightBracket);

        btnRotate.setOnClickListener(new Button.OnClickListener(){

            @SuppressLint("SourceLockedOrientationActivity")
            @Override
            public void onClick(View arg0) {
                int orient = getResources().getConfiguration().orientation;
                if(orient==Configuration.ORIENTATION_LANDSCAPE){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                else{
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }

        });

        Functions.showHelp(btnShow,getApplication());


        Functions.operation(btnX,'×');
        Functions.operation(btnAdd,'+');
        Functions.operation(btnDiv,'/');
        Functions.operation(btnMinus,'-');
        Functions.pow(x2,"2");
        Functions.pow(x3,"3");

        Functions.negate(btnNegate);
        Functions.percent(btnPercent);


        String tmp="";
        for(int i=0;i<history.size();i++){
            tmp+=history.get(i);
        }
        process=tmp;

        Functions.Equals(btnEqual,getApplication());
    }
}
