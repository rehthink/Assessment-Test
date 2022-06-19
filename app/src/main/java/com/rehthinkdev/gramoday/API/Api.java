package com.rehthinkdev.gramoday.API;

import com.rehthinkdev.gramoday.Model.Business;
import com.rehthinkdev.gramoday.Model.GramModel;
import com.rehthinkdev.gramoday.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://api.gramoday.net:8082/v1/user/";

    @GET("bySlug?profileSlug=mmtradingco")
    Call<GramModel> getUser();

    @GET("bySlug?profileSlug=mmtradingco")
    Call<List<Product>> getProduct();

}
