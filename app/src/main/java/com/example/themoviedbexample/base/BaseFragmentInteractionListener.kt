package com.example.themoviedbexample.base

import android.os.Bundle
import androidx.fragment.app.Fragment

interface BaseFragmentInteractionListener {

    /**
     * Expands [android.support.v7.widget.Toolbar] to normal position if collapsed.
     */
    fun resetToolBarScroll()

    /**
     * Used by a [Fragment] to show another Fragment.
     *
     * @param fragmentClass a [Fragment] class
     * @param bundle a [Bundle]
     * @param <T> a generic type to indicate type/subclass of [Fragment]
    </T> */
    fun <T : Fragment> showFragment(
        fragmentClass: Class<T>, bundle: Bundle,
        TAG: String
    ): Fragment

    fun <T : Fragment> showFragment(
        fragmentClass: Class<T>, bundle: Bundle,
        TAG: String, container: Int
    ): Fragment

    fun resetBackStack()
}

