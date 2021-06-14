package com.example.ss7networkrecyclerview.network;


import com.example.ss7networkrecyclerview.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIManager {

    String SERVER_URL = "https://api-demo-anhth.herokuapp.com/";

    @GET("data.json")
    Call<Item> getItemData();


    @GET("datas.json")
    Call<List<Item>> getListData();

}

