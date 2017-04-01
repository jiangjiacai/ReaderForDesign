package com.design.reader.api;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface ReaderApiService {

    @GET("")
    Observable<String> login(@Query("username") String userName, @Query("password") String password);

    @GET("")
    Observable<String> register(@Query("username") String userName, @Query("password") String password);

    @GET
    Observable<ResponseBody> downloadBook(@Url String url);

}
