package com.valentin.mymedicines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.valentin.domain.model.Item
import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.R
import com.valentin.mymedicines.databinding.LayoutMedicineBinding

class MedItem : ItemMed<LayoutMedicineBinding, Medicine> {

    override fun isRelativeItem(item: Item) = item is Medicine

    override fun getLayoutId() = R.layout.layout_medicine

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<LayoutMedicineBinding, Medicine> {
        val binding = LayoutMedicineBinding.inflate(layoutInflater, parent, false)
        return MedViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<Medicine>() {
        override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine) =
            oldItem.name == newItem.name && oldItem.date == newItem.date

        override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine) = oldItem == newItem

//        override fun getChangePayload(oldItem: Medicine, newItem: Medicine): Any? {
//            if (oldItem.isSaved != newItem.isSaved) return newItem.isSaved
//            return super.getChangePayload(oldItem, newItem)
//        }
    }

}

class MedViewHolder(
    binding: LayoutMedicineBinding
) : BaseViewHolder<LayoutMedicineBinding, Medicine>(binding) {

//    init {
//        binding.tbLike.setOnClickListener {
//            if (bindingAdapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
//            onSavePost(item)
//        }
//    }

    override fun onBind(med: Medicine) {
        super.onBind(med)
        with(binding) {
            medicineNameLayout.text = med.name
            medicineDateLayout.text = med.date
        }
    }

//    override fun onBind(item: Medicine, payloads: List<Any>) {
//        super.onBind(item, payloads)
//        val isSaved = payloads.last() as Boolean
//        binding.is.setChecked(isSaved)
//    }
//
//    private fun ImageView.setChecked(isChecked: Boolean) {
//        val icon = when (isChecked) {
//            true -> R.drawable.ic_bookmark_fill_24
//            false -> R.drawable.ic_bookmark_border_24
//        }
//        setImageResource(icon)
//    }
}