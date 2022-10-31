package com.valentin.mymedicines.adapter

import android.view.View

interface BaseAdapterCallback<T> {
    fun onItemClick(model: T, view: View)
}