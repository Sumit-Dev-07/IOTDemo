package com.hexalitics.mvvm_starter.di.network

import com.hexalitics.mvvm_starter.BuildConfig
import com.hexalitics.mvvm_starter.di.NoConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Method used for provide ConfigApiService instance for hilt
     */
    @Provides
    fun provideConfigApi(noConnectionInterceptor: NoConnectionInterceptor): ApiService = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .client(provideOkHttpClient(noConnectionInterceptor))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    /**
     * Method used for provide ConfigRepository instance for hilt
     */
    @Provides
    fun provideOkHttpClient(noConnectionInterceptor: NoConnectionInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.SECONDS)
            .writeTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpBuilder.addInterceptor(interceptor)
        }
        //okHttpBuilder.addInterceptor(noConnectionInterceptor)
        /*okHttpBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("api-key", Constants.SERVER_API_KEY)
            request.addHeader("version", Constants.SERVER_API_VERSION)
            request.addHeader("package-name", Constants.SERVER_API_PACKAGE)
            request.addHeader("resource-type", Constants.resourceType)
            request.addHeader("Accept", Constants.accept)
            chain.proceed(request.build())
        }*/
        val okHttpClient = okHttpBuilder.build()
        okHttpClient.dispatcher.maxRequests = 6
        return okHttpClient
    }

    /**
     * Method used for provide ConfigRepository instance for hilt
     */
    @Provides
    fun provideConfigRepository(retrofitApi: ApiService): Repository =
        Repository(retrofitApi)

}