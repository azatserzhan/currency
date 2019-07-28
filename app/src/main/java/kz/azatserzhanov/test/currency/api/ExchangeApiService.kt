package kz.azatserzhanov.test.currency.api

import io.reactivex.Observable
import io.reactivex.Single
import kz.azatserzhanov.test.currency.model.Currency
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ExchangeApiService {

    @GET("search")
    fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Observable<Currency>

    @GET("latest")
    fun get(): Single<Currency>

    /**
     * Companion object to create the ExchangeApiService
     */
    companion object Factory {
        fun create(): ExchangeApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.exchangeratesapi.io/")
                .build()

            return retrofit.create(ExchangeApiService::class.java);
        }
    }
}