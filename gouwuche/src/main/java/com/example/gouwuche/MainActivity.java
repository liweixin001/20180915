package com.example.gouwuche;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fynn.fluidlayout.FluidLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String mNAME[] = {
            "考拉三周年人气热销榜",
            "电动牙刷",
            "尤妮佳",
            "豆豆鞋",
            "沐浴露",
            "日东红茶",
            "榴莲",
            "电动牙刷",
            "雅诗莱黛",
            "豆豆鞋"
    };
    private FluidLayout liu;
    private FluidLayout fluidLayout;
    private Button button;
    private EditText editText;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            initView();
            initData();
    }

    private void initView() {
        editText = findViewById(R.id.souzhou);
        button = findViewById(R.id.sousuo);
        fluidLayout = findViewById(R.id.liushi);
        liu = findViewById(R.id.liu);

        list = new ArrayList<>();

    }

    private void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = editText.getText().toString();

                 String liua[] = {s};
                for (int i = 0; i < liua.length; i++) {
                    FluidLayout.LayoutParams params =
                            new FluidLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(12, 12, 12, 12);

                    TextView tv = new TextView(MainActivity.this);
                    tv.setText(liua[i]);

                    tv.setBackgroundResource(R.drawable.flow_yangshi);
                    liu.addView(tv, params);
                }
            }
        });


        for (int i = 0; i < mNAME.length; i++) {
            FluidLayout.LayoutParams params=new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12,12,12,12);
            TextView textView= new TextView(this);
            textView.setText(mNAME[i]);

            textView.setBackgroundResource(R.drawable.flow_yangshi);
            textView.setTextColor(Color.BLACK);

            fluidLayout.addView(textView,params);
        }
    }


    public void cart(View view) {
        Toast.makeText(this, "asfsd", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,CartActivity.class));
    }
}
