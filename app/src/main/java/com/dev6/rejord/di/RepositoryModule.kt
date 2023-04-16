package com.dev6.rejord.di
import com.dev6.domain.repository.*
import com.dev6.repositoryImple.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsJoinRepository(repository: JoinRepositoryImple): JoinRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsLoginRepository(repository: LoginRepositoryImple): LoginRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsPostRepository(repository: PostRepositoryImple): PostRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsBannerRepository(repository: BannerRepositoryImple): BannerRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsMyPageRepository(repository: MyPageRepositroyImple) : MyPageRepository

}
