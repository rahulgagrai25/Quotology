package com.example.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//API link- https://quotes-api-self.vercel.app/quote/

public interface MyApi {

    @GET("quote/")
    Call<Quote> getQuote();
}
