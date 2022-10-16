package com.valentin.mymedicines.di

import android.content.Context
import com.valentin.data.data.storage.room.RoomMedicineStorage
import com.valentin.mymedicines.data.repository.MedicineRepository
import com.valentin.mymedicines.data.storage.MedicineStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMedicineStorage(@ApplicationContext context: Context): MedicineStorage {
        return RoomMedicineStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(medicineStorage: MedicineStorage): MedicineRepository {
        return MedicineRepository(storage = medicineStorage)
    }
}