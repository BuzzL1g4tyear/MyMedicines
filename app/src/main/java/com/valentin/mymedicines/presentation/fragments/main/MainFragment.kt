package com.valentin.mymedicines.presentation.fragments.main

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.valentin.data.data.storage.room.RoomMedicineStorage
import com.valentin.domain.usecases.GetMedicineUseCase
import com.valentin.mymedicines.R
import com.valentin.mymedicines.data.repository.MedicineRepository
import com.valentin.mymedicines.databinding.FragmentMainBinding
import com.valentin.mymedicines.presentation.fragments.baseFragment.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override val binding: FragmentMainBinding by viewBinding()

    private val contextFragment by lazy { requireContext() }
    private val storage by lazy { RoomMedicineStorage(context = contextFragment) }
    private val medicineRepository by lazy { MedicineRepository(storage = storage) }
    private val getMedicineUseCase by lazy { GetMedicineUseCase(repository = medicineRepository) }

    override fun onStart() {
        super.onStart()

        CoroutineScope(Dispatchers.IO).launch {
            getMedicineUseCase.execute()
        }

        binding.FABAddNewMedicine.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_editFragment)
        }
    }
}