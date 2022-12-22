package com.dev6.core.util

import android.util.Log
import timber.log.Timber
import java.util.regex.Pattern

class Validation {
    val idPattern: String = "^(?=.*[a-z])(?=.*[0-9])[a-z[0-9]]{5,20}$" // 영어 숫자 섞어쓰기 가능 대문자 X
    val idPattern2: String = "^[a-zA-Z]{5,20}$" // 영문만 가능

    // 대문자 필수포함, 소영문, 숫자 8 ~ 20자 패턴
    val pwPattern: String = "^(?=.*[A-Z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}$"
    val nickNamePattern: String = "^[가-힣a-zA-Z]{2,15}$"

    fun checkPwPattern(passWord: String): Boolean {
        val pattern = Pattern.compile(pwPattern)
        val matcher = pattern.matcher(passWord)
        var result = matcher.find()
        return result
    }


    fun checkIdPattern(Id: String): Boolean {
        var result3 = false
        val pattern = Pattern.compile(idPattern)
        val matcher = pattern.matcher(Id)
        val pattern2 = Pattern.compile(idPattern2)
        val matcher2 = pattern2.matcher(Id)
        var result1 = matcher.find()
        var result2 = matcher2.find()
        result3 = result1 || result2
        return result3
    }

    fun checkNickNamePattern(nickName: String): Boolean {
        val pattern = Pattern.compile(nickNamePattern)
        val matcher = pattern.matcher(nickName)
        var result1 = matcher.find()
        return result1
    }
}