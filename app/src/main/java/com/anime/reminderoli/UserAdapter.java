package com.anime.reminderoli;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.client.cache.Resource;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<User> listUser;
    Context context;

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    public UserAdapter(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (!getListUser().get(i).getFoto().equals("")){

            Glide.with(context).load(getListUser().get(i).getFoto()).into(viewHolder.mGambar);
        }else {
            viewHolder.mGambar.setBackgroundResource(R.drawable.ic_wallpaper_black_24dp);
        }

        viewHolder.kmAkhir.setText(getListUser().get(i).getKmService());
        viewHolder.kmAwal.setText(getListUser().get(i).getKmSekarang());
        viewHolder.mNama.setText(getListUser().get(i).getNama());
        viewHolder.mNopol.setText(getListUser().get(i).getNoPol());



    }

    @Override
    public int getItemCount() {
        return getListUser().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gambar)
        ImageView mGambar;
        @BindView(R.id.list_nama)
        TextView mNama;
        @BindView(R.id.no_pol)
        TextView mNopol;
        @BindView(R.id.jenisoli)
        TextView mOli;
        @BindView(R.id.kilometer_sekarang)
        TextView kmAwal;
        @BindView(R.id.kilometer_service)
        TextView kmAkhir;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
