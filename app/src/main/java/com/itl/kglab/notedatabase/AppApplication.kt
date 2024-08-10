package com.itl.kglab.notedatabase

import android.app.Application
import com.itl.kglab.notedatabase.db.AppDatabase

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.invoke(this)
    }

}