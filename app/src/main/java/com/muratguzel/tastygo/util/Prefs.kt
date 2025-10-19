// util/Prefs.kt
package com.muratguzel.tastygo.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("user_prefs")

@Singleton
class Prefs @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val KEY_ONBOARDING_SEEN = booleanPreferencesKey("onboarding_seen")

    val onboardingSeen: Flow<Boolean> =
        context.dataStore.data.map { it[KEY_ONBOARDING_SEEN] ?: false }

    suspend fun setOnboardingSeen(value: Boolean) {
        context.dataStore.edit { it[KEY_ONBOARDING_SEEN] = value }
    }
}