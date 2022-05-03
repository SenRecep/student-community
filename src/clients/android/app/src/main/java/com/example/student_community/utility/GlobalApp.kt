package com.example.student_community.utility

import android.app.Application
import android.content.Context

class GlobalApp : Application() {
    companion object {
        private lateinit var myContext: Context;
        public fun getAppContext(): Context {
            return myContext;
        }
    }

    override fun onCreate() {
        super.onCreate();
        myContext = this;
    }
}