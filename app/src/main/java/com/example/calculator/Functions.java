package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

                if(temp.length()!=0){
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


                if(answer.length()!=0 && process.charAt(process.length()-1)!=c && process.charAt(process.length()-1)!='%') {
                    if(!Character.isDigit(process.charAt(process.length() - 1)) && process.charAt(process.length()-1)!='(' && process.charAt(process.length()-1)!=')') {
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

                char c = ',';

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

    public static void LBracket(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
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

    public static void showHelp(ImageView btn, final Context cont){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*                //Toast.makeText(getApplicationContext(),answer.getText(),Toast.LENGTH_SHORT).show();
                 */
     /*           if(history.size()==0)
                    Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),String.valueOf(history)+" LB: "+String.valueOf(LBracket)+" RB: "+String.valueOf(RBracket), Toast.LENGTH_SHORT).show();

*/
                Toast.makeText(cont,String.valueOf(history)+" : "+String.valueOf(history.size())+" : "+process.lastIndexOf("%"), Toast.LENGTH_SHORT).show();

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
    }
}
