package com.aria.dansh.testsolid.smsManager.di


import android.app.Application
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import com.aria.dansh.testsolid.smsManager.data.local.MyBroadcastReceiver
import com.aria.dansh.testsolid.smsManager.data.repository.MessengerRepositoryImpl
import com.aria.dansh.testsolid.smsManager.data.worker.NotificationWorker
import com.aria.dansh.testsolid.smsManager.domain.repository.MessengerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//Todo:: Hilt Provides
@Module
@InstallIn(SingletonComponent::class)
class SmsManagerModule {

    @Provides
    @Singleton
    fun providesRepository(
        context : Application,
        broadcastReceiver: MyBroadcastReceiver,
        notificationWorker: OneTimeWorkRequest.Builder
    ): MessengerRepository = MessengerRepositoryImpl(context,broadcastReceiver,notificationWorker)

    @Provides
    @Singleton
    fun providesBroadcastReceiver(): MyBroadcastReceiver = MyBroadcastReceiver()


    @Provides
    @Singleton
    fun providesNotificationWorker() = OneTimeWorkRequestBuilder<NotificationWorker>()




}