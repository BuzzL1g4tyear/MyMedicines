package com.valentin.data.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.valentin.domain.model.Medicine
import com.valentin.mymedicines.data.entity.MedicineEntity

@Database(entities = [MedicineEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDAO
}