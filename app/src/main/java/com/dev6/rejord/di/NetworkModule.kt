package com.dev6.rejord.di
import android.util.Log
import com.dev6.model.login.TokensDTO
import com.dev6.network.BannerAPI
import com.dev6.network.JoinAPI
import com.dev6.network.LoginAPI
import com.dev6.network.PostAPI
import com.dev6.rejord.Application.Companion.prefs
import com.google.gson.Gson
import com.jydev.rest_api_util.extension.executeErrorHandling
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
open class NetworkModule {
    protected open fun baseUrl() = "https://leadpet-dev6.com".toHttpUrl()

    companion object{
        var count = 0
        lateinit var tempRetrofit: Retrofit
        lateinit var authRetrofit : Retrofit
        lateinit var loginAPI: LoginAPI
    }

    init {
        authRetrofit = AuthRetrofit()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        tempRetrofit = Retrofit.Builder()
            .client(provideOkHttpClient(AppInterceptor()))
            .baseUrl(baseUrl())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return tempRetrofit
    }

    fun AuthRetrofit(): Retrofit {
        authRetrofit = Retrofit.Builder()
            .client(provideAuthOkHttpClient())
            .baseUrl(baseUrl())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return authRetrofit
    }

    fun provideAuthOkHttpClient(
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptorAuth: AppInterceptor
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(interceptorAuth)
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }



    class AppInterceptor() : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response {
            val originalRequest = chain.request()
            val authenticationRequest = request(originalRequest)
            val initialResponse = chain.proceed(authenticationRequest)
            count+=1
            if(initialResponse.code == 403 && !(prefs.getRefreshToken() == "" && count < 2)){
               var job = CoroutineScope(Dispatchers.IO).async {
                    try{
                        Log.v("리프레시토큰" , prefs.getRefreshToken())
                        var responseNewToken =  authRetrofit.create(LoginAPI::class.java).getToken("Bearer ${prefs.getRefreshToken()}")
                        prefs.saveToken(responseNewToken.accessToken , responseNewToken.refreshToken)
                    }catch (e : Exception){
                        Log.v("토큰 테스트2" , e.message.toString())
                        prefs.saveToken("","")
                    }
                }
                initialResponse.close()
                val newAuthenticationRequest = request(originalRequest)
                return chain.proceed(newAuthenticationRequest)
            }else{
                return initialResponse
            }
        }
        private fun request(originalRequest: Request): Request {
            return originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer ${prefs.getToken()}")
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideJoinService(retrofit: Retrofit): JoinAPI {
        return retrofit.create(JoinAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginAPI {
        return retrofit.create(LoginAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostAPI {
        return retrofit.create(PostAPI::class.java)
    }
    @Provides
    @Singleton
    fun provideBannerService(retrofit: Retrofit): BannerAPI {
        return retrofit.create(BannerAPI::class.java)
    }


}