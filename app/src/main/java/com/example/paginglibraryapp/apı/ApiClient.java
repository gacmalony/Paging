package com.example.paginglibraryapp.apı;

import static com.example.paginglibraryapp.utils.Utils.API_KEY;
import static com.example.paginglibraryapp.utils.Utils.BASE_URL;




import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class ApiClient {

    static APIInterface apiInterface;

    public static APIInterface getApiInterface(){
        if (apiInterface == null){
            OkHttpClient.Builder client = new OkHttpClient.Builder();

            client.addInterceptor(chain ->{
                Request original = chain.request();
                HttpUrl originalhttpurl = original.url();
                HttpUrl url = originalhttpurl.newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build();
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            apiInterface = retrofit.create(APIInterface.class);
        }
        return apiInterface;
    }



}
