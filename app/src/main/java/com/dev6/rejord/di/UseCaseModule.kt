package com.dev6.rejord.di
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.usecase.JoinReposBaseUseCase
import com.dev6.domain.usecase.JoinUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/*
@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideJoinUsecase(
        repository: JoinRepository
    ): JoinReposBaseUseCase =
        JoinUseCase(repository)


}

 */
