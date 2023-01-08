package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayMultiplication extends AppCompatActivity implements View.OnClickListener{

    TextView output,ansDisplay;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    Button btnOpenSquare, btnCloseSquare,btnDel,btnDec,btnComma,btnCross;
    Button btnClear,btnEquals;
    private String input="",result="";
    Boolean clearResult=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_multiplication);

        output=findViewById(R.id.arrayText);
        ansDisplay=findViewById(R.id.arrayAnswerText);

        assignId(btn0,R.id.bttn0);
        assignId(btn1,R.id.bttn1);
        assignId(btn2,R.id.bttn2);
        assignId(btn3,R.id.bttn3);
        assignId(btn4,R.id.bttn4);
        assignId(btn5,R.id.bttn5);
        assignId(btn6,R.id.bttn6);
        assignId(btn7,R.id.bttn7);
        assignId(btn8,R.id.bttn8);
        assignId(btn9,R.id.bttn9);

        assignId(btnOpenSquare,R.id.bttnOpenSquare);
        assignId(btnCloseSquare,R.id.bttnCloseSquare);
        assignId(btnDel,R.id.bttnDel);
        assignId(btnDec,R.id.bttnDecimal);
        assignId(btnComma,R.id.bttnComma);
        assignId(btnCross,R.id.bttnArray);

        assignId(btnClear,R.id.bttnClear);
        assignId(btnEquals,R.id.bttnEquals);

    }

    public void onClick(View view){
        Button button= (Button) view;
        String data=button.getText().toString();
        output.setText(input);
        switch (data){
            case "=":
//                arraySolve();
                arrayMultiplyier(input);
                clearResult=true;
                break;
            case "d":
                String newText=input.substring(0,input.length()-1);
                input=newText;
                break;
            case "AC":
                input="";
                break;
            default:
                if(clearResult){
                    input="";
                    clearResult=false;
                }
                input+=data;
        }
//        Log.d("array",input+"before finaally printing");
        output.setText(input);
    }

    public void arraySolve(){
        try{
            String arrays[]=input.split("X");
            String a1=arrays[0];
            String a2=arrays[1];
//        Log.d("array",String.valueOf(arrays.length));
//        Log.d("array",String.valueOf(syntaxCheck(a1)));
//        Log.d("array",String.valueOf(syntaxCheck(a2)));
            if(arrays.length==2 ){
                try{
                    a1=truncate(arrays[0]);
                    a2=truncate(arrays[1]);
                    String numbers1[]=a1.split(",");
                    String numbers2[]=a2.split(",");
                    double answer1=Double.parseDouble(numbers1[0])*Double.parseDouble(numbers2[0]);
                    double answer2=Double.parseDouble(numbers1[1])*Double.parseDouble(numbers2[1]);
                    result="[ "+String.valueOf(answer1)+" , "+String.valueOf(answer2)+" ]";
                    ansDisplay.setText(result);
                }
                catch (Exception e){
                    result="Syntax Error";
                    ansDisplay.setText(result);
                    input="";  //to clear input screen after incorrect syntax
//                Log.d("array",input+"At exception block");
                }
            }
            else{
                input="error";
            }
        }
        catch (Exception e){
            result="Enter two arrays";
            ansDisplay.setText(result);
            input="";  //to clear input screen after incorrect syntax
        }
    }

    public void arrayMultiplyier(String s){
        try {
            Boolean stringEndsWithX=s.endsWith("X");
            String arrays[]=s.split("X");

            ArrayList<Double> ansArray= new ArrayList();
            ArrayList<Double> array1= new ArrayList();
            ArrayList<Double> array2= new ArrayList();
            ArrayList<String> answerDisplayArrayList = new ArrayList<String>();


            array1=stringArrayToDoubleArray(arrays[0]);
            array2=stringArrayToDoubleArray(arrays[1]);

            Log.d("thistag2","Array1XArray2  "+array1.toString()+" "+array2.toString());

            Log.d("thistag", Arrays.toString(arrays));
            if(arrays.length==2 & !stringEndsWithX & array1.size()==array2.size()){


                Log.d("thistag","In the length==2 block "+arrays.toString());
                for(int i=0;i<array1.size();i++){
//                    if(Character.isDefined(arrays[0].charAt(i)) & Character.isDigit(arrays[1].charAt(i))){
//                    double mul2=(double) Character.getNumericValue(arrays[1].charAt(i));
//                    double mul1=(double) Character.getNumericValue(arrays[0].charAt(i));
//                    ansArray.add(mul1*mul2);
//                    }
                    Log.d("thistag","Array1XArray2  "+String.valueOf(array1.get(i)*array2.get(i)));
                    ansArray.add(array1.get(i)*array2.get(i));
                }
//                ansDisplay.setText(ansArray.toString());
                for(int i=0;i<ansArray.size();i++){
                    if (ansArray.get(i) == Math.round(ansArray.get(i))) {
                        answerDisplayArrayList.add(String.valueOf(ansArray.get(i).intValue()));
                    } else {
                        answerDisplayArrayList.add(String.valueOf(ansArray.get(i)));
                    }
                }
                ansDisplay.setText(String.valueOf(answerDisplayArrayList));
            }
//            Log.d("thistag", String.valueOf(ansArray.isEmpty()));
            Log.d("notag", "Does the string end with X?"+String.valueOf(stringEndsWithX));
            if(ansArray.isEmpty()){
                if (stringEndsWithX){
                    ansDisplay.setText("Syntax Error");
                }
                else if(array1.size()!=array2.size()){
                    ansDisplay.setText(("Arrays must be of the same size"));
                }
                if(arrays.length!=2){
                    ansDisplay.setText("Cannot Multiply more than 2 arrays");
                }
            }
        }
        catch (Exception e){
            ansDisplay.setText("Syntax Error");
//            ansDisplay.setText(e.getMessage());
            e.printStackTrace();
            Log.d("Midsemsover",e.getMessage());
        }
    }

    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public static String truncate(String str) {
        if (str.length() <= 2) {
            return "";
        }
        return str.substring(1, str.length() - 1);
    }

    public Boolean arraySizeCheck(String a,String b){
        int sizeA=a.length(),sizeB=b.length();
        if(sizeA==sizeB){
            return true;
        }
        return false;
    }

    public ArrayList<Double> stringArrayToDoubleArray(String s){

        ArrayList<Double> returnList = new ArrayList<Double>();

        s=truncate(s);
        String[] numbers = s.split(",");

        for (int i = 0; i < numbers.length; i++)
        {
            returnList.add(Double.parseDouble(numbers[i]));
        }
        Log.d("Midsemsover",returnList.toString());
        return returnList;
    }

//    public  Boolean syntaxCheck(String s) {
//        String check=s.charAt(0) + "a" + s.charAt(s.length() - 1);
//        Log.d("array","check"+check);
//        if (check=="[a]") {
//            return true;
//        }
//        else{
////            input="Invalid Syntax";
//            return false;
//        }
//    }
}