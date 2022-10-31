package com.valentin.data.data.storage.room

import android.content.Context
import androidx.room.Room
import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.data.storage.MedicineStorage
import com.valentin.mymedicines.data.entity.MedicineEntity

class RoomMedicineStorage(context: Context) : MedicineStorage {

    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "MedicineDatabase"
    ).build()

    private val medicineDAO = database.medicineDao()

    override suspend fun get(): List<MedicineEntity> {
        return medicineDAO.getAll()
    }

    override suspend fun insert(medicine: MedicineEntity) {
        medicineDAO.insert(medicine = medicine)
    }

    override suspend fun update(medicine: MedicineEntity) {
        medicineDAO.update(medicine = medicine)
    }

    override suspend fun delete(medicine: MedicineEntity) {
        medicineDAO.delete(medicine = medicine)
    }
}