package com.example.linh_minutes.giaitoannhanh1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton btn_dungsai, btn_info, btn_diemcao ,bt_congtru;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        bt_congtru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,congtru.class);
                startActivity(intent);
            }
        });
        btn_dungsai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(MainActivity.this,dungsai.class);
                startActivity(intent);
            }
        });
        btn_diemcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, diemcao.class);
                startActivity(intent);
            }
        });
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, info.class);
                startActivity(intent);
            }
        });
    }
    public void anhxa(){

        btn_dungsai =(ImageButton) findViewById(R.id.btn_dungsai);
        btn_diemcao =(ImageButton) findViewById(R.id.btn_diemcao);
        btn_info =(ImageButton) findViewById(R.id.btn_info);
        bt_congtru=(ImageButton) findViewById(R.id.btn_congtru);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}