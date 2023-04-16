package com.dev6.core.util
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime

private object TimeMaximum {
    const val MIN = 60
    const val HOUR = 24
    const val DAY = 30
    const val MONTH = 12
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeString(
    year: Int, mounth: Int, day: Int, hour: Int, minite: Int
): String? {

    var diffTime = Duration.between(
        LocalDateTime.of(year, mounth, day, hour, minite),
        LocalDateTime.now()
    ).toMinutes()
    var msg: String? = null
    if(diffTime >0){
        when {
            diffTime < TimeMaximum.MIN -> {
                msg = diffTime.toString() + "분 전"
            }
            TimeMaximum.MIN.let { diffTime /= it; diffTime } < TimeMaximum.HOUR -> {
                msg = diffTime.toString() + "시간 전"
            }
            TimeMaximum.HOUR.let { diffTime /= it; diffTime } < TimeMaximum.DAY -> {
                msg = diffTime.toString() + "일 전"
            }
            TimeMaximum.DAY.let { diffTime /= it; diffTime } < TimeMaximum.MONTH -> {
                msg = diffTime.toString() + "달 전"
            }
            else -> {
                msg = diffTime.toString() + "년 전"
            }
        }
    }else{
        msg = ""
    }

    return msg
}