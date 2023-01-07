package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String input="",Answer,allOperations="";
    private boolean clearResult;
    private Handler handler;
    TextView output,sideDisplay;
    Button btnA, btnExp,btnDel,btnClear;
    Button btnDiv,btnMul,btnMin,btnPlus,btnDec,btnEquals;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    Button btnHistory,btnArray;

    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output=findViewById(R.id.output);
        sideDisplay=findViewById(R.id.sideDisplay);

        assignId(btnA,R.id.bttnOpenSquare);
        assignId(btnExp,R.id.bttnDecimal);
        assignId(btnDel,R.id.bttnComma);
        assignId(btnClear,R.id.bttnArray);
        assignId(btnDiv,R.id.bttnCloseSquare);
        assignId(btnMul,R.id.bttnMul);
        assignId(btnMin,R.id.btnMin);
        assignId(btnPlus,R.id.bttnPlus);
        assignId(btnDec,R.id.bttnDel);
        assignId(btnEquals,R.id.btnEquals);
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

        assignId(btnHistory,R.id.btnHistory);
        assignId(btnArray,R.id.btnArray);
        addItemstoList(list);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("Id","Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }

    void assignId(Button btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    void addItemstoList(ArrayList<String> a){
        for (int i = 1; i < 10; i++) {
            a.add(String.valueOf(i));
        }
        a.add("+");
        a.add("*");
        a.add("/");
        a.add("-");
        a.add(".");
        a.add("^");
        Log.d("yetag", String.valueOf(a));
    }

    public void onClick(View view){
        Button button= (Button) view;
        String data=button.getText().toString();
        output.setText(data);

        if(list.contains(data)){
            allOperations=allOperations+data;
        }

        switch (data){
            case "AC":
                input="";
                sideDisplay.setText("");
                break;
            case "A":
                clearResult=false;
                input+=Answer;
                break;
            case "^":
//                clearResult=false;
                Solve();
                input+="^";
                break;
            case "=":
                clearResult=true;
                DatabaseHelper databaseHelper=new DatabaseHelper(MainActivity.this);
                databaseHelper.add(allOperations);
//                Toast.makeText(MainActivity.this,"hehe",Toast.LENGTH_SHORT).show();

                Solve();
                Answer=input;
                allOperations="";

                Intent serviceIntent = new Intent(this, NotificationHelper.class);
                startService(serviceIntent);

                break;
            case " ":
                if(input.length()>0){
                    clearResult=false;
                    String newText=input.substring(0,input.length()-1);
                    input=newText;
                }
                break;
            case "history":
                DatabaseHelper databaseHelper1=new DatabaseHelper(MainActivity.this);
                List<String> history=databaseHelper1.getHistory();
//                Toast.makeText(MainActivity.this,history.toString(),Toast.LENGTH_SHORT).show();
                openHistory();
                break;
            case "Array":
                openArray();
                break;
            default:
                if(input==null){
                    input="";
                }
                if(data.equals("+") || data.equals("-") || data.equals("/") || data.equals("*")){
                    clearResult=false;

                    Solve();
                    Log.d("yetag", input);
                }
                else if(clearResult==true){
                    input="";
                    clearResult=false;
                }
                input+=data;
        }
        output.setText(input);
        DatabaseHelper dh = new DatabaseHelper(MainActivity.this);
        sideDisplay.setText(dh.displayMostRecent());

    }
    public void Solve(){
        if(input.split("\\*").length==2){
            String numbers[]=input.split("\\*");
            try{
                double mul=Double.parseDouble(numbers[0])*Double.parseDouble(numbers[1]);
                float finalMul=(float)mul;
                input=finalMul+"";
            }
            catch (Exception e){
                input="Error";
            }
        }
        else if (input.split("\\*").length>=2){
            input="Syntax Error";
        }
        else if(input.split("/").length==2){
            String numbers[]=input.split("/");
            try{
                double div=Double.parseDouble(numbers[0])/Double.parseDouble(numbers[1]);
                float finalDiv=(float)div;
                input=finalDiv+"";
            }
            catch (Exception e){
                input="Error";
            }
        }
        else if (input.split("/").length>=2){
            input="Syntax Error";
        }
        else if(input.split("\\^").length==2){
            String numbers[]=input.split("\\^");
            try{
                double pow=Math.pow(Double.parseDouble(numbers[0]),Double.parseDouble(numbers[1]));
                float finalPow=(float)pow;
                input=finalPow+"";
            }
            catch (Exception e){
                //Display error
                input="Error";
            }
        }
        else if (input.split("\\^").length>=2){
            input="Syntax Error";
        }
        else if(input.split("\\+").length==2){
            String numbers[]=input.split("\\+");
            try{
                double sum=Double.parseDouble(numbers[0])+Double.parseDouble(numbers[1]);
                input=sum+"";
            }
            catch (Exception e){
                //Display error
                input="Error";
            }
        }
        else if (input.split("\\+").length>=2){
            input="Syntax Error";
        }
        else if(input.split("\\-").length>1){
            String numbers[]=input.split("\\-");
            if(numbers[0]=="" && numbers.length==2){
                numbers[0]=0+"";
            }
            try{
                double sub=0;
                if(numbers.length==2) {
                    sub = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                }
                else if(numbers.length==3){
                    sub = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                input=sub+"";
            }
            catch (Exception e){
                //Display error
                input="Error";
            }
        }
        else if (input.split("\\-").length>=2){
            input="Syntax Error";
        }
        String n[]=input.split("\\.");
        if(n.length>1){
            if(n[1].equals("0")){
                input=n[0];
            }
        }
        output.setText(input);
    }

    public void openHistory(){
        Intent intent=new Intent(this,HistoryPage.class);
        startActivity(intent);
    }

    public void openArray(){
        Intent intent=new Intent(this,ArrayMultiplication.class);
        startActivity(intent);
    }

    public void notifyFunction(String s){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this,"Id");
        builder.setContentTitle("Notifcation");
        builder.setContentText("You received a Notification");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);



        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MainActivity.this);
        managerCompat.notify(1,builder.build());

    }
}
