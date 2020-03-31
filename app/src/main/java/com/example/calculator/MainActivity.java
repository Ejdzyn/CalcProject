package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static TextView answer,hist;

    ViewGroup parent;
    View rollup;
    public static TextView tAns;
    public static ScrollView HistoryScroll;

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

    public static String txt;

    public static String getTmpProcess() {
        return tmpProcess;
    }

    public static void setTmpProcess(String tmpProcess) {
        MainActivity.tmpProcess = tmpProcess;
    }

    public static String tmpProcess="";
    public static String process="";
    public static int dots=1;
    public static List<Character> Lista = Arrays.asList('+', '-','/','*','×');
    public static int LBracket=0;
    public static int RBracket=0;
    public static boolean isAnswer;
    public static boolean isLog =false;
    public static boolean isSqrt=false;
    public static String ans1="";

    public static String SaveHistory="";

    public static List<String> history = new ArrayList<>();

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        rollup = findViewById(R.id.RollUp);
        tAns= findViewById(R.id.HistAns);
        HistoryScroll = findViewById(R.id.ScrollViewHistory);

        ImageView btnCopy = findViewById(R.id.btnCopy);
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
        Button btnPi = findViewById(R.id.btnPi);
        answer = findViewById(R.id.answer);
        hist = findViewById(R.id.history);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnLog = findViewById(R.id.btnLog);
        Button btnFact = findViewById(R.id.btnFact);
        Button btnSqrt = findViewById(R.id.btnSqrt);
        Button x2 = findViewById(R.id.btnX2);
        Button x3 = findViewById(R.id.btnX3);
        ImageView btnHistory = findViewById(R.id.btnHistory);

        answer.setText(process);
        hist.setText(process);

        Functions.del(btnClear,getApplicationContext());
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

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", answer.getText().toString());
                Objects.requireNonNull(clipboard).setPrimaryClip(clip);

                Toast.makeText(MainActivity.this, "Saved to clipboard", Toast.LENGTH_SHORT).show();

            }
        });

        Functions.operation(btnX,'×');
        Functions.operation(btnAdd,'+');
        Functions.operation(btnDiv,'/');
        Functions.operation(btnMinus,'-');
        Functions.pow(x2,"2");
        Functions.pow(x3,"3");
        Functions.factorial(btnFact);
        Functions.pi(btnPi);
        Functions.log10(btnLog);
        Functions.sqrt(btnSqrt);
        Functions.negate(btnNegate);
        Functions.percent(btnPercent);


        btnHistory.setOnClickListener(new View.OnClickListener(){

            boolean visible;

            @Override
            public void onClick(View v) {

                Transition transition = new Slide(Gravity.BOTTOM);
                transition.setDuration(600);
                transition.addTarget(R.id.RollUp);

                TransitionManager.beginDelayedTransition(parent,transition);
                visible=!visible;
                rollup.setVisibility(visible ? View.VISIBLE : View.GONE);

            }
        });


        String tmp="";
        for(int i=0;i<history.size();i++){
            tmp+=history.get(i);
        }
        process=tmp;

        Functions.Equals(btnEqual,getApplication());

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("HISTORY",SaveHistory);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SaveHistory = savedInstanceState.getString("HISTORY");
        tAns.setText(SaveHistory);
    }
}
