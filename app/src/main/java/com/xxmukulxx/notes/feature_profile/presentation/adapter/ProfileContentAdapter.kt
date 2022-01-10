package com.xxmukulxx.notes.feature_profile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxmukulxx.notes.BR
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.presentation.adapter.RecyclerAdapter
import com.xxmukulxx.notes.databinding.ItemProfileContentBinding
import com.xxmukulxx.notes.feature_profile.domain.model.ProfileContent
import com.xxmukulxx.notes.util.toast

class ProfileContentAdapter(private val arr: MutableList<ProfileContent>) :
    RecyclerView.Adapter<ProfileContentAdapter.ViewHolder>() {

    inner class ViewHolder(private val b: ItemProfileContentBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(model: ProfileContent) {
            b.setVariable(BR.recyclerData, model)
            b.executePendingBindings()
            b.rvDataList.apply {
                adapter = RecyclerAdapter(
                    model.productsList, R.layout.item_profile_content_data
                ) {
                    toast(model.productsList[it].title)
                }.apply {
                    isAnimation = true
                }
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileContentAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_profile_content,
            parent,
            false
        )
        return ViewHolder(binding as ItemProfileContentBinding)
    }

    override fun onBindViewHolder(holder: ProfileContentAdapter.ViewHolder, position: Int) {
        holder.bind(arr[position])
    }

    override fun getItemCount(): Int = arr.size
}