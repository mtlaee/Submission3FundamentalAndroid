package com.example.submission2aplikasigithubtaufikakbar.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2aplikasigithubtaufikakbar.Api.ApiConfig
import com.example.submission2aplikasigithubtaufikakbar.Response.DataUser
import com.example.submission2aplikasigithubtaufikakbar.Response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSearchViewModel : ViewModel() {

    val listDataUser = MutableLiveData<ArrayList<DataUser>>()
    fun setDataUser(query: String){
        ApiConfig.apiInstance
            .searchuser(query)
            .enqueue(object : Callback<ResponseUser>{
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                )
                    {
                        if (response.isSuccessful)
                            {
                                listDataUser.postValue(response.body()?.items)
                            }
                    }
                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.d("Data Failure", t.message.toString())
                }
            })
    }

    fun getDataUser(): LiveData<ArrayList<DataUser>> {
        return listDataUser
    }
}