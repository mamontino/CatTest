package com.mamontov.cattest.di.data

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mamontov.cattest.BuildConfig
import com.mamontov.cattest.di.AppScope
import com.mamontov.data.network.ErrorHandler
import com.mamontov.data.network.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule {

    companion object {
        private const val TIMEOUT_CONNECT_SEC = 30L
        private const val TIMEOUT_READ_SEC = 30L
        private const val TIMEOUT_WRITE_SEC = 30L
    }

    @Provides
    @AppScope
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }

    @Provides
    @AppScope
    fun provideErrorHandler(gson: Gson): ErrorHandler {
        return ErrorHandler(gson)
    }

    @Provides
    @AppScope
    fun provideCallAdapterFactory(): CallAdapter.Factory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @AppScope
    fun provideConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @AppScope
    fun provideHttpClient(): OkHttpClient =
        createHttpClient()

    @Provides
    @AppScope
    fun provideRetrofit(
        callAdapterFactory: CallAdapter.Factory,
        convertFactory: Converter.Factory,
        httpClient: OkHttpClient
    ): Retrofit =
        createRetrofit(BuildConfig.CAT_URL, callAdapterFactory, convertFactory, httpClient)

    private fun createRetrofit(
        baseUrl: String,
        callAdapterFactory: CallAdapter.Factory,
        convertFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(convertFactory)
            .client(okHttpClient)
            .build()

    private fun createHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(StethoInterceptor())
            .addInterceptor(HeaderInterceptor())
            .connectTimeout(TIMEOUT_CONNECT_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ_SEC, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_WRITE_SEC, TimeUnit.SECONDS)

        return builder.build()
    }
}