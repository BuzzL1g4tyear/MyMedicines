package com.valentin.mymedicines.presentation.fragments.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.valentin.data.data.storage.room.RoomMedicineStorage
import com.valentin.domain.model.Medicine
import com.valentin.domain.usecases.SaveMedicineUseCase
import com.valentin.mymedicines.data.repository.MedicineRepository

class EditFrViewModel(
//    private val
    private val saveMedicineUseCase: SaveMedicineUseCase
) : ViewModel() {

    private val nameMedLiveDataMutable = MutableLiveData<String>()
    private val dateMedLiveDataMutable = MutableLiveData<String>()
    val nameMedLiveData: LiveData<String> = nameMedLiveDataMutable
    val dateMedLiveData: LiveData<String> = dateMedLiveDataMutable

    suspend fun save(newMedicine: Medicine) {
        saveMedicineUseCase.execute(medicine = newMedicine)
        nameMedLiveDataMutable.value = newMedicine.name
        dateMedLiveDataMutable.value = newMedicine.date
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val storage = RoomMedicineStorage(context = )
                val repository = MedicineRepository(storage = storage)
                val saveMedicineUseCase = SaveMedicineUseCase(repository = repository)
                EditFrViewModel(
                    saveMedicineUseCase = saveMedicineUseCase
                )
            }
        }
    }
}