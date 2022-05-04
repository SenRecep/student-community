package com.example.student_community.ui.auth

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.student_community.R
import com.example.student_community.ui.auth.signin.SignInFragment
import com.example.student_community.ui.auth.signup.SignUpFragment
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_launch.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        var pagerAdapter=ScreenSlidePagerAdapter(this)
        pagerAdapter.addFragment(SignInFragment())
        pagerAdapter.addFragment(SignUpFragment())
        login_viewpager.adapter=pagerAdapter;
    }
    private  inner  class  ScreenSlidePagerAdapter(fa:FragmentActivity):FragmentStateAdapter(fa){
        var fragments=ArrayList<Fragment>()
        override fun getItemCount(): Int {
            return  fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return  fragments.get(position)
        }

        fun addFragment(f:Fragment){
            fragments.add(f)
        }

    }
}