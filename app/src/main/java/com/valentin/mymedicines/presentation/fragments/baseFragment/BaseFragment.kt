package com.valentin.mymedicines.presentation.fragments.baseFragment

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.valentin.mymedicines.R

abstract class BaseFragment<Binding : ViewBinding>(layoutID: Int) : Fragment(layoutID) {
    protected abstract val binding: Binding

    fun Fragment.replaceFragment(fragment: Fragment, addStack: Boolean = true) {
        if (addStack) {
            this.fragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(
                    R.id.fragmentContainerView,
                    fragment
                )?.commit()
        } else{
            this.fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragmentContainerView,
                    fragment
                )?.commit()
        }
    }
}