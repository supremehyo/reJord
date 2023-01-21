package com.dev6.data.network
import com.dev6.data.MainCoroutinesRule
import com.dev6.model.login.LoginReqDTO
import com.dev6.network.LoginAPI
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class LoginApiTest : ApiAbstract<LoginAPI>() {
    private lateinit var service: LoginAPI

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setRetrofit() {
        service = createService(LoginAPI::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `로그인 테스트_성공`() {
        runBlocking {
            //enqueueResponse("/logintest200.json")
            val response = service.login (
                LoginReqDTO(
                    password = "A1234567",
                    userId = "qwert"
                )
            )
            println(response.body()?.nickname)
            Truth.assertThat(response.body()?.nickname).isEqualTo("닉닉")
        }
    }


    @Throws(IOException::class)
    @Test
    fun `로그인 테스트_404`() {
        runBlocking {
            val response = service.login(
                LoginReqDTO(
                    password = "A123dd4567",
                    userId = "qwert"
                )
            )
            println(response.code())
           // mockWebServer.takeRequest()
            Truth.assertThat(response.body()).isNull()
            Truth.assertThat(response.code()).isEqualTo(404)
        }
    }
}