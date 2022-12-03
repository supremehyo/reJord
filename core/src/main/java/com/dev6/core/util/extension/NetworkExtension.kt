package com.jydev.rest_api_util.extension

import com.dev6.core.exception.*
import com.dev6.core.util.extension.HandleServerStatus

suspend fun <DATA> DATA?.executeErrorHandling(
    handleServerStatus: HandleServerStatus?
): DATA {
    handleServerStatus?.let {
        val serverFailResult = handleServerStatus.isServerFail()
        val alreadyExit = handleServerStatus.isAleadyExit()
        val notAutorutyResult = handleServerStatus.isNotAutority()
        val notCorrect = handleServerStatus.isNotCorrect()
        return when {
            serverFailResult.status -> {
                throw ServerFailException(serverFailResult.message)
            }
            notAutorutyResult.status -> {
                throw NotAutorityException(notAutorutyResult.message)
            }
            alreadyExit.status -> {
                throw AleadyExitException(alreadyExit.message)
            }
            notCorrect.status -> {
                throw NotCorrectException(notCorrect.message)
            }
            else -> this ?: throw NotFormatingMethod("리스폰스 없음")
        }
    } ?: kotlin.run {
        return this ?: throw NotFormatingMethod("리스폰스 없음")
    }
}
