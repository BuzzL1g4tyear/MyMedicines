package com.valentin.mymedicines.data.repository

import com.valentin.mymedicines.data.storage.MedicineStorage
import com.valentin.domain.domain.repository.MedicineRepositoryInterface
import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.data.entity.MedicineEntity

class MedicineRepository(private val storage: MedicineStorage) : MedicineRepositoryInterface {
    override suspend fun saveMedicine(medicine: Medicine) {

        val medToStorage = toStorage(medicine)

        storage.insert(medToStorage)
    }

    override suspend fun getMedicine(): List<Medicine> {
        val medToDomain = storage.get()
        return toDomain(medToDomain)
    }

    private fun toStorage(medicine: Medicine): MedicineEntity {
        return MedicineEntity(name = medicine.name, date = medicine.date)
    }

    private fun toDomain(medicineList: List<MedicineEntity>): List<Medicine> {

        val list = mutableListOf<Medicine>()

        for (med in medicineList) {
            val medicine = Medicine(
                name = med.name,
                date = med.date
            )
            list.add(medicine)
        }
        return list
    }
}