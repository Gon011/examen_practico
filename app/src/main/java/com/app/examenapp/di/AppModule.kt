package com.app.examenapp.di

import com.app.examenapp.data.remote.api.SentimentApi
import com.app.examenapp.data.repository.SentimentRepositoryImpl
import com.app.examenapp.domain.repository.SentimentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor { chain ->
                        val request =
                            chain
                                .request()
                                .newBuilder()
                                .addHeader("X-Api-Key", "lHDbvFT4wabSf5+v9sEnIQ==KKONIdLywJQWGVBD")
                                .build()
                        chain.proceed(request)
                    }.build(),
            ).build()

    @Provides
    @Singleton
    fun provideSentimentApi(retrofit: Retrofit): SentimentApi =
        retrofit
            .create(SentimentApi::class.java)

    @Provides
    @Singleton
    fun provideSentimentRepository(api: SentimentApi): SentimentRepository = SentimentRepositoryImpl(api)
}
