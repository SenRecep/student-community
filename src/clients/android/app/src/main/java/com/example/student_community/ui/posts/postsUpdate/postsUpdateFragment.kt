package com.example.student_community.ui.posts.postsUpdate

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.student_community.R
import com.example.student_community.utility.GlobalApp

class postsUpdateFragment : Fragment() {

    private val args: postsUpdateFragmentArgs by navArgs()

    private lateinit var viewModel: PostsUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostsUpdateViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.posts_update_fragment, container, false)
        Toast.makeText(GlobalApp.getAppContext(), args.post.Title, Toast.LENGTH_SHORT).show()
        return fragmentView
    }


}