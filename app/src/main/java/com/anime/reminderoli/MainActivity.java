package com.anime.reminderoli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_list_user)
            RecyclerView rvCatagory;

    UserAdapter adapter;
    ArrayList<User> listUser;
    String URL = "http://reminder.96.lt/jsonNew.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listUser = new ArrayList<>();
        adapter = new UserAdapter(this);
        adapter.setListUser(listUser);
        rvCatagory.setLayoutManager(new LinearLayoutManager(this));
        rvCatagory.setAdapter(adapter);
        ItemClickSupport.addTo(rvCatagory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                User user = new User();
                user.setNama(listUser.get(position).getNama());
                user.setNoPol(listUser.get(position).getNoPol());
                user.setKmService(listUser.get(position).getKmService());
                user.setKmSekarang(listUser.get(position).getKmSekarang());
                user.setFoto(listUser.get(position).getFoto());

                Intent intent = new Intent(MainActivity.this,DetailUserActivity.class);
                intent.putExtra("User",user);
                startActivity(intent);
            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<User> userData = new ArrayList<>();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        User user = new User(obj);


                       userData.add(user);


                    }
                    listUser.clear();
                    listUser.addAll(userData);
                    adapter.setListUser(listUser);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
