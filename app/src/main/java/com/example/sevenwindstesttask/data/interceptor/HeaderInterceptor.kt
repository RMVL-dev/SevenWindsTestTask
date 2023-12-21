package com.example.sevenwindstesttask.data.interceptor

import com.example.sevenwindstesttask.data.repository.SharedPref
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderInterceptor @Inject constructor(
    preferenceStorage:SharedPref
):Interceptor {

    companion object{
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer "
    }


    private val token: String by lazy {
        preferenceStorage.userToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = if (!original.url.encodedPath.contains("user/signin")||token.isNotBlank()){
            original.newBuilder().header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
        }else{
            original.newBuilder()
        }
            .method(original.method, original.body)
            .build()

        return chain.proceed(request = request)
    }

}