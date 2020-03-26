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
    public static List<Character> Lista = Arrays.asList('+', '-','/','*');
    public static int LBracket=0;
    public static int RBracket=0;

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
            Button btnDel = findViewById(R.id.btnDel);
            answer = findViewById(R.id.answer);
            hist = findViewById(R.id.history);
            Button btnClear = findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tmp = answer.getText().toString();

                if(!tmp.isEmpty() && !history.isEmpty())
                {
                    if(tmp.charAt(tmp.length()-1)==')')RBracket--;
                    else if(tmp.charAt(tmp.length()-1)=='(')LBracket--;
                    String subC = answer.getText().toString().substring(0,answer.length()-1);
                    process= process.substring(0, process.length() - 1);
                    hist.setText(process);
                    answer.setText(subC);
                    if(!history.isEmpty()) history.remove(history.size()-1);

                }
            }
        });

        btnClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(answer.getText()!="")
                {
                    answer.setText("");
                    hist.setText("");
                    process="";
                    history.clear();
                    LBracket=0;
                    RBracket=0;
                }

                return true;
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                //Toast.makeText(getApplicationContext(),answer.getText(),Toast.LENGTH_SHORT).show();
*/
                if(history.size()==0)
                    Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),String.valueOf(history)+" LB: "+String.valueOf(LBracket)+" RB: "+String.valueOf(RBracket), Toast.LENGTH_SHORT).show();

                /*
                String xdd="";
                for(int i =0;i<history.size();i++){
                    xdd += history.get(i);


                }*/
                //Toast.makeText(getApplicationContext(), process +" : "+process.length(), Toast.LENGTH_SHORT).show();
                //hist.setText(process);

/*                if(process.isEmpty())
                    Toast.makeText(getApplicationContext(),"empty"+process.length(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"not empty"+process.length(),Toast.LENGTH_SHORT).show();*/

                //Toast.makeText(getApplicationContext(),"proces: "+process,Toast.LENGTH_SHORT).show();
                //hist.setText(process);
            }
        });

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


        btnLeftBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();

                if(!history.isEmpty() && Functions.isNumeric(history.get(history.size()-1))) {
                    history.add("×");
                    history.add("(");
                    process += "×(";
                    LBracket++;
                }
                else {
                    history.add("(");
                    process +="(";
                    LBracket++;
                }
                answer.setText(process);
                hist.setText(process);

            }
        });

        btnRightBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(LBracket>RBracket){
                    process = answer.getText().toString();
                    process +=")";
                    history.add(")");
                    answer.setText(process);
                    hist.setText(process);
                    RBracket++;
                }
            }
        });

        btnRightBracket.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                process = answer.getText().toString();

                for(int i=0;i<(LBracket-RBracket);i++)
                {
                    history.add(")");
                    process+=")";
                }

                return true;
            }
        });

        Functions.operation(btnX,'×');
        Functions.operation(btnAdd,'+');
        Functions.operation(btnDiv,'/');
        Functions.operation(btnMinus,'-');
        Functions.negate(btnNegate,getApplicationContext());



        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();

                if(!process.isEmpty()){

                   for(int i=0;i<LBracket-RBracket;i++){
                        history.add(")");
                        process+=")";
                        //RBracket++;
                        hist.setText(process);
                    }

                    Toast.makeText(getApplicationContext(),String.valueOf(history),Toast.LENGTH_SHORT).show();

                    process = process.replace("×","*");
                    process = process.replace("%","/100");
                    process = process.replace(",",".");


                    Context rhino = Context.enter();
                    rhino.setOptimizationLevel(-1);

                    String finalResult;

                    Scriptable script = rhino.initStandardObjects();
                    finalResult = rhino.evaluateString(script,process,"Evale",1,null).toString();

                    Context.exit();

                    double ans = Double.parseDouble(finalResult);

                    DecimalFormat format = new DecimalFormat("0.##########");

                    double a=ans;

                    int b = (int)a;
                    String t = (format.format(a));
                    t = t.replace(".",",");
                    if(a-b==0)answer.setText(t);
                    else answer.setText(t);

                    process=t;
                    answer.setHint(t);
                    history.clear();
                    LBracket=0;RBracket=0;
                }
            }
        });



    }
}
