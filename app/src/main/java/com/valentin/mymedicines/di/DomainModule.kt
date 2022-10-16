package com.valentin.mymedicines.di

import com.valentin.domain.usecases.GetMedicineUseCase
import com.valentin.domain.usecases.SaveMedicineUseCase
import com.valentin.mymedicines.data.repository.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideSaveMedicineUseCase(repository: MedicineRepository): SaveMedicineUseCase {
        return SaveMedicineUseCase(repository = repository)
    }

    @Provides
    fun provideGetMedicineUseCase(repository: MedicineRepository): GetMedicineUseCase {
        return GetMedicineUseCase(repository = repository)
    }
}