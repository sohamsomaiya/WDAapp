package com.example.wda

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.core.view.ViewCompat
import java.util.*

object LocaleHelper {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    private const val APP_PREFERENCES = "MyAppPreferences"
    fun persistSelectedLanguage(context: Context, language: String) {
        val preferences = context.getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    fun setLocale(context: Context, language: String): Context {
        persistSelectedLanguage(context, language)

        // Updating the language for devices above Android Nougat
        return updateResources(context, language)
    }

    // The method is used to update the language of the application by creating
    // an object of the built-in Locale class and passing the language argument to it
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.setLocale(locale)

        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }
    // Fetch the selected language from SharedPreferences
    fun getSelectedLanguage(context: Context): String {
        val preferences = context.getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
        return preferences.getString(SELECTED_LANGUAGE, "en") ?: "en"
    }
}
