package com.example.submission2aplikasigithubtaufikakbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2aplikasigithubtaufikakbar.Adapter.LisUserAdapter
import com.example.submission2aplikasigithubtaufikakbar.Data.FavoriteUser
import com.example.submission2aplikasigithubtaufikakbar.Response.DataUser
import com.example.submission2aplikasigithubtaufikakbar.ViewModel.FavoriteViewModel
import com.example.submission2aplikasigithubtaufikakbar.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favadapter: LisUserAdapter
    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite"
        favadapter = LisUserAdapter()
        favadapter.notifyDataSetChanged()

        favViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        favadapter.setOnItemClickCallback(object : LisUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataUser) {
                Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.DETAIL_USER, data.login)
                    it.putExtra(DetailActivity.DETAIL_ID, data.id)
                    it.putExtra(DetailActivity.DETAIL_AVATAR, data.avatar_url)
                    startActivity(it)
                }
            }
        })
        binding.apply {
            rvGithubFav.setHasFixedSize(true)
            rvGithubFav.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvGithubFav.adapter = favadapter
        }
        favViewModel.getFavoriteDataUser()?.observe(this) {
            if (it != null) {
                val list = mapList(it)
                favadapter.setListUser(list)
            }
        }
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<DataUser> {
        val listUsers = ArrayList<DataUser>()
        for (user in users) {
            val userMapped = DataUser(
                user.login,
                user.id,
                user.avatar_url
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}