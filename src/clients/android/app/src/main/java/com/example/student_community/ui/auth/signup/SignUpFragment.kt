package com.example.student_community.ui.auth.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.student_community.R
import com.example.student_community.models.user.UserSignUp
import com.example.student_community.utility.LoadingState
import kotlinx.android.synthetic.main.sign_up_fragment.view.*

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.sign_up_fragment, container, false)
        var viewPager = requireActivity().findViewById<ViewPager2>(R.id.login_viewpager)


        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState.Loading -> fragmentView.signup_button_kayit.text = "Loading"
                LoadingState.Loaded -> fragmentView.signup_button_kayit.text = "Kayıt Ol"
            }
        }

        fragmentView.signup_button_kayit.setOnClickListener {
            var userSignUp = UserSignUp(
                FirstName = fragmentView.signup_text_first_name.editText?.text.toString(),
                LastName = fragmentView.signup_text_last_name.editText?.text.toString(),
                Email = fragmentView.signup_text_email.editText?.text.toString(),
                Password = fragmentView.signup_text_password.editText?.text.toString(),
            )
            viewModel.signUp(userSignUp).observe(viewLifecycleOwner) {
                if (it)
                    viewPager.currentItem = 0
            }
        }
        return fragmentView
    }
}