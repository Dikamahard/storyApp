package com.dicoding.storyapp.helper

import android.content.Context
import android.util.Log
import com.dicoding.storyapp.User
import com.dicoding.storyapp.data.response.LoginResult

class UserPreferences(context: Context) {

    companion object {
         const val PREFS_NAME = "login_pref"
         const val NAME = "name"
         const val EMAIL = "email"
         const val PASSWORD = "password"
        const val IS_LOGIN = "islogin"
        const val TOKEN = "token"
        const val USER_ID = "userId"
        val TAG = "USERPREF"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

//    fun registerUser(user: User) {
//        val editor = preferences.edit()
//        editor.putString(NAME, user.name)
//        editor.putString(EMAIL, user.email)
//        editor.putString(PASSWORD, user.password)
//        editor.apply()
//    }

    fun loginUser(data: LoginResult) {
        val editor = preferences.edit()
        editor.putString(TOKEN, data.token)
        editor.putString(USER_ID, data.userId)
        editor.putString(NAME, data.name)
        editor.putBoolean(IS_LOGIN, true)
        editor.apply()
    }


    fun getUserData(key: String): String? {
        return preferences.getString(key, null)
    }

//    fun loginUser(value: Boolean) {
//        val editor = preferences.edit()
//        editor.putBoolean(IS_LOGIN, value).apply()
//    }

    fun isLogin(): Boolean {
        return preferences.getBoolean(IS_LOGIN, false)
    }

    fun logoutUser() {
        Log.d(TAG, "logoutUser: kepanggil awal")
        val editor = preferences.edit()
        editor.putString(TOKEN, null)
        editor.putString(USER_ID, null)
        editor.putString(NAME, null)
        editor.putBoolean(IS_LOGIN, false)
        editor.apply()
        Log.d(TAG, "logoutUser: kepanggil akhir")
    }

}