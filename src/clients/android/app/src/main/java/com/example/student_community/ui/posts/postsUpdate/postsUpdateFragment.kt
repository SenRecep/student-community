package com.example.student_community.ui.posts.postsUpdate

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.student_community.R

class postsUpdateFragment : Fragment() {

    companion object {
        fun newInstance() = postsUpdateFragment()
    }

    private lateinit var viewModel: PostsUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posts_update_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostsUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}