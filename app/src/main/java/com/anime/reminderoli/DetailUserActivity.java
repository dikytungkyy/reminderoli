package com.anime.reminderoli;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailUserActivity extends AppCompatActivity {
    final static String getData = "User";
    User user;
    @BindView(R.id.img_user)
    ImageView mFoto;
    @BindView(R.id.detail_username)
    TextView mUser;
    @BindView(R.id.detail_nopol)
    TextView mNoPol;
    @BindView(R.id.detail_jenisoli)
    TextView mOli;
    @BindView(R.id.detail_kilometersekarang)
    TextView kmAwal;
    @BindView(R.id.kilometersevice)
    TextView kmAkhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        ButterKnife.bind(this);
        user = getIntent().getParcelableExtra(getData);
        if (!user.getFoto().equals("")){
            Glide.with(this).load(user.getFoto()).into(mFoto);
        }else {
            mFoto.setBackgroundResource(R.drawable.ic_wallpaper_black_24dp);
        }
        mUser.setText("Nama\t\t\t\t\t\t\t: "+user.getNama());
        mNoPol.setText("No Pol\t\t\t\t\t\t\t: "+user.getNoPol());
        kmAkhir.setText("Km Service\t\t\t: "+user.getKmService());
        kmAwal.setText("Km Awal\t\t\t\t\t: "+user.getKmSekarang());
        mOli.setText("Jenis Oli\t\t\t\t\t: "+"Null");

    }
}
