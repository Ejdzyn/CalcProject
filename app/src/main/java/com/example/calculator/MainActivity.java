package com.example.calculator;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
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


        Functions.showHelp(btnShow,getApplication());


        Functions.operation(btnX,'×');
        Functions.operation(btnAdd,'+');
        Functions.operation(btnDiv,'/');
        Functions.operation(btnMinus,'-');

        Functions.negate(btnNegate);
        Functions.percent(btnPercent);

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();
                process = process.replaceAll("×","*");

                if(!process.isEmpty() ){


                    Context rhino = Context.enter();
                    rhino.setOptimizationLevel(-1);

                    String finalResult;

                    Scriptable script = rhino.initStandardObjects();
                    DecimalFormat format = new DecimalFormat("0.############");

                   for(int i=0;i<LBracket-RBracket;i++){
                        history.add(")");
                        process+=")";
                        hist.setText(process);
                    }
                    String tmp = "1";
                    StringBuilder tmp2= new StringBuilder();
                    int b;
                    String xd = null;
                    process = process.replace("×","*");
                   try {                                                        //TODO modulo
                       if(process.lastIndexOf("%")!=-1){                   //Warunek wykonania
                           for (int i = 0; i < process.length(); i++) {        //Petla od dlugosci
                               if (process.charAt(i) == '%') {                 //Warunek jestli na i jest %
                                   tmp = process.substring(0, i);
                                   int max =0;
                                   String a = tmp;
                                   for (Character character : Lista) {
                                       if (max <= a.lastIndexOf(character)) {
                                           max = a.lastIndexOf(character);
                                       }
                                   }
                                   hist.setText(String.valueOf(max));
                                   if(!history.get(max).equals("+") || history.get(max).equals("-")){
                                       history.set(i,"/100)");
                                       if(max!=0)max+=1;
                                       history.add(max,"(");      //max > max + 1
                                   }
                                   else{
                                       if(tmp.lastIndexOf("(")==-1)
                                           b=0;
                                        else
                                            b=tmp.lastIndexOf("(");

                                        tmp = tmp.substring(b,max);
                                        String eval = rhino.evaluateString(script,tmp,"Eval",1,null).toString();
                                       history.set(i,"/100*"+eval);
                                   }
                               }

                           }
                       }

                       for(int i =0 ;i<history.size();i++){
                           tmp2.append(history.get(i));
                       }
                       process= tmp2.toString();
                       process = process.replaceAll("×","*");
                       process = process.replace(",",".");

                       finalResult = rhino.evaluateString(script,process,"Eval",1,null).toString();
                       Context.exit();

                       double ans = Double.parseDouble(finalResult);

                       String t = (format.format(ans));
                       t = t.replace(".",",");
                       answer.setText(t);

                       process=t;
                       answer.setHint(t);
                       ans1=t;
                       history.clear();
                       LBracket=0;RBracket=0;

                       isAnswer=true;

                       Toast.makeText(MainActivity.this, "Working " + finalResult , Toast.LENGTH_SHORT).show();


                   }catch (Exception e){
                       e.printStackTrace();
                       Toast.makeText(MainActivity.this, "Error : "+tmp, Toast.LENGTH_SHORT).show();
                   }




                }
            }
        });



    }
}
