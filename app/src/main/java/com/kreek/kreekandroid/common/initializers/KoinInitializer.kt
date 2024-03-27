package com.kreek.kreekandroid.common.initializers

import android.content.Context
import androidx.startup.Initializer
import com.kreek.kreekandroid.common.koin.appModule
import com.example.moviesapptask.common.koin.dataSourceModule
import com.kreek.kreekandroid.common.koin.repositoryModule
import com.kreek.kreekandroid.common.koin.useCaseModule
import com.kreek.kreekandroid.common.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context)
            modules(
            //    coroutineDispatchersModule,
                appModule,
                viewModelModule,
                repositoryModule,
                dataSourceModule,
                useCaseModule,
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}