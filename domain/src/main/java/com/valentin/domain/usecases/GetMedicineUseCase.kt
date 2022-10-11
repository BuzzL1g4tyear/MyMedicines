package com.valentin.domain.usecases

import com.valentin.domain.domain.repository.MedicineRepositoryInterface
import com.valentin.domain.model.Medicine

class GetMedicineUseCase(private val repository: MedicineRepositoryInterface) {

    suspend fun execute(): List<Medicine> {
        return repository.getMedicine()
    }
}