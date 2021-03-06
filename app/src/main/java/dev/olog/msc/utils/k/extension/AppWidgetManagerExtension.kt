package dev.olog.msc.utils.k.extension

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context

fun Context.getAppWidgetsIdsFor(clazz: Class<*>): IntArray {
    return AppWidgetManager.getInstance(this).getAppWidgetIds(
            ComponentName(this, clazz))
}