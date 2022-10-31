package com.valentin.mymedicines.presentation.fragments.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valentin.domain.model.Medicine
import com.valentin.domain.usecases.SaveMedicineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val saveMedicineUseCase: SaveMedicineUseCase
) : ViewModel() {

    private val nameMedLiveDataMutable = MutableLiveData<String>()
    private val dateMedLiveDataMutable = MutableLiveData<String>()
    val nameMedLiveData: LiveData<String> = nameMedLiveDataMutable
    val dateMedLiveData: LiveData<String> = dateMedLiveDataMutable

    suspend fun save(newMedicine: Medicine) {
        saveMedicineUseCase.execute(medicine = newMedicine)
        nameMedLiveDataMutable.postValue(newMedicine.name)
        dateMedLiveDataMutable.postValue(newMedicine.date)
    }
}