package com.roguekingapps.bgdb.application

import android.app.Application
import com.roguekingapps.bgdb.application.di.ApplicationComponent
import com.roguekingapps.bgdb.application.di.DaggerApplicationComponent

class BGDbApplication: Application() {

    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)

}