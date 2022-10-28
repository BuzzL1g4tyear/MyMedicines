package com.valentin.mymedicines.presentation.fragments.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentin.domain.model.Item
import com.valentin.domain.usecases.GetMedicineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFrViewModel @Inject constructor(
    private val getMedicineUseCase: GetMedicineUseCase
) : ViewModel() {

    suspend fun getList(): MutableLiveData<MutableList<Item>> {
        return MutableLiveData(
            getMedicineUseCase.execute().toMutableList()
        )
    }
}