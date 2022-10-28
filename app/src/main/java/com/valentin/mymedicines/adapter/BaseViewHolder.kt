package com.valentin.mymedicines.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.valentin.domain.model.Item
import com.valentin.domain.model.Medicine

abstract class BaseViewHolder<out V : ViewBinding, I : Item>(
    val binding: V
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var med: I

    open fun onBind(med: I) {
        this.med = med
    }

    open fun onBind(med: I, payloads: List<Any>) {
        this.med = med
    }
}