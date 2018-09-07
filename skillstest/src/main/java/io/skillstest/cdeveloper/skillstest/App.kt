package io.skillstest.cdeveloper.skillstest

import android.app.Application
import io.skillstest.cdeveloper.data.di.RepositoryModule
import io.skillstest.cdeveloper.skillstest.di.AppModule
import io.skillstest.cdeveloper.skillstest.di.DaggerIAppComponent
import io.skillstest.cdeveloper.skillstest.di.IAppComponent
import io.skillstest.cdeveloper.skillstest.di.InteractorsModule

class App : Application() {

    private lateinit var component: IAppComponent

    override fun onCreate() {
        super.onCreate()

        initializeInjector()
    }

    fun initializeInjector() {
        component = DaggerIAppComponent.builder()
                .appModule(AppModule(this))
                .repositoryModule(RepositoryModule())
                .interactorsModule(InteractorsModule())
                .build()
    }

    fun component() = component

}