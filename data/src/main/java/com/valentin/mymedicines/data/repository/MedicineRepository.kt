package com.valentin.mymedicines.data.repository

import com.valentin.mymedicines.data.storage.MedicineStorage
import com.valentin.domain.domain.repository.MedicineRepositoryInterface
import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.data.entity.MedicineEntity

class MedicineRepository(private val storage: MedicineStorage) : MedicineRepositoryInterface {

    override suspend fun getMedicine(): List<Medicine> {
        return toDomain(storage.get())
    }

    override suspend fun saveMedicine(medicine: Medicine) {
        storage.insert(toStorage(medicine))
    }

    override suspend fun updateMedicine(medicine: Medicine) {
        storage.update(toStorage(medicine = medicine))
    }

    override suspend fun deleteMedicine(medicine: Medicine) {
        storage.delete(toStorage(medicine = medicine))
    }

    private fun toStorage(medicine: Medicine): MedicineEntity {
        return MedicineEntity(uid = medicine.id, name = medicine.name, date = medicine.date)
    }

    private fun toDomain(medicineList: List<MedicineEntity>): List<Medicine> {

        val list = mutableListOf<Medicine>()

        for (med in medicineList) {
            val medicine = Medicine(
                id = med.uid,
                name = med.name,
                date = med.date
            )
            list.add(medicine)
        }
        return list
    }
}