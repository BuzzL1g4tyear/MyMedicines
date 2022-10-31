package com.valentin.mymedicines.presentation.redact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.R
import com.valentin.mymedicines.databinding.FragmentRedactBinding
import com.valentin.mymedicines.presentation.fragments.baseFragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RedactFragment(val medicine: Medicine) :
    BaseFragment<FragmentRedactBinding>(R.layout.fragment_redact) {

    override val binding: FragmentRedactBinding by viewBinding()
    private val redactViewModel: RedactViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.redactNameMedicine.setText(medicine.name)
        binding.redactDateMedicine.setText(medicine.date)
    }

    override fun onStart() {
        super.onStart()
        binding.FABConfirmRedactMedicine.setOnClickListener {
            val name = binding.redactNameMedicine.text.toString()
            val date = binding.redactDateMedicine.text.toString()

            CoroutineScope(Dispatchers.IO).launch {

                val med = Medicine(id = medicine.id, name = name, date = date)

                redactViewModel.update(medicine = med)
            }

        }
    }
}