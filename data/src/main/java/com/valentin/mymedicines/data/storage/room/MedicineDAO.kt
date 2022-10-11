package com.valentin.data.data.storage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.valentin.mymedicines.data.entity.MedicineEntity

@Dao
interface MedicineDAO {

    @Query("SELECT * FROM MedicineEntity")
    suspend fun getAll(): List<MedicineEntity>

    @Insert
    suspend fun insert(medicine: MedicineEntity)

    @Update
    suspend fun update(medicine: MedicineEntity)

    @Delete
    suspend fun delete(medicine: MedicineEntity)
}