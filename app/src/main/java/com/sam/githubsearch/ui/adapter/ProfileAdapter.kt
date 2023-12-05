package com.sam.githubsearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sam.githubsearch.R
import com.sam.githubsearch.data.model.ProfileData
import com.sam.githubsearch.databinding.ProfileItemBinding


class ProfileAdapter(private val onClick: (ProfileData, Int, Int) -> Unit) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private val profileList = mutableListOf<ProfileData>()

    fun updateList(list: List<ProfileData>) {
        profileList.clear()
        profileList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ProfileViewHolder(val viewBinding: ProfileItemBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val viewBinding =
            ProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profileList[position]
        holder.viewBinding.profileName.text = profile.name
        holder.viewBinding.id.text = holder.viewBinding.root.context.getString(R.string.id, profile.id)
        Glide.with(holder.viewBinding.profileImage.context)
            .load(profile.owner?.avatarUrl)
            .into(holder.viewBinding.profileImage)

        holder.viewBinding.root.setOnClickListener {
            onClick(profile, it.id, position)
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

}
