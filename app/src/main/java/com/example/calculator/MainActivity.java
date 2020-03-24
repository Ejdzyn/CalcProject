package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    public static ArrayList<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
            Button btnShow = findViewById(R.id.btnShow);
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
                }

                return true;
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                //Toast.makeText(getApplicationContext(),answer.getText(),Toast.LENGTH_SHORT).show();

                *//*if(history.size()==0)
                    Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();*//*

                String xdd="";
                for(int i =0;i<history.size();i++){
                    xdd += history.get(i);


                }*/
/*                Toast.makeText(getApplicationContext(), process +" : "+process.length(), Toast.LENGTH_SHORT).show();
                hist.setText(process);*/

                if(process.isEmpty())
                    Toast.makeText(getApplicationContext(),"empty",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"not empty",Toast.LENGTH_SHORT).show();

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





        btnRightBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();
                process +=")";
                answer.setText(process);
                history.add(")");

            }
        });

        btnLeftBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();
                process +="(";
                answer.setText(process);
                history.add("(");

            }
        });

        Functions.operation(btnX,'*');
        Functions.operation(btnAdd,'+');
        Functions.operation(btnDiv,'/');
        Functions.operation(btnMinus,'-');
//        Functions.negate(btnNegate,getApplicationContext());







        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();

                if(!process.isEmpty()){
                    process = process.replaceAll("×","*");
                    process = process.replaceAll("%","/100");
                    process = process.replaceAll(",",".");

                    Context rhino = Context.enter();

                    rhino.setOptimizationLevel(-1);

                    String finalResult;

                    try {
                        Scriptable scriptable = rhino.initStandardObjects();
                        finalResult = rhino.evaluateString(scriptable,process,"javascript",1,null).toString();

                    }catch (Exception e){
                        finalResult="Złe dane";
                    }

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
                }
            }
        });



    }
}
