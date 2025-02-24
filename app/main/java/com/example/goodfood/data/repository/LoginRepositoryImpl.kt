package com.example.goodfood.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.goodfood.domain.repository.LoginRepository
import com.example.goodfood.domain.service.GoodFoodApiService
import com.example.goodfood.domain.service.LoginRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginRepositoryImpl(context: Context) : PrefsDataStore(context = context, PREF_LOGIN_STATE), LoginRepository  {
    private val apiService = GoodFoodApiService()

    companion object {
        const val PREF_LOGIN_STATE = "user_login_state_pref"
        private val LOGIN_STATE_KEY = booleanPreferencesKey("user_login_state")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_FIRST_NAME_KEY = stringPreferencesKey("user_first_name")
        private val USER_LAST_NAME_KEY = stringPreferencesKey("user_last_name")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        private val USER_TYPE = stringPreferencesKey("user_type")
        private val USER_CITY = stringPreferencesKey("user_city")
    }

    override val loginState: Flow<Boolean>
        get() = mDataStore.data.map { preferences ->
            val uiMode = preferences[LOGIN_STATE_KEY] ?: false
            uiMode
        }

    override suspend fun toggleLoginState(email: String, password: String): Int {
        val request = LoginRequest(email, password)
        val response = apiService.api.login(request)

        if (response.isSuccessful) {
            val loginResponse = response.body()
            if (loginResponse != null) {
                mDataStore.edit { preferences ->
                    val loginState = loginResponse.jwt.isNotEmpty()

                    preferences[LOGIN_STATE_KEY] = loginState
                    preferences[USER_ID_KEY] = loginResponse.user.userId
                    preferences[USER_FIRST_NAME_KEY] = loginResponse.user.firstName
                    preferences[USER_LAST_NAME_KEY] = loginResponse.user.lastName
                    preferences[USER_EMAIL_KEY] = loginResponse.user.email
                    preferences[USER_TYPE] = loginResponse.user.userType
                    preferences[USER_CITY] = loginResponse.user.city
                }
            }
        }

        return response.code()
    }

    override suspend fun logout() {
        mDataStore.edit { preferences ->
            preferences[LOGIN_STATE_KEY] = false
        }
    }

    override val userid: Flow<String>
        get() = mDataStore.data.map { preferences ->
            preferences[USER_ID_KEY] ?: ""
        }

    override val firstName: Flow<String>
        get() = mDataStore.data.map { preferences ->
            preferences[USER_FIRST_NAME_KEY] ?: ""
        }

    override val lastName: Flow<String>
        get() = mDataStore.data.map { preferences ->
            preferences[USER_LAST_NAME_KEY] ?: ""
        }

    override val email: Flow<String>
        get() = mDataStore.data.map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }
    override val userType: Flow<String>
        get() = mDataStore.data.map { preferences ->
            preferences[USER_TYPE] ?: ""
        }

    override val city: Flow<String>
        get() = mDataStore.data.map { preferences ->
            preferences[USER_CITY] ?: ""
        }
}

abstract class PrefsDataStore(context: Context, fileName: String) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(fileName)
    val mDataStore: DataStore<Preferences> = context.dataStore
}
