package com.demox.sbrcurrency.di

import android.content.Context
import com.demox.currency.repository.CurrencyRepository
import com.demox.currency.repository.CurrencyRepositoryImpl
import com.demox.currency.services.CurrencyService
import com.demox.shared_pref.storage.SharedPrefUserStorage
import com.demox.shared_pref.storage.UserStorage
import com.demox.user_settings.repository.SettingsRepository
import com.demox.user_settings.repository.UserSettingRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideCurrencyRepository(currencyService: CurrencyService, userStorage: UserStorage): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyService = currencyService, userStorage = userStorage)
    }

    @Singleton
    @Provides
    fun provideUserSettingRepository(userStorage: UserStorage): SettingsRepository {
        return UserSettingRepositoryImpl(userStorage = userStorage)
    }

    @Singleton
    @Provides
    fun provideUserStorage(@ApplicationContext context: Context): UserStorage {
        return SharedPrefUserStorage(context = context)
    }

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // .connectTimeout(30L, TimeUnit.SECONDS)
            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .followRedirects(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit.Builder {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://cbr.ru/scripts/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideHomeService(retrofit: Retrofit.Builder): CurrencyService {
        return retrofit.build()
            .create(CurrencyService::class.java)
    }
}
