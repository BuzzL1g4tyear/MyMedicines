package com.valentin.domain.domain.repository

import com.valentin.domain.model.Medicine

interface MedicineRepositoryInterface {

    suspend fun saveMedicine(medicine: Medicine)

    suspend fun getMedicine(): List<Medicine>

    suspend fun updateMedicine(medicine: Medicine)

    suspend fun deleteMedicine(medicine: Medicine)
}