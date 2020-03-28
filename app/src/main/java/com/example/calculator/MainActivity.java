package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.*;

public class MainActivity extends AppCompatActivity {

    public static TextView answer,hist;
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

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();
                process = process.replaceAll("×","*");

                if(!process.isEmpty() ) {

                    String finalResult;
                    Context rhino = Context.enter();
                    rhino.setOptimizationLevel(-1);
                    Scriptable script = rhino.initStandardObjects();
                    DecimalFormat format = new DecimalFormat("0.############");

                    String tLoop = "";
                    String tLoop2 = "";
                    int l = 0;
                    int l2 = 0;
                    if (process.lastIndexOf("^") != -1) {
                        for (int i = 0; i < process.length(); i++) {
                            if (process.charAt(i) == '^') {
                                String a = process.substring(0, i);
                                int max = 0;
                                for (Character character : Lista) {
                                    if (max <= a.lastIndexOf(character)) {
                                        max = a.lastIndexOf(character);
                                    }
                                }
                                tLoop = process.substring(max, i);
                                for (int y = i + 1; y < process.length(); y++) {
                                    if (Character.isDigit(process.charAt(y))) {
                                        tLoop2 += process.charAt(y);
                                        l = y;
                                    } else
                                        break;
                                }

                                for (int y = i - 1; y >= 0; y--) {
                                    if (Character.isDigit(process.charAt(y))) {
                                        l2 = y;
                                    } else
                                        break;
                                }

                                try {
                                    if ((max < l)) {
                                        for (int x = l; x >= l2; x--) {
                                            history.remove(x);

                                        }
                                        double b = Double.parseDouble(tLoop);
                                        double c = Double.parseDouble(tLoop2);
                                        double wynik = Math.pow(b, c);
                                        history.add(max, valueOf(wynik));
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }

                            }       //END IF
                        }       //END FOR
                    }
                    for (int i = 0; i < LBracket - RBracket; i++) {
                        history.add(")");
                        process += ")";
                        hist.setText(process);
                    }
                    String tmp;
                    StringBuilder tmp2 = new StringBuilder();
                    process = process.replace("×", "*");

                    if (process.lastIndexOf("%") != -1) {           //3*(3+5*6)+5%
                        try {
                            for (int i = 0; i < process.length(); i++) {
                                if (process.charAt(i) == '%') {

                                    if(i!=process.length()-1 && Character.isDigit(process.charAt(i+1))){
                                        Toast.makeText(MainActivity.this, "Modulo", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        tmp = process.substring(0, i);
                                        int max = 0;
                                        String a = tmp;
                                        for (Character character : Lista) {
                                            if (max <= a.lastIndexOf(character)) {
                                                max = a.lastIndexOf(character);
                                            }
                                        }

                                        if (!history.get(max).equals("+") || history.get(max).equals("-")) {
                                            history.set(i, "/100");
                                        } else {
                                             history.set(i, "/100+1)");
                                             history.add(max+1,"(");
                                            history.set(max,"*");
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "% ERROR %", Toast.LENGTH_SHORT).show();
                        }
                    }


                    try {
                        for (int i = 0; i < history.size(); i++) {
                            tmp2.append(history.get(i));
                        }
                        process = tmp2.toString();
                        process = process.replaceAll("×", "*");
                        Toast.makeText(MainActivity.this, process, Toast.LENGTH_SHORT).show();
                        finalResult = rhino.evaluateString(script, process, "Eval", 1, null).toString();

                        Context.exit();

                        double ans = Double.parseDouble(finalResult);

                        String t = (format.format(ans));
                        t = t.replace(".", ",");
                        answer.setText(t);
                        process = t;
                        answer.setHint(t);
                        ans1 = t;
                        history.clear();
                        LBracket = 0;
                        RBracket = 0;
                        if (ans != 0)
                            isAnswer = true;

                    }catch(Exception e){
                        Toast.makeText(MainActivity.this, "Error At final", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("process",this.process);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.process = savedInstanceState.getString("process");
    }
}
