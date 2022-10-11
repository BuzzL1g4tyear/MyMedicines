package com.valentin.mymedicines.presentation.fragments.edit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.valentin.data.data.storage.room.RoomMedicineStorage
import com.valentin.domain.usecases.SaveMedicineUseCase
import com.valentin.mymedicines.data.repository.MedicineRepository

class editFrViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val storage by lazy { RoomMedicineStorage(context = context) }
    private val medicineRepository by lazy { MedicineRepository(storage = storage) }
    private val saveMedicineUseCase by lazy { SaveMedicineUseCase(repository = medicineRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditFrViewModel(saveMedicineUseCase = saveMedicineUseCase) as T
    }
}