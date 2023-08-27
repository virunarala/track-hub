package dev.virunarala.trackhub.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.virunarala.trackhub.data.network.model.Repository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

private const val BASE_URL = "https://api.github.com/"

private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val headerAdder = object: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest  = chain.request().newBuilder()
            .build()
        return chain.proceed(newRequest)
    }

}

private val okHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(interceptor)
    addInterceptor(headerAdder)
}.build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttpClient)
    .build()

interface ApiEndpoint {

    @GET("repos/{owner}/{repoName}")
    suspend fun getRepo(@Path("owner") owner: String, @Path("repoName") repoName: String): Repository
}


@InstallIn(SingletonComponent::class)
@Module
object Network {

    @Singleton
    @Provides
    fun providesApiService(): ApiEndpoint {
        val apiEndpoint: ApiEndpoint by lazy {
            retrofit.create(ApiEndpoint::class.java)
        }
        return apiEndpoint
    }


}