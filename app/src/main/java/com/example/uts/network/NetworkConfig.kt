package com.example.uts.network

import com.example.uts.model.ResponseAddMatkul
import com.example.uts.model.ResponseItem
import com.example.uts.model.ResponseMatkul
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class NetworkConfig {
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.4/uts_crud/public/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Users::class.java)
}
interface Users {
    @GET("api/progmob/matkul/{nim_progmob}")
    fun getMatkul(@Path("nim_progmob") nim_progmob : String): Call<List<ResponseMatkul>>

    @POST("api/progmob/matkul/create")
    fun addMatkul(@Body req : ResponseItem): Call<ResponseAddMatkul>

    @PUT("api/progmob/matkul/update/")
    fun updateMatkul(@Path("id") id : Int, @Body req : ResponseItem): Call<ResponseAddMatkul>

    @DELETE("api/progmob/matkul/delete")
    fun deleteMatkul(@Path("id") id: Int): Call<ResponseAddMatkul>

}