package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
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
                isSqrt=false;
                isFact=false;


                return true;
            }
        });

    }

    public static void del(Button btn, final Context cont){

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    String tmp = answer.getText().toString();

                    if(!tmp.isEmpty() && !history.isEmpty())
                    {
                        String subC;
                        if(tmp.charAt(tmp.length()-1)==')')RBracket--;
                        else if(tmp.charAt(tmp.length()-1)=='(')LBracket--;
                        if(history.get(history.size() - 1).equals("log10(")){
                            process = process.substring(0,process.length()-6);
                            subC = answer.getText().toString().substring(0,answer.length()-6);
                        }if(history.get(history.size() - 1).equals("sqrt(")){
                            process = process.substring(0,process.length()-5);
                            subC = answer.getText().toString().substring(0,answer.length()-5);
                        }else {
                            process = process.substring(0, process.length() - 1);
                            subC = answer.getText().toString().substring(0,answer.length()-1);
                        }
                        hist.setText(process);
                        answer.setText(subC);
                        if(!history.isEmpty()) history.remove(history.size()-1);

                    }

                } catch (Exception e) {
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

                if(isFact){
                    history.add(")");
                    process+=")";
                    isFact=false;
                }

                if(isAnswer){
                    for(int i = 0 ; i < ans1.length();i++){
                        history.add(i, String.valueOf(ans1.charAt(i)));
                    }
                    isAnswer=false;
                }

                if(answer.length()!=0 && process.charAt(process.length()-1)!=c ) {
                    if(!Character.isDigit(process.charAt(process.length() - 1)) && process.charAt(process.length()-1)!='(' && process.charAt(process.length()-1)!=')'&& process.charAt(process.length()-1)!='%' && process.charAt(process.length()-1)!='!'&& process.charAt(process.length()-1)!='π' ) {
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

                    if (answer.length() != 0 && !isOperation(process.charAt(process.length() - 1)) && process.charAt(process.length() - 1) != '%') {
                        process += "^" + p;
                        history.add("^");
                        history.add(p);
                        answer.setText(process);
                        hist.setText(process);
                    }
                }
            });
        }
        catch(Exception e){
        }
    }

    public static void factorial(Button btn) {
        try {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String tmp = answer.getText().toString();

                    if (!tmp.isEmpty() && !history.isEmpty() && Character.isDigit(process.charAt(process.length() - 1)) || process.charAt(process.length()-1)==')') {
                        int max = 0;
                        for (Character character : Lista) {
                            if (max <= process.lastIndexOf(character)) {
                                max = process.lastIndexOf(character);
                            }
                        }
                        String t=process.substring(max,process.length()-1);
                        if(t.lastIndexOf(".")==-1){
                            history.add("!");
                            process += "!";
                            hist.setText(process);
                            answer.setText(process);
                        }
                    }
                }
            });
        } catch (Exception e) {
        }

    }

    public static void log10(Button btn) {
        try {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int max = 0;
                    for (Character character : Lista) {
                        if (max <= process.lastIndexOf(character)) {
                            max = process.lastIndexOf(character);
                        }
                    }
                    String t = process;
                    if(!process.isEmpty())
                        t=process.substring(max,process.length()-1);

                    if(t.lastIndexOf(".")==-1 && !isFact){
                        history.add("log10(");
                        process += "log10(";
                        LBracket++;
                        isFact=true;
                        hist.setText(process);
                        answer.setText(process);
                    }

                }
            });
        } catch (Exception ignored) {
        }

    }
    public static void sqrt(Button btn) {
        try {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int max = 0;
                    for (Character character : Lista) {
                        if (max <= process.lastIndexOf(character)) {
                            max = process.lastIndexOf(character);
                        }
                    }
                    String t = process;
                    if(!process.isEmpty())
                        t=process.substring(max,process.length()-1);

                    if(t.lastIndexOf(".")==-1 && !isFact){
                        history.add("sqrt(");
                        process += "sqrt(";
                        LBracket++;
                        isSqrt=true;
                        hist.setText(process);
                        answer.setText(process);
                    }

                }
            });
        } catch (Exception ignored) {
        }

    }

    public static void pi(Button btn, final Context cont) {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isAnswer) {
                    history.clear();
                    answer.setText("");
                    hist.setText("");
                    process = "";
                    isAnswer = false;
                    isFact=false;
                }

                int max = 0;
                for (Character character : Lista) {
                    if (max <= process.lastIndexOf(character)) {
                        max = process.lastIndexOf(character);
                    }
                }
                try {
                    boolean ifDot=false;
                    boolean ifDigit=false;
                    int d = 0;
                    if(!process.isEmpty()){
                        if(process.charAt(process.length()-1)=='.'){
                            ifDot=true;
                        }
                    }
                    if(!process.isEmpty()){
                        if(Character.isDigit(process.charAt(process.length()-1))){
                            ifDigit=true;
                        }
                    }


                    if(!ifDot && !ifDigit){

                        history.add("π");
                        process += "π";
                        hist.setText(process);
                        answer.setText(process);
                    }else {
                        history.add("*");
                        history.add("π");
                        process += "π";
                        hist.setText(process);
                        answer.setText(process);
                    }


                } catch (Exception e) {
                }
            }
        });

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
                if(isFact)isFact=false;
                if(isSqrt)isSqrt=false;
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

    public static void changePowAndPercent(final Context cont){
        org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
        rhino.setOptimizationLevel(-1);
        Scriptable script = rhino.initStandardObjects();

        String p=getProcess();

        String pOut="";
        String tmp;
        p = p.replace(",", ".");
        int rRight = 0;
        int rLeft = 0;
        String tRight = "";
        String tLeft="";

        if(p.lastIndexOf("%") != -1 || p.lastIndexOf("^") != -1 || p.lastIndexOf("!") != -1 || p.lastIndexOf("π") != -1 || history.lastIndexOf("log10(")!=-1 || history.lastIndexOf("sqrt(")!=-1) {
            try {
                for (int i = 0; i < p.length(); i++) {
                    //if((p.lastIndexOf("%") != -1 || p.lastIndexOf("^") != -1 || p.lastIndexOf("!") != -1 || p.lastIndexOf("π") != -1 || history.lastIndexOf("log10(")!=-1 || history.lastIndexOf("sqrt(")!=-1))break;
                    if (p.charAt(i) == '%') {
                        if (i != p.length() - 1 && Character.isDigit(p.charAt(i + 1))) {
                        } else {
                            tmp = p.substring(0, i);
                            int max = 0;
                            String a = tmp;
                            for (Character character : Lista) {
                                if (max <= a.lastIndexOf(character)) {
                                    max = a.lastIndexOf(character);
                                }
                            }
                            boolean ifDiv=false;
                            if(i != history.size() - 1){
                                if(history.get(i+1).equals("*")){
                                    ifDiv = true;
                                }
                            }
                            if (!history.get(max).equals("+") || history.get(max).equals("-") || ifDiv) {
                                //String tW = String.valueOf(process.charAt(i-1))+"/100";
                                history.remove(i);
                                history.add(i,"0");
                                history.add(i,"0");
                                history.add(i,"1");
                                history.add(i,"/");
                            } else {
                                int b = 0;
                                Toast.makeText(cont, tmp, Toast.LENGTH_SHORT).show();
                                if (tmp.lastIndexOf("(") > tmp.lastIndexOf(")")) {
                                    b = p.lastIndexOf("(");
                                    tmp = p.substring(b+1, max);
                                }else if(tmp.lastIndexOf("(") < tmp.lastIndexOf(")")){
                                    tmp = p.substring(tmp.lastIndexOf("("),tmp.lastIndexOf(")"));
                                }else{
                                    tmp = p.substring(b, max);
                                }

                                String eval = rhino.evaluateString(script, tmp, "eval", 1, null).toString();
                                String tWp= rhino.evaluateString(script,"("+(p.charAt(i-1)+"/100*"+eval+")"),"tWEval",1,null).toString();
                                String tW = "";

                                hist.setText(tW);
                                history.remove(i);
                                history.remove(i-1);        //Dodac petle
                                for(int x=tWp.length()-1;x>=0;x--){
                                    history.add(i-1,String.valueOf(tWp.charAt(x)));
                                }
                            }
                        }

                        pOut="";
                        for (String str : history
                        ) {
                            pOut += str;
                        }

                        setTxt(pOut);

                        p = getTxt();
                        answer.setText(p);

                    }else if(p.charAt(i) == '^' ){
                        String a = p.substring(0, i);
                        int max = 0;
                        for (Character character : Lista) {
                            if (max <= a.lastIndexOf(character)) {
                                max = a.lastIndexOf(character);
                            }
                        }
                        tRight="";
                        for (int y = i + 1; y < p.length(); y++) {
                            if (Character.isDigit(p.charAt(y))) {
                                tRight += String.valueOf(p.charAt(y));
                                rRight = y;
                            } else
                                break;
                        }
                        if(p.charAt(i-1)==')'){
                            Toast.makeText(cont, "2", Toast.LENGTH_SHORT).show();
                            String w = a.substring(a.lastIndexOf("(",a.lastIndexOf(")")));
                            tLeft=rhino.evaluateString(script,w,"tEval",1,null).toString();
                            rLeft=a.lastIndexOf("(");
                        }
                        else {
                            Toast.makeText(cont, "2", Toast.LENGTH_SHORT).show();
                            tLeft="";
                            /*for (int y =0; y <i; y++) {
                                if (Character.isDigit(p.charAt(y)) || p.charAt(y) == '.') {
                                    tLeft += String.valueOf(p.charAt(y));
                                    rLeft = i-y-1;
                                } else
                                    break;
                            }*/
                            for (int y =i-1; y >=0; y++) {
                                if (Character.isDigit(p.charAt(y)) || p.charAt(y) == '.') {
                                    tLeft += String.valueOf(p.charAt(y));
                                    rLeft = y;
                                } else
                                    break;
                            }
                            Toast.makeText(cont, "rLeft : "+rLeft, Toast.LENGTH_SHORT).show();
                        }

                        try {
                            if ((max < rRight)) {
                                for (int x = rRight; x >= rLeft; x--) {
                                    history.remove(x);

                                }
                                double b = Double.parseDouble(tLeft);
                                double c = Double.parseDouble(tRight);
                                double wynik = Math.pow(b, c);
                                DecimalFormat f = new DecimalFormat("0.#####");
                                String w = f.format(wynik);

                                //history.add(rLeft, w);

                                for(int x=w.length()-1;x>=0;x--){
                                    history.add(rLeft,String.valueOf(w.charAt(x)));
                                }

                                pOut="";
                                for (String str : history
                                ) {
                                    pOut += str;
                                }

                                setTmpProcess(pOut);
                                p=getTmpProcess();
                                answer.setText(p);
                            }
                        } catch (Exception e) {
                            Toast.makeText(cont, "Error pow", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (p.charAt(i) == '!') {
                        String a = p.substring(0, i);
                        int max = 0;
                        for (Character character : Lista) {
                            if (max <= a.lastIndexOf(character)) {
                                max = a.lastIndexOf(character);
                            }
                        }

                        //a=p.substring(max,i);

                        if(p.charAt(i-1)==')'){
                            String w = a.substring(a.lastIndexOf("("),a.lastIndexOf(")"));
                            tLeft=rhino.evaluateString(script,w,"tEval",1,null).toString();
                            rLeft=a.lastIndexOf("(");
                            Toast.makeText(cont, "1", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(cont, "2", Toast.LENGTH_SHORT).show();
                            for (int y = i - 1; y >= 0; y--) {
                                if (Character.isDigit(p.charAt(y))) {
                                    tLeft = String.valueOf(p.charAt(y));
                                    rLeft = y;
                                } else
                                    break;
                            }
                        }
                        a = tLeft;
                        int t = 1;
                        int zm = Integer.parseInt(a);
                        for(int x = 1; x<= zm; x++){
                            t=t*x;
                        }

                        String w = Integer.toString(t);

                        for(int y=rLeft;y<=i;y++) {
                            history.remove(rLeft);
                        }

                        try {
                            for (int z = w.length() - 1; z >= 0; z--) {
                                history.add(rLeft, String.valueOf(w.charAt(z)));
                            }
                        } catch (Exception e) {
                            Toast.makeText(cont, "Error Fact", Toast.LENGTH_LONG).show();
                        }
                    }else if(history.get(i).equals("π")){
                        try{
                            //Toast.makeText(cont, "i: "+i, Toast.LENGTH_SHORT).show();
                            if(Character.isDigit(p.charAt(i+1))) {
                                history.add(i + 1, "*");
                            }else if(!history.isEmpty()){
                                if(Character.isDigit(p.charAt(i-1))){
                                    Toast.makeText(cont, "YeS", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {
                        }

                        history.remove(i);

                        double pi = 3.14159265359;
                        DecimalFormat format = new DecimalFormat("0.####");
                        String ans = (format.format(pi));

                        for(int d = ans.length()-1;d>=0;d--) {
                            history.add(i, String.valueOf(ans.charAt(d)));
                        }

                        pOut="";
                        for (String str : history
                        ) {
                            pOut += str;
                        }

                        setTmpProcess(pOut);
                        p=getTmpProcess();
                        answer.setText(p);


                    } else if(history.get(i).equals("log10(")){

                    history.set(i,"Math.log10(");

                    pOut="";
                    for (String str : history
                    ) {
                        pOut += str;
                    }

                    setTmpProcess(pOut);
                    p=getTmpProcess();
                    answer.setText(p);

                    } else if(history.get(i).equals("sqrt(")){


                        history.set(i,"Math.sqrt(");

                        pOut="";
                        for (String str : history
                        ) {
                            pOut += str;
                        }

                        setTmpProcess(pOut);
                        p=getTmpProcess();
                        answer.setText(p);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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
                        RBracket++;
                        hist.setText(process);
                    }

                    StringBuilder tmp2 = new StringBuilder();
                    for (int i = 0; i < history.size(); i++) {
                        tmp2.append(history.get(i));
                    }
                    setProcess(String.valueOf(tmp2));
                    /*Functions.changePow(cont);
                    Functions.changePercent(cont);*/
                    Functions.changePowAndPercent(cont);
                    process = process.replaceAll(",",".");
                    try {
                        StringBuilder tmp3 = new StringBuilder();
                        for (int i = 0; i < history.size(); i++) {
                            tmp3.append(history.get(i));
                        }

                            for (int i = 0; i < LBracket - RBracket; i++) {
                            history.add(")");
                            process += ")";
                            RBracket++;
                            hist.setText(process);
                        }

                        process = tmp3.toString();
                        process = process.replaceAll("×", "*");
                        process = process.replaceAll(",",".");

                        finalResult = rhino.evaluateString(script, process, "Eval", 1, null).toString();

                        org.mozilla.javascript.Context.exit();

                        double ans = Double.parseDouble(finalResult);

                        String t = (format.format(ans));
                        t = t.replace(",", ".");
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
                        Toast.makeText(cont, "Error At final", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
