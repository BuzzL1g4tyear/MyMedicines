package com.valentin.mymedicines.presentation.fragments.baseFragment

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding : ViewBinding>(layoutID: Int) : Fragment(layoutID) {
    protected abstract val binding: Binding
}