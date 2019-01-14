package com.anime.reminderoli;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailUserActivity extends AppCompatActivity {
    final static String getData = "mobil";
    Mobil mobil;

    @BindView(R.id.detail_username)
    TextView mUser;
    @BindView(R.id.detail_nopol)
    TextView mNoPol;
    @BindView(R.id.detail_jenisoli)
    TextView mOli;
    @BindView(R.id.detail_kilometersekarang)
    TextView kmAwal;
    @BindView(R.id.detaiKmService)
    TextView kmAkhir;
    @BindView(R.id.detaiNamaMobil)
    TextView mNamaMobil;
    @BindView(R.id.detaiJenisMobil)
    TextView mJenisMobil;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.loadingDetail)
    ProgressBar loading;


    String URL = "http://reminder.96.lt/getMobil.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        ButterKnife.bind(this);


        mobil = getIntent().getParcelableExtra(getData);
        loadData(mobil);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(mobil);
            }
        });

    }


    private void loadData(final Mobil user) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Mobil newUser = new Mobil();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object1 = jsonArray.getJSONObject(i);
                        Mobil userUrl = new Mobil(object1);


                        if (userUrl.getId_user().equals(user.getId_user())) {
                            loading.setVisibility(View.GONE);
                            newUser = userUrl;


                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //isi data disini

                mUser.setText("Nama\t\t\t\t\t\t\t: " + newUser.getId_user());
                mNoPol.setText("No Pol\t\t\t\t\t\t\t: " + newUser.getNoPol());
                kmAkhir.setText("Km Service\t\t\t: " + newUser.getKmService());
                kmAwal.setText("Km Awal\t\t\t\t\t: " + newUser.getKmSekarang());
                mOli.setText("Jenis Oli\t\t\t\t\t: " + newUser.getId_oli());
                mJenisMobil.setText("Jenis mobil\\t\\t\\t\\t\\t:" + newUser.getJenisMobil());
                mNamaMobil.setText("nama Mobil" + newUser.getNamaMobil());
                swipe.setRefreshing(false);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailUserActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(DetailUserActivity.this);
        requestQueue.add(stringRequest);

    }

}