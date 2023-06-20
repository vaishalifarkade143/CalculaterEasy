package com.example.calculatereasy;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView res_tv,enterdata_tv;
    MaterialButton btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9;
    MaterialButton btn_c,btn_open,btn_close;
    MaterialButton btn_div,btn_mul,btn_add,btn_minus,btn_equalto;
    MaterialButton btn_ac,btn_dot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res_tv = findViewById(R.id.res_tv);
        enterdata_tv = findViewById(R.id.enterdata_tv);

        assignIdToBtn(btn_c,R.id.btn_c);
        assignIdToBtn(btn_open,R.id.btn_open);
        assignIdToBtn(btn_close,R.id.btn_close);
        assignIdToBtn(btn_dot,R.id.btn_dot);
        assignIdToBtn(btn_ac,R.id.btn_ac);
        assignIdToBtn(btn_equalto,R.id.btn_equalto);
        assignIdToBtn(btn_div,R.id.btn_div);
        assignIdToBtn(btn_mul,R.id.btn_mul);
        assignIdToBtn(btn_add,R.id.btn_add);
        assignIdToBtn(btn_minus,R.id.btn_minus);


        assignIdToBtn(btn_0,R.id.btn_0);
        assignIdToBtn(btn_1,R.id.btn_1);
        assignIdToBtn(btn_2,R.id.btn_2);
        assignIdToBtn(btn_3,R.id.btn_3);
        assignIdToBtn(btn_4,R.id.btn_4);
        assignIdToBtn(btn_5,R.id.btn_5);
        assignIdToBtn(btn_6,R.id.btn_6);
        assignIdToBtn(btn_7,R.id.btn_7);
        assignIdToBtn(btn_8,R.id.btn_8);
        assignIdToBtn(btn_9,R.id.btn_9);


    }
        //setting onClicklistner on all buttong at a time to reduce code
    void assignIdToBtn(MaterialButton btn ,int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton but = (MaterialButton) view;
        String btu_text = (String) but.getText();
        String datatocal = enterdata_tv.getText().toString();

        if(btu_text.equals("AC"))
        {
            enterdata_tv.setText("");
            res_tv.setText("0");
            return;
        }

        if(btu_text.equals("="))
        {
            enterdata_tv.setText(res_tv.getText());
            return;
        }
        if(btu_text.equals("C"))
        {
            //it will cut the last enter value from the (enterdat_tv)
            datatocal = datatocal.substring(0,datatocal.length()-1);
        }
        else
        {
            //to concatinate the text enter by button on layout(enterdata_tv)
            datatocal = datatocal + btu_text;
        }
        enterdata_tv.setText(datatocal);

        String Finalres = toCalculate(datatocal);//this final result we hava to set on res_tv

        if(!Finalres.equals("Err"))
        {
            res_tv.setText((Finalres)); //seting reult on res_tv
        }

    }

    // method to calculate
    String toCalculate(String data)
    {
        try
        {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            //for final result
            String Finalres = context.evaluateString(scriptable,data,"javaScript",1,null).toString();
                // to replace .0 to empty string ("")
            if(Finalres.endsWith(".0"))
            {
                Finalres = Finalres.replace(".0","");
            }

            return Finalres;
        }
        catch (Exception e)
        {
            return "Err";
        }

    }
}