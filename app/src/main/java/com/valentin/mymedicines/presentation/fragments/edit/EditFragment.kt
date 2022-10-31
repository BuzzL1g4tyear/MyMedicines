package com.valentin.mymedicines.presentation.fragments.edit

import android.os.Bundle
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.transition.platform.MaterialContainerTransform
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
    private val editViewModel: EditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onStart() {
        super.onStart()

        editViewModel.nameMedLiveData.observe(this) {
            binding.nameMedicine.setText(it)
        }
        editViewModel.dateMedLiveData.observe(this) {
            binding.dateMedicine.setText(it)
        }

        binding.FABConfirmNewMedicine.setOnClickListener {

            val nameMedicine = binding.nameMedicine.text.toString()
            val dateMedicine = binding.dateMedicine.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val newMedicine = Medicine(
                    name = nameMedicine, date = dateMedicine
                )
                editViewModel.save(newMedicine = newMedicine)
            }
        }
    }
}