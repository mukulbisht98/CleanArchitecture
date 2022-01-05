package com.xxmukulxx.notes.common.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.xxmukulxx.notes.BR
import com.xxmukulxx.notes.R

class RecyclerAdapter<T>(
    private val items: MutableList<T>,
    @LayoutRes val layoutId: Int,
    val itemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.VH<T>>() {
    private val animatedPosition: HashSet<Int> by lazy { HashSet() }
    private var inflater: LayoutInflater? = null
    var isAnimation = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH<T> {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH<T>, position: Int) {
        val model = items[position]

        holder.itemView.setOnClickListener {
            itemClicked(position)
        }
        holder.bind(model)
        if (isAnimation)
            setAnimation(holder, position)
    }

    private fun setAnimation(holder: RecyclerView.ViewHolder, position: Int) {
        if (this.animatedPosition.contains(Integer.valueOf(position))) {
            holder.itemView.clearAnimation()
            return
        }
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.anim_slide_from_bottom
            )
        )
        this.animatedPosition.add(Integer.valueOf(position))
    }

    class VH<T>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: T) {
            binding.setVariable(BR.recyclerData, model)
            binding.executePendingBindings()
        }
    }
}