package com.valentin.domain.domain.repository

import com.valentin.domain.model.Medicine

interface MedicineRepositoryInterface {

    suspend fun saveMedicine(medicine: Medicine)

    suspend fun getMedicine(): List<Medicine>

}