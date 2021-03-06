package dev.olog.msc.utils.k.extension

import com.crashlytics.android.Crashlytics
import dev.olog.msc.BuildConfig

fun Throwable.printStackTraceOnDebug(){
    if (BuildConfig.DEBUG){
        this.printStackTrace()
    }
}

fun Throwable.logStackStace(){
    if (!BuildConfig.DEBUG){
        try {
            Crashlytics.logException(this)
        } catch (ex: Exception){}
    }
    this.printStackTrace()
}