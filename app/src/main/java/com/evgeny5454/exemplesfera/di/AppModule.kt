package com.evgeny5454.exemplesfera.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.data.db.AppDao
import com.evgeny5454.exemplesfera.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDBInstance(context)
    }

    @Singleton
    @Provides
    fun provideAppDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.getAppDao()
    }

    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
}