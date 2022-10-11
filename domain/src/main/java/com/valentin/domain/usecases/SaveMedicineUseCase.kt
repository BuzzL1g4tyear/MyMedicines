package com.valentin.domain.usecases

import com.valentin.domain.model.Medicine
import com.valentin.domain.domain.repository.MedicineRepositoryInterface

class SaveMedicineUseCase(private val repository: MedicineRepositoryInterface) {
    suspend fun execute(medicine: Medicine) {
        repository.saveMedicine(medicine = medicine)
    }
}