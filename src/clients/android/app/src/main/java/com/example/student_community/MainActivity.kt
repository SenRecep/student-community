package com.example.student_community

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.student_community.databinding.ActivityMainBinding
import com.example.student_community.ui.auth.AuthActivity
import com.example.student_community.utility.GlobalApp
import com.example.student_community.utility.HelperService
import com.example.student_community.utility.IViewModelState
import com.example.student_community.utility.LoadingState
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var loadingView: View
        fun setLoadingStatus(viewModel: IViewModelState, viewLifecycleOwner: LifecycleOwner) {
            viewModel.loadingState.observe(viewLifecycleOwner) {
                when (it) {
                    LoadingState.Loading -> loadingView.visibility = View.VISIBLE
                    LoadingState.Loaded -> loadingView.visibility = View.GONE
                }
            }

        }

        fun setErrorStatus(viewModel: IViewModelState, viewLifecycleOwner: LifecycleOwner) {
            viewModel.errorState.observe(viewLifecycleOwner) {
                HelperService.showErrorMessageByToast(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingView = full_page_loading_view

        setSupportActionBar(binding.appBarMain.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        binding.navView.menu.findItem(R.id.menu_logout).setOnMenuItemClickListener {
            HelperService.removeTokenSharedPreference()
            val intent= Intent(GlobalApp.getAppContext(),AuthActivity::class.java)
            startActivity(intent)
            finish()
            true
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.postsListFragmentNav
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                Toast.makeText(GlobalApp.getAppContext(), "Recep Şen (192804015)\nHikmet Gezmen (192804008)\nAli Eren Eriş (192802001)", Toast.LENGTH_LONG)
                    .show()
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}