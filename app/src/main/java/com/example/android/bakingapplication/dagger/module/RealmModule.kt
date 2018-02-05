package com.example.android.bakingapplication.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class RealmModule {

    @Provides
    @Singleton
    fun provideRealmInstance(application: Application): Realm {
        Realm.init(application)
        val builder = RealmConfiguration.Builder()
        builder.deleteRealmIfMigrationNeeded()
                .name("recipeDB3.realm")

        val configuration = builder.build()

        return Realm.getInstance(configuration)
    }
}
