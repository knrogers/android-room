package com.roguekingapps.bgdb.application

import android.app.Application
import com.roguekingapps.bgdb.di.ApplicationComponent
import com.roguekingapps.bgdb.di.DaggerApplicationComponent

class BGDbApplication: Application() {

    val applicationComponent: ApplicationComponent =
        DaggerApplicationComponent.factory()
            .create(application = this, context = this)

}