package com.codegod.alctrack.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codegod.alctrack.R;
import com.codegod.alctrack.activity.adapter.DataAdapter;
import com.codegod.alctrack.activity.model.User;
import com.codegod.alctrack.activity.rest.GithubJSONResponse;
import com.codegod.alctrack.activity.rest.GithubRequestInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> data;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubRequestInterface request = retrofit.create(GithubRequestInterface.class);
        Call<GithubJSONResponse> call = request.getJSON();
        call.enqueue(new Callback<GithubJSONResponse>() {
            @Override
            public void onResponse(Call<GithubJSONResponse> call, Response<GithubJSONResponse> response) {
                GithubJSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getItems()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GithubJSONResponse> call, Throwable t) {
                Log.d("retro", t.getMessage());
            }
        });
    }
}
