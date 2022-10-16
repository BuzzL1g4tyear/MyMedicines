package com.valentin.mymedicines.presentation.fragments.edit

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.R
import com.valentin.mymedicines.databinding.FragmentEditBinding
import com.valentin.mymedicines.presentation.fragments.baseFragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditFragment : BaseFragment<FragmentEditBinding>(R.layout.fragment_edit) {

    override val binding: FragmentEditBinding by viewBinding()

    private val editFrViewModel: EditFrViewModel by viewModels()

    override fun onStart() {
        super.onStart()

        editFrViewModel.nameMedLiveData.observe(this) {
            binding.nameMedicine.setText(it)
        }
        editFrViewModel.dateMedLiveData.observe(this) {
            binding.dateMedicine.setText(it)
        }

        binding.FABConfirmNewMedicine.setOnClickListener {

            val nameMedicine = binding.nameMedicine.text.toString()
            val dateMedicine = binding.dateMedicine.text.toString()

            val newMedicine = Medicine(
                name = nameMedicine,
                date = dateMedicine
            )

            CoroutineScope(Dispatchers.IO).launch {
                editFrViewModel.save(newMedicine = newMedicine)
            }
        }
    }
}