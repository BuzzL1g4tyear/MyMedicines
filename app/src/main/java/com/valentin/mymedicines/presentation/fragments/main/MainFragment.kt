package com.valentin.mymedicines.presentation.fragments.main

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.valentin.mymedicines.R
import com.valentin.mymedicines.adapter.MedAdapter
import com.valentin.mymedicines.adapter.MedItem
import com.valentin.mymedicines.databinding.FragmentMainBinding
import com.valentin.mymedicines.presentation.fragments.baseFragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override val binding: FragmentMainBinding by viewBinding()
    private val mainFrViewModel: MainFrViewModel by viewModels()

    private lateinit var adapter: MedAdapter

    override fun onStart() {
        super.onStart()

        adapter = MedAdapter(getMedicines())

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = this@MainFragment.adapter
        }

        CoroutineScope(Dispatchers.Main).launch{

            adapter.submitList(mainFrViewModel.getList().value)
        }

        binding.FABAddNewMedicine.setOnClickListener {
            onFABClick(it)
        }
    }

    private fun getMedicines() = listOf(
        MedItem()
    )

    private fun onFABClick(view: View) {
        val extras = FragmentNavigatorExtras(view to "shared_container")
        findNavController().navigate(
            resId = R.id.action_mainFragment_to_editFragment,
            args = null,
            navOptions = null,
            navigatorExtras = extras
        )
    }
}