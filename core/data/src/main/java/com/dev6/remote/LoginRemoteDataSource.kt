package com.dev6.remote
import android.util.Log
import com.dev6.model.executeNetworkHandling
import com.dev6.model.login.LoginReqDTO
import com.dev6.model.login.LoginResDTO
import com.dev6.model.login.TokensDTO
import com.dev6.network.LoginAPI
import javax.inject.Inject

interface  LoginRemoteDataSource{
    suspend fun login(loginReqDTO : LoginReqDTO) : LoginResDTO
}

class LoginRemoteDataSourceImpl @Inject constructor(
    private val loginService : LoginAPI
) : LoginRemoteDataSource {
    override suspend fun login(loginReqDTO: LoginReqDTO): LoginResDTO {
        Log.v("test" , loginReqDTO.password+" "+loginReqDTO.userId)
       return loginService.login(loginReqDTO).executeNetworkHandling()
    }
}