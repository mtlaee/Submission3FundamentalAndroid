package com.example.submission2aplikasigithubtaufikakbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2aplikasigithubtaufikakbar.Adapter.LisUserAdapter
import com.example.submission2aplikasigithubtaufikakbar.DarkMode.DarkModeActivity
import com.example.submission2aplikasigithubtaufikakbar.Response.DataUser
import com.example.submission2aplikasigithubtaufikakbar.ViewModel.UserSearchViewModel
import com.example.submission2aplikasigithubtaufikakbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var searchViewModel : UserSearchViewModel
    private lateinit var listadapter: LisUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Github User"
        listadapter = LisUserAdapter()
        listadapter.notifyDataSetChanged()
        listadapter.setOnItemClickCallback(object : LisUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data:DataUser) {
                Intent(this@MainActivity,DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.DETAIL_USER, data.login)
                    it.putExtra(DetailActivity.DETAIL_ID, data.id)
                    it.putExtra(DetailActivity.DETAIL_AVATAR, data.avatar_url)
                    startActivity(it)
                }
            }
        })
        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserSearchViewModel::class.java)
        binding.apply {
            rvGithub.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGithub.setHasFixedSize(true)
            rvGithub.adapter = listadapter

            searchButton.setOnClickListener {
                searchUser()
            }
            searchQuery.setOnKeyListener { v, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        searchViewModel.getDataUser().observe(this, {
            if (it != null) {
                listadapter.setListUser(it)
                showLoading(false)
            }
        })
    }

    private fun searchUser() {
        binding.apply {
            val query = searchQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            searchViewModel.setDataUser(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                val i = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(i)
                return true
            }
            R.id.menu2->{
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.menu3->{

                val i = Intent(this, DarkModeActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progresBar.visibility = View.VISIBLE
        } else {
            binding.progresBar.visibility = View.GONE
        }
    }
}