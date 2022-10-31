package com.valentin.mymedicines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.valentin.domain.model.Item
import com.valentin.domain.model.Medicine
import javax.inject.Inject
import javax.inject.Singleton

class MedAdapter(
    private val meds: List<ItemMed<*, *>>,
) : ListAdapter<Item, BaseViewHolder<ViewBinding, Item>>(
    MedDiffUtil(meds)
) {

    var callback: BaseAdapterCallback<Item>? = null

    fun attachCallback(callback: BaseAdapterCallback<Item>) {
        this.callback = callback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return meds.find { it.getLayoutId() == viewType }
            ?.getViewHolder(inflater, parent)
            ?.let { it as BaseViewHolder<ViewBinding, Item> }
            ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(currentList[position])
        holder.itemView.setOnClickListener {
            callback?.onItemClick(currentList[position], holder.itemView)
        }
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewBinding, Item>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(currentList[position], payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return meds.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }
}