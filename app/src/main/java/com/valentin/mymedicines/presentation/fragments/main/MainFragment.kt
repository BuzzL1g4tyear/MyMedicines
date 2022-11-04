package com.valentin.mymedicines.presentation.fragments.main

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.valentin.domain.model.Item
import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.R
import com.valentin.mymedicines.adapter.BaseAdapterCallback
import com.valentin.mymedicines.adapter.MedAdapter
import com.valentin.mymedicines.adapter.MedItem
import com.valentin.mymedicines.adapter.anim.*
import com.valentin.mymedicines.databinding.FragmentMainBinding
import com.valentin.mymedicines.presentation.fragments.baseFragment.BaseFragment
import com.valentin.mymedicines.presentation.fragments.redact.RedactFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override val binding: FragmentMainBinding by viewBinding()
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var adapter: MedAdapter
    private lateinit var list: MutableList<Item>

    override fun onStart() {
        super.onStart()

        adapter = MedAdapter(getMedicines())

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = this@MainFragment.adapter
            itemAnimator = AddableItemAnimator(SimpleCommonAnimator()).also { animator ->
                animator.addViewTypeAnimation(R.layout.layout_medicine, SlideInLeftCommonAnimator())
                animator.addDuration = 200L
                animator.removeDuration = 300L
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitList(mainViewModel.getList().value)
            list = mainViewModel.getList().value!!
        }

        initSwipeToDelete()

        binding.FABAddNewMedicine.setOnClickListener {
            onFABClick(it)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.attachCallback(object : BaseAdapterCallback<Item> {
            override fun onItemClick(model: Item, view: View) {
                model as Medicine
                replaceFragment(RedactFragment(medicine = model))
            }
        })
    }

    private fun getMedicines() = listOf(
        MedItem()
    )

    private fun onFABClick(view: View) {
        val extras = FragmentNavigatorExtras(view to "shared_element_container")
        findNavController().navigate(
            resId = R.id.action_mainFragment_to_editFragment,
            args = null,
            navOptions = null,
            navigatorExtras = extras
        )
    }

    private fun initSwipeToDelete() {
        val onItemSwipedToDelete = { positionForRemove: Int ->
            val removedItem = list[positionForRemove]
            list.removeAt(positionForRemove)
            adapter.submitList(list.toList())

            showRestoreItemSnackBar(positionForRemove, removedItem)

        }
        val swipeToDeleteCallback = SwipeToDelete(onItemSwipedToDelete)
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.recyclerView)
    }

    private fun showRestoreItemSnackBar(position: Int, item: Item) {
        item as Medicine
        Snackbar.make(
            binding.recyclerView,
            "${item.name} ${requireContext().resources.getString(R.string.snack_description)}",
            Snackbar.LENGTH_LONG
        ).setAction(requireContext().resources.getString(R.string.snack_undo)) {
            list.add(position, item)
            adapter.submitList(list.toList())
        }.show()
    }
}