package com.example.linh_minutes.giaitoannhanh1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class diemcao extends AppCompatActivity {
    SharedPreferences luuDiem, luuten ;
    TextView diemcaodungsai, diemcaocongtru;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diemcao);
        diemcaodungsai = (TextView) findViewById(R.id.diemcaodungsai);
        diemcaocongtru = (TextView) findViewById(R.id.diemcaocongtru);




        luuDiem = getSharedPreferences("DIEMCAO" , Context.MODE_PRIVATE);
        int getdiem_dung_sai = luuDiem.getInt("DIEMCAO_DUNG_SAI",0);
        String ten_dung_sai = luuDiem.getString("NGUOI_CHOI_DUNG_SAI", "Người chơi");

        diemcaodungsai.setText(ten_dung_sai+" : "+ getdiem_dung_sai);
        int getdiem_congtru = luuDiem.getInt("DIEMCAO_CONG_TRU",0);
        String ten_cong_tru = luuDiem.getString("NGUOI_CHOI_CONG_TRU", "Người chơi");
        diemcaocongtru.setText(ten_cong_tru+" : " + getdiem_congtru);


    }


}
