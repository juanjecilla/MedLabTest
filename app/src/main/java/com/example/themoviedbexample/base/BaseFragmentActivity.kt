package com.example.themoviedbexample.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.themoviedbexample.R

open class BaseFragmentActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    fun <T : Fragment> showFragment(
        fragmentClass: Class<T>, bundle: Bundle?,
        tag: String
    ): Fragment {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance()
                fragment!!.arguments = bundle
            } catch (e: InstantiationException) {
                throw RuntimeException("New Fragment should have been created", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("New Fragment should have been created", e)
            }

        }

        fragmentTransaction.replace(
            R.id.container, fragment,
            tag
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        return fragment
    }

    fun <T : Fragment> showFragment(fragmentClass: Class<T>) {
        showFragment(fragmentClass, null, "")
    }

    fun popFragmentBackStack() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }
    }

    private fun shouldShowActionBarUpButton() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        } else {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            popFragmentBackStack()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onBackStackChanged() {
        //shouldShowActionBarUpButton();
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
