package com.example.student_community.ui.launch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.student_community.MainActivity
import com.example.student_community.R
import com.example.student_community.ui.auth.AuthActivity

class LaunchActivity : AppCompatActivity() {
    lateinit var viewModel: LaunchActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        viewModel = ViewModelProvider(this).get(LaunchActivityViewModel::class.java)

        viewModel.tokenCheck().observe(this) {
            var intent: Intent? = when (it) {
                true ->
                    Intent(this@LaunchActivity, MainActivity::class.java)
                false ->
                    Intent(this@LaunchActivity, AuthActivity::class.java)
            }
            startActivity(intent)
        }
    }
}