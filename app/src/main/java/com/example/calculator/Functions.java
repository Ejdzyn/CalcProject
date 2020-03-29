package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;

@SuppressLint("Registered")
public class Functions extends MainActivity {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isOperation(char str){
        return str=='+' ||  str=='-' ||  str=='*' ||  str=='/' ||  str=='×';
    }

    public static void num0(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = answer.getText().toString();

                if(temp.length()!=0 && !history.get(history.size() - 1).equals("/")){
                    process = answer.getText().toString();
                    process +="0";
                    answer.setText(process);
                    hist.setText(process);
                    history.add("0");
                }
            }
        });
    }

    public static void clear(Button btn){

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                answer.setText("");
                hist.setText("");
                process="";
                history.clear();
                LBracket=0;
                RBracket=0;
                isAnswer=false;


                return true;
            }
        });

    }

    public static void del(Button btn){

        btn.setOnClickListener(new View.OnClickListener() {
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

    }

    public static void numb(Button btn,  final char c)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isAnswer){
                    history.clear();
                    answer.setText("");
                    hist.setText("");
                    process="";
                    isAnswer=false;
                }

                process = answer.getText().toString();
                process +=c;
                answer.setText(process);
                hist.setText(process);
                history.add(String.valueOf(c));
            }
        });
    }

    public static void operation(Button btn, final char c)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAnswer){
                    for(int i = 0 ; i < ans1.length();i++){
                        history.add(i, String.valueOf(ans1.charAt(i)));
                    }
                    isAnswer=false;
                }

                if(answer.length()!=0 && process.charAt(process.length()-1)!=c ) {
                    if(!Character.isDigit(process.charAt(process.length() - 1)) && process.charAt(process.length()-1)!='(' && process.charAt(process.length()-1)!=')'&& process.charAt(process.length()-1)!='%') {
                        history.set(history.size()-1,String.valueOf(c));
                        process = process.substring(0, process.length() - 1);
                        process+=c;
                        answer.setText(process);
                        hist.setText(process);
                    }
                    else{
                        process += c;
                        answer.setText(process);
                        hist.setText(process);
                        history.add(String.valueOf(c));
                    }
                }
                dots++;
            }
        });

    }

    public static void dot(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                char c = '.';

                String a = process;
                int max =0;
                for (Character character : Lista) {
                    if (max <= a.lastIndexOf(character)) {
                        max = a.lastIndexOf(character);
                    }
                }

                if(isAnswer){
                    history.clear();
                    answer.setText("");
                    hist.setText("");
                    process="";
                    isAnswer=false;
                }

                String t;

                t = process.substring(max);

                if(t.lastIndexOf(c)==-1){
                    if (!process.isEmpty() && Character.isDigit(process.charAt(process.length()-1))) {
                        if (process.charAt(process.length() - 1) != c) {
                            process += c;
                            answer.setText(process);
                            hist.setText(process);
                            history.add(String.valueOf(c));
                        }
                    }
                    else{
                        process += "0"+c;
                        answer.setText(process);
                        hist.setText(process);
                        history.add("0");
                        history.add(String.valueOf(c));
                    }
                }
            }
        });
    }

    public static void negate(Button btn)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = process;
                StringBuilder tmp = new StringBuilder();
                int max =0;
                for (Character character : Lista) {
                    if (max <= a.lastIndexOf(character)) {
                        max = a.lastIndexOf(character);
                    }
                }
                if(max !=0){
                    if(history.get(max - 1).equals("(")){
                        history.remove(max);
                        history.remove(max-1);
                        LBracket-- ;
                    }else{
                        history.add(max+1,"(");
                        history.add(max+2,"-");
                        LBracket++ ;
                    }
                }
                else {
                    history.add(0,"(");
                    history.add(1,"-");
                    LBracket++ ;
                }



                for(int i =0 ;i<history.size();i++){
                    tmp.append(history.get(i));
                }
                process = tmp.toString();
                answer.setText(process);
                hist.setText(process);

            }
        });
    }

    public static void percent(Button btn){

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answer.length()!=0 && !isOperation(process.charAt(process.length()-1)) && process.charAt(process.length()-1)!='%'){
                    process+="%";
                    history.add("%");
                    answer.setText(process);
                    hist.setText(process);
                }
            }
        });
    }

    public static void pow(Button btn, final String p){
        try{
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answer.length()!=0 && !isOperation(process.charAt(process.length()-1)) && process.charAt(process.length()-1)!='%'){
                    process+="^"+p;
                    history.add("^");
                    history.add(p);
                    answer.setText(process);
                    hist.setText(process);
                }
            }
        });}
        catch(Exception e){
        }
    }

    public static void LBracket(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();

                if(isAnswer){
                    for(int i = 0 ; i < ans1.length();i++){
                        history.add(i, String.valueOf(ans1.charAt(i)));
                    }
                    isAnswer=false;
                }

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
    }
    public static void RBracket(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
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
    }

    public static void FillRBrackets(Button btn){
        btn.setOnLongClickListener(new View.OnLongClickListener() {
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
    }

    public static void changePow(final Context cont){

        String p=getProcess();
        p = p.replace(",", ".");
        int l = 0;
        int l2 = 0;
        String tLoop2 = "";
        String tLoop="";
        String pOut="";

        if (p.lastIndexOf("^") != -1) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == '^') {
                    String a = p.substring(0, i);
                    int max = 0;
                    for (Character character : Lista) {
                        if (max <= a.lastIndexOf(character)) {
                            max = a.lastIndexOf(character);
                        }
                    }

                    for (int y = i + 1; y < p.length(); y++) {
                        if (Character.isDigit(p.charAt(y))) {
                            tLoop2 = String.valueOf(p.charAt(y));
                            l = y;
                        } else
                            break;
                    }

                    for (int y = i - 1; y >= 0; y--) {
                        if (Character.isDigit(p.charAt(y))||p.charAt(y)=='.') {
                            tLoop = String.valueOf(p.charAt(y));
                            l2 = y;
                        } else
                            break;
                    }

                    try {
                        tLoop = p.substring(l2, i);
                        //Toast.makeText(cont,tLoop, Toast.LENGTH_SHORT).show();
                        if ((max < l)) {
                            for (int x = l; x >= l2; x--) {
                                history.remove(x);

                            }
                            double b = Double.parseDouble(tLoop);
                            double c = Double.parseDouble(tLoop2);
                            double wynik = Math.pow(b, c);
                            DecimalFormat f = new DecimalFormat("0.#####");
                            String w = f.format(wynik);

                            //history.add(l2, w);

                            for(int x=w.length()-1;x>=0;x--){
                                history.add(l2,String.valueOf(w.charAt(x)));
                            }


                            for (String str : history
                            ) {
                                pOut += str;
                            }

                            setTxt(pOut);

                            p=getTxt();
                        }
                    } catch (Exception e) {
                        //Toast.makeText(cont, "Error pow", Toast.LENGTH_SHORT).show();
                    }

                }       //END IF
            }       //END FOR
        }
    }

    public static void changePercent(final Context cont){

        org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
        rhino.setOptimizationLevel(-1);
        Scriptable script = rhino.initStandardObjects();

        String p=getTxt();

        Toast.makeText(cont, p, Toast.LENGTH_SHORT).show();

        String pOut="";

        p = p.replace("×", "*");
        String tmp;
        if (p.lastIndexOf("%") != -1) {           //3*(3+5*6)+5%
            try {
                for (int i = 0; i < p.length(); i++) {
                    if (p.charAt(i) == '%') {

                        if (i != p.length() - 1 && Character.isDigit(p.charAt(i + 1))) {
                            //Toast.makeText(cont, "Modulo", Toast.LENGTH_SHORT).show();
                        } else {
                            tmp = p.substring(0, i);
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
                                int b = 0;
                                if (tmp.lastIndexOf("(") > tmp.lastIndexOf(")")) {
                                    b = p.lastIndexOf("(");
                                    b += 1;
                                }
                                if (tmp.lastIndexOf(")") != -1) {
                                    tmp = p.substring(b, tmp.lastIndexOf(")"));
                                } else {
                                    tmp = p.substring(b, max);
                                    //Toast.makeText(cont, "XD :"+tmp, Toast.LENGTH_SHORT).show();

                                    Toast.makeText(cont, tmp, Toast.LENGTH_SHORT).show();
                                    String eval = rhino.evaluateString(script, tmp, "eval", 1, null).toString();
                                    history.set(i, "/100*" + eval + ")");
                                    history.add(max + 1, "(");
                                }
                            }
                        }

                        for (String str : history
                        ) {
                            pOut += str;
                        }

                        setTxt(pOut);

                        p = getTxt();
                    }
                }

            } catch (Exception e) {
                //Toast.makeText(cont, "% ERROR %", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void Equals(Button btn, final Context cont){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process = answer.getText().toString();
                process = process.replaceAll("×","*");
                process = process.replaceAll(",",".");

                if(!process.isEmpty() ) {

                    String finalResult;
                    org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
                    rhino.setOptimizationLevel(-1);
                    Scriptable script = rhino.initStandardObjects();
                    DecimalFormat format = new DecimalFormat("0.############");

                    for (int i = 0; i < LBracket - RBracket; i++) {
                        history.add(")");
                        process += ")";
                        hist.setText(process);
                    }

                    Functions.changePow(cont);
                    Functions.changePercent(cont);

                    try {
                        StringBuilder tmp3 = new StringBuilder();
                        for (int i = 0; i < history.size(); i++) {
                            tmp3.append(history.get(i));
                        }



                        process = tmp3.toString();
                        process = process.replaceAll("×", "*");
                        process = process.replaceAll(",",".");

                        finalResult = rhino.evaluateString(script, process, "Eval", 1, null).toString();

                        org.mozilla.javascript.Context.exit();

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
                        //Toast.makeText(cont, "Error At final", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static void showHelp(ImageView btn, final Context cont){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(cont, String.valueOf( history)+":"+process, Toast.LENGTH_LONG).show();
                //Toast.makeText(cont, process, Toast.LENGTH_SHORT).show();


            }
        });
    }
}
