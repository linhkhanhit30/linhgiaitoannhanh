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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class congtru extends AppCompatActivity implements View.OnClickListener {
    ImageButton num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;
    TextView tv_dau, tv_diem;
    ImageView tv_num1, tv_num2;
    int ketqua = 0, diem = 0, count;
    MediaPlayer mediaPlayer, me_sai;
    Question question = new Question();
    Random random = new Random();
    int check;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    SharedPreferences luuDiem, luuten;
    Dialog dialogPlayer;
    EditText edtPlayer;
    Button btnOk, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congtru);
        ketnoi();
        click();
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
        num0 = (ImageButton) findViewById(R.id.num0);
        num1 = (ImageButton) findViewById(R.id.num1);
        num2 = (ImageButton) findViewById(R.id.num2);
        num3 = (ImageButton) findViewById(R.id.num3);
        num4 = (ImageButton) findViewById(R.id.num4);
        num5 = (ImageButton) findViewById(R.id.num5);
        num6 = (ImageButton) findViewById(R.id.num6);
        num7 = (ImageButton) findViewById(R.id.num7);
        num8 = (ImageButton) findViewById(R.id.num8);
        num9 = (ImageButton) findViewById(R.id.num9);
        num10 = (ImageButton) findViewById(R.id.num10);
        tv_dau = (TextView) findViewById(R.id.tv_dau);
        tv_diem = (TextView) findViewById(R.id.tv_diem);
        tv_num1 = (ImageView) findViewById(R.id.tv_num1);
        tv_num2 = (ImageView) findViewById(R.id.tv_num2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    public void click() {
        num0.setOnClickListener(this);
        num1.setOnClickListener((View.OnClickListener) this);
        num2.setOnClickListener((View.OnClickListener) this);
        num3.setOnClickListener((View.OnClickListener) this);
        num4.setOnClickListener((View.OnClickListener) this);
        num5.setOnClickListener((View.OnClickListener) this);
        num6.setOnClickListener((View.OnClickListener) this);
        num7.setOnClickListener((View.OnClickListener) this);
        num8.setOnClickListener((View.OnClickListener) this);
        num9.setOnClickListener((View.OnClickListener) this);
        num10.setOnClickListener((View.OnClickListener) this);

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
                    Intent intt = new Intent(congtru.this, MainActivity.class);
                    startActivity(intt);
                }
            });
            btnReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intt = new Intent(congtru.this, congtru.class);
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
                    Intent intt = new Intent(congtru.this, MainActivity.class);
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

    public void ketthuc() {
        countDownTimer.cancel();
        luuDiem = getSharedPreferences("DIEMCAO", Context.MODE_PRIVATE);
        int getdiem = luuDiem.getInt("DIEMCAO_CONG_TRU", 0);

        if (getdiem < diem) {
//            SharedPreferences.Editor edit = luuDiem.edit();
//            edit.putInt("DIEMCAO_CONG_TRU", diem);
//            edit.apply();
            DialogPlayer();
        }
        else {
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
                edit.putInt("DIEMCAO_CONG_TRU", diem);
                edit.putString("NGUOI_CHOI_CONG_TRU", player);
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
                else {
                    Toast.makeText(congtru.this, "Nhập tên người chơi", Toast.LENGTH_SHORT).show();
                }
                if (edtPlayer.getText().toString().length() > 15) {
                    Toast.makeText(congtru.this, "Tên người chơi quá dài , nhập lại ", Toast.LENGTH_SHORT).show();
                   return;
                }
                SharedPreferences.Editor edit = luuDiem.edit();
                edit.putInt("DIEMCAO_CONG_TRU", diem);
                edit.putString("NGUOI_CHOI_CONG_TRU", player);
                edit.apply();
                dialogPlayer.cancel();
                DialogShow(0);
            }
        });
        dialogPlayer.show();
    }

    public void setImage(int num1, int num2) {
        if (ketqua == question.tru()) {
            question.change();
        }
        switch (num1) {
            case 0: {
                tv_num1.setImageResource(R.drawable.num0);
                break;
            }
            case 1: {
                tv_num1.setImageResource(R.drawable.num1);
                break;
            }
            case 2: {
                tv_num1.setImageResource(R.drawable.num2);
                break;
            }
            case 3: {
                tv_num1.setImageResource(R.drawable.num3);
                break;
            }
            case 4: {
                tv_num1.setImageResource(R.drawable.num4);
                break;
            }
            case 5: {
                tv_num1.setImageResource(R.drawable.num5);
                break;
            }
            case 6: {
                tv_num1.setImageResource(R.drawable.num6);
                break;
            }
            case 7: {
                tv_num1.setImageResource(R.drawable.num7);
                break;
            }
            case 8: {
                tv_num1.setImageResource(R.drawable.num8);
                break;
            }
            case 9: {
                tv_num1.setImageResource(R.drawable.num9);
                break;
            }
            case 10: {
                tv_num1.setImageResource(R.drawable.num10);
                break;
            }
        }
        switch (num2) {
            case 0: {
                tv_num2.setImageResource(R.drawable.num0);
                break;
            }
            case 1: {
                tv_num2.setImageResource(R.drawable.num1);
                break;
            }
            case 2: {
                tv_num2.setImageResource(R.drawable.num2);
                break;
            }
            case 3: {
                tv_num2.setImageResource(R.drawable.num3);
                break;
            }
            case 4: {
                tv_num2.setImageResource(R.drawable.num4);
                break;
            }
            case 5: {
                tv_num2.setImageResource(R.drawable.num5);
                break;
            }
            case 6: {
                tv_num2.setImageResource(R.drawable.num6);
                break;
            }
            case 7: {
                tv_num2.setImageResource(R.drawable.num7);
                break;
            }
            case 8: {
                tv_num2.setImageResource(R.drawable.num8);
                break;
            }
            case 9: {
                tv_num2.setImageResource(R.drawable.num9);
                break;
            }
            case 10: {
                tv_num2.setImageResource(R.drawable.num10);
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Question question = new Question();
        countDownTimer.start();
        check = random.nextInt(2);

        if (check % 2 == 0) {
            ketqua = question.cong();
            tv_dau.setText("+");
        } else {
            //question.change();
            ketqua = question.tru();
            tv_dau.setText("-");
            //
        }

        setImage(question.num1, question.num2);
        progressBar.setProgress(0);
    }

    public void onBackPressed() {
        DialogShow(1);
        countDownTimer.cancel();

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.num0: {
                if (ketqua == 0) {
                    diem++;
                    tv_diem.setText(diem + "");
                    mediaPlayer.start();
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }
                break;
            }
            case R.id.num1: {
                if (ketqua == 1) {
                    diem++;
                    tv_diem.setText(diem + "");
                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }
                break;
            }
            case R.id.num2: {
                if (ketqua == 2) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }

                break;
            }
            case R.id.num3: {
                if (ketqua == 3) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }
                break;
            }
            case R.id.num4: {
                if (ketqua == 4) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }
                break;
            }
            case R.id.num5: {
                if (ketqua == 5) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }
                break;
            }
            case R.id.num6: {
                if (ketqua == 6) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }

                break;
            }
            case R.id.num7: {
                if (ketqua == 7) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }

                break;
            }
            case R.id.num8: {
                if (ketqua == 8) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }
                break;
            }
            case R.id.num9: {
                if (ketqua == 9) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }
                break;
            }
            case R.id.num10: {
                if (ketqua == 10) {
                    diem++;
                    tv_diem.setText(diem + "");

                    mediaPlayer.start();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    onResume();
                } else {
                    ketthuc();
                    me_sai.start();
//                    DialogShow(0);
                }
                break;
            }
        }

    }

}
