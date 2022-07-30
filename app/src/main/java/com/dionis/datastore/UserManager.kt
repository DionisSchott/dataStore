package com.dionis.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

    private val Context.dataUser: DataStore<Preferences> by preferencesDataStore(name = "user")

class UserManager(val context: Context) {


    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
        private val USER_SECONDNAME_KEY = stringPreferencesKey("USER_SECONDNAME")
        private val USER_AUTHENTICATED_KEY = booleanPreferencesKey("USER_AUTHENTICATED")

    }


    suspend fun saveDataUser(name: String, secondName: String, authenticated: Boolean) {
        context.dataUser.edit {
            it[USER_NAME_KEY] = name
            it[USER_SECONDNAME_KEY] = secondName
            it[USER_AUTHENTICATED_KEY] = authenticated
        }

    }

    suspend fun readDataUser(): User {

        val prefs = context.dataUser.data.first()

        return User(
            name = prefs[USER_NAME_KEY] ?: "",
            secondName = prefs[USER_SECONDNAME_KEY] ?: "",
            authenticated = prefs[USER_AUTHENTICATED_KEY] ?: false
        )

    }

    suspend fun clearDataUser(){
        context.dataUser.edit { it.clear() }
    }

}