package com.dev6.rejord.di
import com.dev6.network.LoginAPI
import com.dev6.remote.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsJoinRemoteSource(source: JoinRemoteDataSourceImpl): JoinRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindsLoginRemoteSource(source: LoginRemoteDataSourceImpl): LoginRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindsPostRemoteSource(source: PostRemoteDataSourceImpl): PostRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindsBannerRemoteSource(source: BannerRemoteDataSourceImpl): BannerRemoteDataSource

    @Singleton
    @Binds
    abstract fun myPageRemoteSource(source : MyPageRemoteDataSourceImpl) :MyPageRemoteDataSource
}
