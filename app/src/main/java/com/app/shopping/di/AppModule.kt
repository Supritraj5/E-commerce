package com.app.shopping.di

import android.content.Context
import androidx.room.Room
import com.app.shopping.data.local.dao.CartDAO
import com.app.shopping.data.local.dao.ProductDAO
import com.app.shopping.data.local.database.AppDatabase
import com.app.shopping.data.remote.ApiService
import com.app.shopping.data.repository.CartRepository
import com.app.shopping.data.repository.ProductRepository
import com.app.shopping.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(
                OkHttpClient().newBuilder().build()
            ).build()
    }

    //retrofit api service
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    //room DB
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration().build()
    }

    //cart dao
    @Provides
    @Singleton
    fun provideCartDao(appDatabase: AppDatabase) = appDatabase.cartDao()


    //product dao
    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase) = appDatabase.productDao()


    //cart repo
    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDAO): CartRepository {
        return CartRepository(cartDao)
    }

    //product repo
    @Provides
    @Singleton
    fun provideProductRepository(
        productDao: ProductDAO,
        apiService: ApiService
    ): ProductRepository {
        return ProductRepository(productDao, apiService)
    }

}