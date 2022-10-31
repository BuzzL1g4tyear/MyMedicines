package com.valentin.domain.usecases

import com.valentin.domain.domain.repository.MedicineRepositoryInterface
import com.valentin.domain.model.Medicine

class DeleteMedicineUseCase(private val repository: MedicineRepositoryInterface){
    suspend fun execute(medicine: Medicine) {
        repository.deleteMedicine(medicine = medicine)
    }
}