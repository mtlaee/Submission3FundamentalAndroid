package com.example.submission2aplikasigithubtaufikakbar

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2aplikasigithubtaufikakbar.Adapter.LisUserAdapter
import com.example.submission2aplikasigithubtaufikakbar.ViewModel.DetailViewModel
import com.example.submission2aplikasigithubtaufikakbar.ViewModel.FollowViewModel
import com.example.submission2aplikasigithubtaufikakbar.databinding.ItemRowFragmentBinding

class FragmentFollower : Fragment(R.layout.item_row_fragment) {
    private var _binding : ItemRowFragmentBinding? = null
    private val binding get() =  _binding!!
    private lateinit var followerViewModel: FollowViewModel
    private lateinit var followerAdapter: LisUserAdapter
    private lateinit var username : String

    override fun onViewCreated(view: View, savedInstanceState:Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments
        username = requireActivity().intent.getStringExtra(DetailActivity.DETAIL_USER).toString()
        _binding= ItemRowFragmentBinding.bind(view)

        followerAdapter = LisUserAdapter()
        followerAdapter.notifyDataSetChanged()

        binding.apply {
            rvGithub.setHasFixedSize(true)
            rvGithub.layoutManager = LinearLayoutManager(activity)
            rvGithub.adapter = followerAdapter
        }
        showLoading(true)
        followerViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowViewModel::class.java)
        followerViewModel.setDataListFollower(username)
        followerViewModel.getDataLisFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                followerAdapter.setListUser(it)
                Log.d("tag", "$it")
                showLoading(false)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressBar.visibility= View.VISIBLE
        }
        else {
            binding.progressBar.visibility= View.GONE
        }
    }
}