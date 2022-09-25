package com.example.submission2aplikasigithubtaufikakbar.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.submission2aplikasigithubtaufikakbar.Data.DbUser
import com.example.submission2aplikasigithubtaufikakbar.Data.FavoriteUser
import com.example.submission2aplikasigithubtaufikakbar.Data.FavoriteUserDAO

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var user_DAO : FavoriteUserDAO?
    private var user_DB: DbUser?

    init {
        user_DB = DbUser.getDB(application)
        user_DAO = user_DB?.favoriteUserDao()
    }
    fun getFavoriteDataUser(): LiveData<List<FavoriteUser>>? {
        return user_DAO?.getFavoriteUser()
    }
}