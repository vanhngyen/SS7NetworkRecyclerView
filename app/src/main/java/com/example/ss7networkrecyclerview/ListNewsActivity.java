package com.example.ss7networkrecyclerview;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ss7networkrecyclerview.adapter.NewAdapter;
import com.example.ss7networkrecyclerview.model.Item;
import com.example.ss7networkrecyclerview.network.APIManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListNewsActivity extends AppCompatActivity {
    RecyclerView rvListNews;
    List<Item> listData;
    NewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        // B1:data source
        getListData();
        // B2:adapter
        listData = new ArrayList<>();
        adapter = new NewAdapter(listData, ListNewsActivity.this);
        // B3: layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        // B4: recyclerView
        rvListNews = findViewById(R.id.rvListNews);
        rvListNews.setLayoutManager(layoutManager);
        rvListNews.setAdapter(adapter);

    }

    private void getListData() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIManager.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        APIManager service = retrofit.create(APIManager.class);
        service.getListData().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.body() != null) {
                    listData = response.body();
                    adapter.reloadData(listData);

                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(ListNewsActivity.this, "Load fail", Toast.LENGTH_LONG).show();
            }
        });

    }
}
