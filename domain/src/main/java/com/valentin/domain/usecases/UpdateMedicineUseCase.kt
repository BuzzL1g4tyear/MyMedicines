package com.valentin.domain.usecases

import com.valentin.domain.domain.repository.MedicineRepositoryInterface
import com.valentin.domain.model.Medicine

class UpdateMedicineUseCase(private val repository: MedicineRepositoryInterface) {
    suspend fun execute(medicine: Medicine) {
        repository.updateMedicine(medicine = medicine)
    }
}