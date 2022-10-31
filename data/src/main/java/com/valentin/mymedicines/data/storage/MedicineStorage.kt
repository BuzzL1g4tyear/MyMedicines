package com.valentin.mymedicines.data.storage

import com.valentin.mymedicines.data.entity.MedicineEntity

interface MedicineStorage {

    suspend fun get(): List<MedicineEntity>

    suspend fun insert(medicine: MedicineEntity)

    suspend fun update(medicine: MedicineEntity)

    suspend fun delete(medicine: MedicineEntity)
}