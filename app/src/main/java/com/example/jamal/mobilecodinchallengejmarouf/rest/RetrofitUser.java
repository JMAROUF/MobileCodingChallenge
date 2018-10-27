package com.example.jamal.mobilecodinchallengejmarouf.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUser {



        private static final String BASE_URL = "https://api.github.com/search/";
        private static volatile RetrofitUser mInstance;
        private Retrofit retrofit;


        private RetrofitUser() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static synchronized RetrofitUser getInstance() {
            if (mInstance == null) {
                mInstance = new RetrofitUser();
            }
            return mInstance;
        }

        public API getApi() {
            return retrofit.create(API.class);
        }
    }

