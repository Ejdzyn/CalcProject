package com.example.calculator;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class Functions extends MainActivity {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
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

    public static void numb(Button btn,  final char c)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                if(answer.length()!=0 && process.charAt(process.length()-1)!=c) {
                    if(!Character.isDigit(process.charAt(process.length() - 1)) && process.charAt(process.length()-1)=='(' && process.charAt(process.length()-1)==')' ) {
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

                String t;

                t = process.substring(max);

                if(t.lastIndexOf(".")==-1){
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
                        history.add(String.valueOf(c));
                    }
                }
            }
        });
    }



}
