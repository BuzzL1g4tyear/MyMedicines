package com.valentin.mymedicines.data.storage

import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.data.entity.MedicineEntity

interface MedicineStorage {

    suspend fun insert(medicine: MedicineEntity)

    suspend fun get(): List<MedicineEntity>

}