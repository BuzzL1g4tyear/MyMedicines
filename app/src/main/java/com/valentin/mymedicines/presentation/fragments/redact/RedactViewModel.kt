package com.valentin.mymedicines.presentation.fragments.redact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valentin.domain.model.Medicine
import com.valentin.domain.usecases.UpdateMedicineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RedactViewModel @Inject constructor(
    private val updateMedicineUseCase: UpdateMedicineUseCase
) : ViewModel() {
    private val nameMedLiveDataMutable = MutableLiveData<String>()
    private val dateMedLiveDataMutable = MutableLiveData<String>()

    suspend fun update(medicine: Medicine) {
        updateMedicineUseCase.execute(medicine = medicine)
        nameMedLiveDataMutable.postValue(medicine.name)
        dateMedLiveDataMutable.postValue(medicine.date)
    }
}