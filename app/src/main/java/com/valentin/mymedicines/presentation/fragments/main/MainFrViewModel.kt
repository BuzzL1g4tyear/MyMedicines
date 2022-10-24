package com.valentin.mymedicines.presentation.fragments.main

import androidx.lifecycle.ViewModel
import com.valentin.domain.usecases.GetMedicineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainFrViewModel @Inject constructor(
    private val getMedicineUseCase : GetMedicineUseCase
) : ViewModel() {

    suspend fun get(){
        getMedicineUseCase.execute()
    }
}