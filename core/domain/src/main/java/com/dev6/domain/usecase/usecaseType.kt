package com.dev6.domain.usecase
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.challenge.ChallengeReadReq
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.model.join.login.LoginRes
import com.dev6.domain.model.join.nickName.NicknameExistCheckRes
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.domain.model.join.nickName.NicknameUpdateRes
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.model.post.write.PostWriteRes
import kotlinx.coroutines.flow.Flow


typealias LoginReposBaseUseCase = BaseUseCase<LoginReq, Flow<UiState<LoginRes>>>
typealias JoinReposBaseUseCase = BaseUseCase<JoinReq, Flow<UiState<JoinRes>>>
typealias NicknameExistCheckBaseUseCase = BaseUseCase<String, Flow<UiState<NicknameExistCheckRes>>>
typealias JoinUpdateReposBaseUseCase = BaseUseCase<Pair<NicknameReq,String>, Flow<UiState<NicknameUpdateRes>>>
typealias PostGetListReposBaseUseCase = BaseUseCase<PostReadReq, Flow<UiState<PostReadRes>>>
typealias ChallengeGetListReposBaseUseCase = BaseUseCase<ChallengeReadReq, Flow<UiState<ChallengeRes>>>
typealias PostWriteBaseUseCase = BaseUseCase<PostWriteReq, Flow<UiState<PostWriteRes>>>

