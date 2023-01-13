package com.dev6.domain.usecase
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.model.join.nickName.NicknameExistCheckRes
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.domain.model.join.nickName.NicknameUpdateRes
import kotlinx.coroutines.flow.Flow


typealias JoinReposBaseUseCase = BaseUseCase<JoinReq, Flow<UiState<JoinRes>>>
typealias NicknameExistCheckBaseUseCase = BaseUseCase<String, Flow<UiState<NicknameExistCheckRes>>>
typealias JoinUpdateReposBaseUseCase = BaseUseCase<Pair<NicknameReq,String>, Flow<UiState<NicknameUpdateRes>>>
