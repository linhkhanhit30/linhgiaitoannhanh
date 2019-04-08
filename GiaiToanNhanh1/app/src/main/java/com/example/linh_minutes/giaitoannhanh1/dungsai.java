package com.example.linh_minutes.giaitoannhanh1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class dungsai extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences luuDiem, luuten;
    ImageButton btn_dung, btn_sai;
    TextView tv_cauhoi, tv_diem;
    int so_1, so_2, so_3, diem, count;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer, me_sai;
    Dialog dialogPlayer;
    EditText edtPlayer;
    Button btnOk, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungsai);
        ketnoi();
        taocauhoi();
        btn_sai.setOnClickListener(this);
        btn_dung.setOnClickListener(this);

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                count = progressBar.getProgress();
                if (count >= progressBar.getMax()) {
                    count = 0;
                }
                progressBar.setProgress(count + 10);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(200);
                ketthuc();
//                DialogShow(0);
            }

        };
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dungroigioiqua);
        me_sai = MediaPlayer.create(getApplicationContext(), R.raw.chuadungtiecqua);
    }

    public void ketnoi() {
        btn_dung = (ImageButton) findViewById(R.id.btn_dung);
        btn_sai = (ImageButton) findViewById(R.id.btn_sai);
        tv_cauhoi = (TextView) findViewById(R.id.tv_cauhoi);
        tv_diem = (TextView) findViewById(R.id.tv_diem);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void taocauhoi() {
        Random rd = new Random();
        so_1 = rd.nextInt(10);
        so_3 = rd.nextInt(10);
        int check = rd.nextInt(2);
        if (check % 2 == 0) {
            if (so_3 > so_1) {
                // 1 +1 = 2
                so_2 = so_3 - so_1;
            } else {
                // sai
                so_2 = rd.nextInt(11);
            }

            String cauhoi = so_1 + " + " + so_2 + " = " + so_3;
            tv_cauhoi.setText(cauhoi);
        } else {
            if (so_3 > so_1) {
                // 1 +1 = 2
                so_2 = so_3 - so_1;
            } else {
                // sai
                so_2 = rd.nextInt(11);
            }

            String cauhoi = so_3 + " - " + so_2 + " = " + so_1;
            tv_cauhoi.setText(cauhoi);
        }

    }

    public boolean check_kq() {
        if (so_1 + so_2 == so_3) {
            return true;
        } else {
            return false;
        }
    }

    public void ketthuc() {

        luuDiem = getSharedPreferences("DIEMCAO", Context.MODE_PRIVATE);

        int getdiem = luuDiem.getInt("DIEMCAO_DUNG_SAI", 0);


        if (getdiem < diem) {
//            SharedPreferences.Editor edit = luuDiem.edit();
//            edit.putInt("DIEMCAO", diem);
//            edit.apply();
            DialogPlayer();

        }else {
            DialogShow(0);
        }


    }

    public void DialogPlayer() {
        dialogPlayer = new Dialog(this);
        dialogPlayer.setTitle("Nhập tên người chơi");
        dialogPlayer.setContentView(R.layout.dialog_player_high_score);

         edtPlayer = (EditText) dialogPlayer.findViewById(R.id.edt_player);
         btnCancel = (Button) dialogPlayer.findViewById(R.id.btn_cancel);
         btnOk = (Button) dialogPlayer.findViewById(R.id.btn_ok);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player = "Người chơi";
                SharedPreferences.Editor edit = luuDiem.edit();
                edit.putInt("DIEMCAO_DUNG_SAI", diem);
                edit.putString("NGUOI_CHOI_DUNG_SAI", player);
                edit.apply();
                dialogPlayer.cancel();
                DialogShow(0);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player = "Người chơi";
                if (edtPlayer.getText().toString().length() > 0)
                    player = edtPlayer.getText().toString();
                else{
                    Toast.makeText(dungsai.this, "Nhập tên người chơi", Toast.LENGTH_SHORT).show();
                }
                if (edtPlayer.getText().toString().length() > 15) {
                    Toast.makeText(dungsai.this, "Tên người chơi quá dài", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor edit = luuDiem.edit();
                    edit.putInt("DIEMCAO_DUNG_SAI", diem);
                    edit.putString("NGUOI_CHOI_DUNG_SAI", player);
                    edit.apply();
                    dialogPlayer.cancel();
                    DialogShow(0);
                }
            }
        });
        dialogPlayer.show();

    }

    private void DialogShow(int num) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Mời bạn lựa chọn");
        dialog.setCancelable(false);
        TextView tv_dialog = (TextView) dialog.findViewById(R.id.tv_dialog);
        ImageButton btnCancel = (ImageButton) dialog.findViewById(R.id.btnCancel);
        ImageButton btnReplay = (ImageButton) dialog.findViewById(R.id.btnReplay);


        if (num == 0) {
            tv_dialog.setText("Bé chơi lại nhé?");
            btnCancel.setImageResource(R.drawable.ic_exit_to_app_black_24dp);
            btnReplay.setImageResource(R.drawable.ic_replay_black_24dp);
            // if button is clicked, close the custom dialog

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intt = new Intent(dungsai.this, MainActivity.class);
                    startActivity(intt);
                }
            });
            btnReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intt = new Intent(dungsai.this, dungsai.class);
                    startActivity(intt);
                }
            });
        } else {
            tv_dialog.setText("Bé muốn thoát?");
            btnCancel.setImageResource(R.drawable.ic_exit_to_app_black_24dp);
            btnReplay.setImageResource(R.drawable.ic_replay_black_24dp);

            // if button is clicked, close the custom dialog
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intt = new Intent(dungsai.this, MainActivity.class);
                    startActivity(intt);
                }
            });
            btnReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    onResume();
                }
            });
        }

        dialog.show();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btn_dung.getId()) {
            if (check_kq()) {
                diem++;
                mediaPlayer.start();
                tv_diem.setText(diem + "");
                progressBar.setProgress(0);
                countDownTimer.cancel();
                countDownTimer.start();
                taocauhoi();
            } else {
                me_sai.start();
//                DialogShow(0);
                countDownTimer.cancel();
                ketthuc();

            }

        }
        if (v.getId() == btn_sai.getId()) {
            if (!check_kq()) {
                diem++;
                mediaPlayer.start();
                progressBar.setProgress(0);
                countDownTimer.cancel();
                countDownTimer.start();
                taocauhoi();
                tv_diem.setText(diem + "");
            } else {
                me_sai.start();
                countDownTimer.cancel();
                ketthuc();
//                DialogShow(0);
            }
        }

    }

    @Override
    public void onBackPressed() {
        DialogShow(1);
        countDownTimer.cancel();

    }

    @Override
    protected void onResume() {
        super.onResume();
        taocauhoi();
        countDownTimer.start();
        progressBar.setProgress(0);

    }
}
