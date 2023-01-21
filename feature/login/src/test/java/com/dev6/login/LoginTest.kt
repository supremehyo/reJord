package com.dev6.login
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginTest {
    lateinit var loginUseCase: LoginUseCase
    lateinit var viewModel: LoginViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
       // val mainThreadSurrogate = newSingleThreadContext(LoginViewModel::class.java.simpleName)
        Dispatchers.setMain(testDispatcher)
        loginUseCase = Mockito.mock(LoginUseCase::class.java)
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
     fun `로그인 성공`() = runTest(UnconfinedTestDispatcher()) {
        var loginReq: LoginReq = LoginReq("testPassword", "Asdfsd")
        viewModel.userLogin(loginReq)

         assertEquals(true,true )
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}