package br.com.victorcs.querosermb.data.source.remote

import br.com.victorcs.querosermb.BuildConfig
import br.com.victorcs.querosermb.core.interceptor.ConnectivityInterceptor
import br.com.victorcs.querosermb.core.services.WifiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val HEADER_TOKEN = "X-CoinAPI-Key"
private const val HEADER_ACCEPT = "Accept"
private const val JSON_APPLICATION = "application/json"

object RetrofitConfig {

    fun <T> create(service: Class<T>, baseUrl: String, wifiService: WifiService): T {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Auth2HeaderInterceptor())
            .addInterceptor(ConnectivityInterceptor(wifiService))
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(MoshiBuilder.create()))
            .build()
            .create(service) as T
    }
}

class Auth2HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
            .header(HEADER_TOKEN, BuildConfig.TOKEN_KEY)
            .header(HEADER_ACCEPT, JSON_APPLICATION)

        return chain.proceed(builder.build())
    }
}