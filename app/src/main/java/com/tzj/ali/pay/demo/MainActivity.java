package com.tzj.ali.pay.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.tzj.ali.pay.PayResult;
import com.tzj.ali.pay.UtilAlipay;

public class MainActivity extends Activity  implements View.OnClickListener{

    private TextView input,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        result = findViewById(R.id.result);
        result.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onClick(View v) {
        result.setText("");
        UtilAlipay.pay(this, input.getText().toString(), new UtilAlipay.PayCallback() {
            @Override
            public void CallBack(PayResult payResult) {
                result.setText(payResult.toString());
            }
        });
    }
}
