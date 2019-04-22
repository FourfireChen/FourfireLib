package com.fourfire.fourfirelib;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fourfire.syringe.Syringe;
import com.fourfire.syringeannotation.BindView;

import androidx.appcompat.app.AppCompatActivity;

//import com.fourfire.syringeannotation.BindView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.mButton)
    Button mButton;

    DataRes dataRes = new DataRes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Syringe.sringe(this);

        mButton.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        dataRes.testRetrofit(new DataRes.Callback() {
            @Override public void onSuccess(String content) {
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
            }

            @Override public void onFail() {

            }
        });
    }
}
