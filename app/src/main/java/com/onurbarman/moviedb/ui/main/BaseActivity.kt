package com.onurbarman.moviedb.ui.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.view.KeyEvent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onurbarman.moviedb.R
import com.onurbarman.moviedb.data.remote.NetworkChangeReceiver
import com.onurbarman.moviedb.ui.main.ui.movies.MoviesFragment
import com.onurbarman.moviedb.ui.main.ui.profile.ProfileFragment
import com.onurbarman.moviedb.ui.main.ui.tv.TVFragment


open class BaseActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private var fragmentMovies: MoviesFragment =
        MoviesFragment()
    private var fragmentTV: TVFragment =
        TVFragment()
    private var fragmentProfile: ProfileFragment =
        ProfileFragment()

    val fragmentManager = supportFragmentManager

    val networkChangeReceiver : NetworkChangeReceiver =
        NetworkChangeReceiver()

    lateinit var alertDialog : SweetAlertDialog

    override fun onStart() {
        super.onStart()

        initNavigationView()
        initAlertDialog()

        val intentFilter : IntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver,intentFilter)
    }

    private fun initAlertDialog() {
        alertDialog = SweetAlertDialog(this)
        alertDialog.changeAlertType(SweetAlertDialog.WARNING_TYPE)
        alertDialog.setTitleText(getString(R.string.alert_close))
        alertDialog.setContentText(getString(R.string.alert_question))
        alertDialog.showCancelButton(true)
        alertDialog.setConfirmText(getString(R.string.alert_quit_text))
        alertDialog.setCancelText(getString(R.string.alert_cancel_text))
        alertDialog.setConfirmClickListener (object: SweetAlertDialog.OnSweetClickListener {
            override fun onClick(sDialog:SweetAlertDialog) {
                alertDialog.dismissWithAnimation()
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        })
        alertDialog.setCancelClickListener(object: SweetAlertDialog.OnSweetClickListener {
            override fun onClick(sDialog:SweetAlertDialog) {
                alertDialog.dismissWithAnimation()
            }
        })
    }


    private fun initNavigationView()
    {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(this)
        navView.setItemIconTintList(null)
        navView.setSelectedItemId(R.id.navigation_movies)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.navigation_movies -> {
                loadFragment(fragmentMovies, "MoviesFragment")
                return true
            }
            R.id.navigation_tv -> {
                loadFragment(fragmentTV , "TVFragment")
                return true
            }
            R.id.navigation_profile -> {
                loadFragment(fragmentProfile,"ProfileFragment")
                return true
            }
        }
        false

        return true
    }

    open fun loadFragment(fragment: Fragment?,tag : String): Boolean {
        //switching fragment
        if (fragment != null) {
            fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment,tag)
                .commit()
            return true
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {

                val tvDetailFragment : Fragment? = fragmentManager.findFragmentByTag("TVDetailFragment")
                val moviesDetailFragment : Fragment? = fragmentManager.findFragmentByTag("MoviesDetailFragment")
                if(alertDialog.isShowing )
                {
                    alertDialog.dismissWithAnimation()
                }
                else if (tvDetailFragment != null && tvDetailFragment.isVisible)
                {
                    fragmentManager.popBackStackImmediate()
                }
                else if (moviesDetailFragment != null && moviesDetailFragment.isVisible)
                {
                    fragmentManager.popBackStackImmediate()
                }
                else
                {
                    initAlertDialog()
                    alertDialog.show()
                }
                return true
            }
            else -> return false
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkChangeReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
    }



}