package com.example.android.bakingapplication.dagger.module

import com.example.android.bakingapplication.dagger.qualifier.Local
import com.example.android.bakingapplication.dagger.qualifier.Network
import com.example.android.bakingapplication.repository.RecipeRepository
import com.example.android.bakingapplication.repository.RecipeRepositoryImpl
import com.example.android.bakingapplication.repository.local.LocalDataSource
import com.example.android.bakingapplication.repository.remote.NetworkDataSource
import dagger.Module
import dagger.Provides
import io.realm.Realm
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RecipeRepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(@Local databaseSource: RecipeRepository, @Network networkSource: RecipeRepository): RecipeRepository {
        return RecipeRepositoryImpl(databaseSource, networkSource)
    }

    @Singleton
    @Provides
    @Local
    fun provideRecipeDatabaseSource(realm: Realm): RecipeRepository {
        return LocalDataSource(realm)
    }

    @Singleton
    @Provides
    @Network
    fun provideRecipeNetworkSource(retrofit: Retrofit): RecipeRepository {
        return NetworkDataSource(retrofit)
    }
}
