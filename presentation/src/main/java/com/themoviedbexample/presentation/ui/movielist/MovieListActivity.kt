package com.themoviedbexample.presentation.ui.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.themoviedbexample.presentation.R
import com.themoviedbexample.presentation.ui.movielist.list.MovieListFragment

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        showFragment(MovieListFragment::class.java, null, true, "")

    }

    private fun <T : Fragment> showFragment(
        fragmentClass: Class<T>,
        bundle: Bundle?,
        addToBackStack: Boolean,
        tag: String
    ): Fragment {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: fragmentClass.newInstance()

        fragment.arguments = bundle

        fragmentTransaction.replace(
            R.id.container, fragment,
            tag
        )

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()

        return fragment
    }
}