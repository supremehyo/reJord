package com.dev6.core
import com.dev6.core.util.Validation
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidationTest {
    private lateinit var validation : Validation

    @Before
    fun setUp(){}


    @Test
    fun `아이디 체크 성공`() {
        validation = spy(Validation::class.java)
        assertEquals(true, validation.checkIdPattern("dfgdfgdf1"))
    }
    @Test
    fun `아이디 입력값 조건 체크 실패`() {
        validation = spy(Validation::class.java)
        assertEquals(false, validation.checkIdPattern("!@$%gfdg"))
    }
    @Test
    fun `아이디 입력값 길이 체크 실패`() {

        validation = spy(Validation::class.java)
        assertEquals(true, validation.checkIdPattern("awefg1awefg1awefg1awefg1awefg1awefg1awefg1"))
    }
    @Test
    fun `아이디 empty 체크`() {
        validation = spy(Validation::class.java)
        assertEquals(false, validation.checkIdPattern(""))
    }


    @Test
    fun `패스워드 체크 성공`() {
        validation = spy(Validation::class.java)
        assertEquals(true, validation.checkPwPattern("1234sdfd"))
    }
    @Test
    fun `패스워드 체크 실패`() {
        validation = spy(Validation::class.java)
        assertEquals(false, validation.checkPwPattern("1234sdfd1234sdfd1234sdfd1234sdfd1234sdfd"))
    }

    @Test
    fun `패스워드 empty 체크`() {
        validation = spy(Validation::class.java)
        assertEquals(false, validation.checkPwPattern(""))
    }


    @Test
    fun `닉네임체크 성공`() {
        validation = spy(Validation::class.java)
        assertEquals(true, validation.checkNickNamePattern("테스트"))
    }
    @Test
    fun `닉네임체크 실패`() {
        validation = spy(Validation::class.java)
        assertEquals(false, validation.checkNickNamePattern("테스!트"))
    }
    @Test
    fun `닉네임 empty 체크`() {
        validation = spy(Validation::class.java)
        assertEquals(false, validation.checkNickNamePattern(""))
    }
}