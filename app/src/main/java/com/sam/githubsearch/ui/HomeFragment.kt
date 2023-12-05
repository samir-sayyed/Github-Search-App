package com.sam.githubsearch.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sam.githubsearch.data.model.ProfileData
import com.sam.githubsearch.databinding.FragmentHomeBinding
import com.sam.githubsearch.ui.adapter.ProfileAdapter
import com.sam.githubsearch.util.dismissLoadingDialog
import com.sam.githubsearch.util.showLoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        private const val TAG = "HomeFragment"
    }

    private var viewBinding: FragmentHomeBinding? = null
    private var profilesAdapter: ProfileAdapter? = null
    private var profilesList = listOf<ProfileData>()

    private val gitHubSearchViewModel by lazy {
        ViewModelProvider(this)[GitHubSearchViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gitHubSearchViewModel.getProfiles()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoadingDialog()
        gitHubSearchViewModel.profiles.observe(viewLifecycleOwner) {
            profilesList = it?: emptyList()
            profilesAdapter = ProfileAdapter { profileData, _, _ ->
              findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToProfileDetailsFragment(profileData.owner?.repoUrl.toString())
              )
            }
            val layoutManager = LinearLayoutManager(requireContext())
            viewBinding?.profileRecyclerView?.layoutManager = layoutManager
            viewBinding?.profileRecyclerView?.adapter = profilesAdapter
            profilesAdapter?.updateList(it ?: emptyList())
            dismissLoadingDialog()
        }
        editTextChangeListener()
    }

    private fun editTextChangeListener() {
        viewBinding?.searchBar?.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    viewBinding?.noResultsFoundTv?.visibility = View.GONE
                    viewBinding?.profileRecyclerView?.visibility = View.VISIBLE
                    profilesAdapter?.updateList(profilesList)
                } else
                    searchProfile(s.toString())
            }
        })
    }

    private fun searchProfile(keyword: String) {
        val filteredList = arrayListOf<ProfileData>()
        profilesList.forEach {
            if (it.name?.contains(keyword, true) == true
                || it.id.toString().contains(keyword, true)) {
                filteredList.add(it)
            }
        }
        if (filteredList.isEmpty()) {
            viewBinding?.noResultsFoundTv?.visibility = View.VISIBLE
            viewBinding?.profileRecyclerView?.visibility = View.GONE
        }else{
            viewBinding?.noResultsFoundTv?.visibility = View.GONE
            viewBinding?.profileRecyclerView?.visibility = View.VISIBLE
            profilesAdapter?.updateList(filteredList)
        }
    }

}