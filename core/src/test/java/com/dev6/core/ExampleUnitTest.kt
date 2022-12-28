package com.dev6.core
import com.dev6.core.util.Validation
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    private lateinit var validation : Validation

    @Before
    fun setUp(){}


    @Test
    fun checkIdPattern() {
        validation = spy(Validation::class.java)
        assertEquals(true, validation.checkIdPattern("dfgdfgdf1"))
    }

    @Test
    fun checkPwPattern() {
        validation = spy(Validation::class.java)
        assertEquals(true, validation.checkPwPattern("1234sdfd"))
    }

    @Test
    fun checkNicknamePattern() {
        validation = spy(Validation::class.java)
        assertEquals(true, validation.checkNickNamePattern("테스트"))
    }
}