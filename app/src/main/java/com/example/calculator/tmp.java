package com.example.calculator;

public class tmp {

       /*public static String changePow(final Context cont, String pr) {

        String p;

        p=getProcess();

        p = p.replace(",", ".");
        int l = 0;
        int l2 = 0;
        String tLeft = "";
        String tRight = "";
        String w;
        String pOut;
        if (p.lastIndexOf("^") != -1) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == '^' ) {
                    String a = p.substring(0, i);
                    int max = 0;
                    for (Character character : Lista) {
                        if (max <= a.lastIndexOf(character)) {
                            max = a.lastIndexOf(character);
                        }
                    }

                *//*for (int y = i + 1; y < p.length(); y++) {
                    if (isNumeric(String.valueOf(p.charAt(y)))) {

                    }else break;
                }*//*



     *//*try {
                        int y = i + 1;
                        while (Character.isDigit(p.charAt(y)) || p.charAt(y) == '.') {
                            tLeft += p.charAt(y);
                            l = y;
                            y++;
                            if (y > p.length() - 1) break;
                        }
                    } catch (Exception e) {
                        Toast.makeText(cont, "Err", Toast.LENGTH_SHORT).show();
                    }


                    try {
                        int y = i - 1;
                        while (Character.isDigit(p.charAt(y)) || p.charAt(y) == '.') {
                            tLoop += p.charAt(y);
                            l2 = y;
                            y--;
                            if (y < 0) break;

                        }
                    } catch (Exception e) {
                        Toast.makeText(cont, "Err", Toast.LENGTH_SHORT).show();
                    }*//*

                    for (int y = i - 1; y >= 0; y--) {
                        if (isNumeric(String.valueOf(p.charAt(y)))) {
                            tLeft+=p.charAt(y);
                            l2 = y;
                        }else break;
                    }

                    for (int y = i + 1; y <= p.length()-1; y++) {
                        if (isNumeric(String.valueOf(p.charAt(y)))) {
                            tRight+=p.charAt(y);
                            l = y;
                        } else break;
                    }

                    StringBuilder bs = new StringBuilder();
                    for(int x =tLeft.length()-1 ; x >= 0;x--){
                        bs.append(tLeft.charAt(x));
                    }
                    tLeft= String.valueOf(bs);
                    hist.setText(tLeft+":"+tRight);

                    try {
                        tRight = p.substring(l2, i);
                        //Toast.makeText(cont,tLoop, Toast.LENGTH_SHORT).show();
                        if ((max < l)) {
                            for (int x = l; x >= l2; x--) {
                                history.remove(x);

                            }

                            double b = Double.parseDouble(tRight);
                            double c = Double.parseDouble(tLeft);
                            double wynik = Math.pow(b, c);
                            DecimalFormat f = new DecimalFormat("0.#####");
                            w = f.format(wynik);
                        *//*for(i = w.length()-1; i>=0; i--){
                            history.add(l2,String.valueOf(w.charAt(i)));
                        }*//*
                            history.add(l2, w);

                            //Toast.makeText(cont, String.valueOf(history), Toast.LENGTH_SHORT).show();
                            //history.add(l2, w);

                            pOut = "";
                            for (String str : history
                            ) {
                                pOut += str;
                            }

                            setProcess(pOut);
                        }
                    } catch (Exception e) {
                        Toast.makeText(cont, "Error pow", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
        return null;
    }*/

    /*public static void changePercent(final Context cont){

        org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
        rhino.setOptimizationLevel(-1);
        Scriptable script = rhino.initStandardObjects();

        process=getProcess();

        process = process.replace("×", "*");
        String tmp;
        if (process.lastIndexOf("%") != -1) {           //3*(3+5*6)+5%
            try {
                for (int i = 0; i < process.length(); i++) {
                    if (process.charAt(i) == '%') {

                        if(i!=process.length()-1 && Character.isDigit(process.charAt(i+1))){
                            //Toast.makeText(cont, "Modulo", Toast.LENGTH_SHORT).show();
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
                                int b = 0;
                                if(tmp.lastIndexOf("(")>tmp.lastIndexOf(")")){
                                    b=process.lastIndexOf("(");
                                    b+=1;
                                }
                                            *//*if(tmp.lastIndexOf(")")!=-1){
                                                tmp=process.substring(b,tmp.lastIndexOf(")"));
                                            }else {*//*
                                tmp = process.substring(b, max);
                                //Toast.makeText(cont, "XD :"+tmp, Toast.LENGTH_SHORT).show();
                                tmp = Functions.changePow(cont,tmp);
                                Toast.makeText(cont, tmp, Toast.LENGTH_SHORT).show();
                                //String eval = rhino.evaluateString(script,tmp,"eval",1,null).toString();
                                history.set(i, "/100*"+tmp+")");
                                history.add(max+1,"(");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                //Toast.makeText(cont, "% ERROR %", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

}
