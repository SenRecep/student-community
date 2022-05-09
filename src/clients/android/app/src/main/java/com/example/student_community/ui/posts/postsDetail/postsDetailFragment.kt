package com.example.student_community.ui.posts.postsDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.student_community.MainActivity
import com.example.student_community.R
import com.example.student_community.utility.GlobalApp
import kotlinx.android.synthetic.main.posts_detail_fragment.*

class postsDetailFragment : Fragment() {

    val args: postsDetailFragmentArgs by navArgs()

    private lateinit var viewModel: PostsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostsDetailViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.posts_detail_fragment, container, false)

        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)

        viewModel.getPost(args.postId)

        viewModel.post.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            txt_detail_Title.text = it.Title
            txt_detail_address.text = it.Address.Address
            txt_detail_content.text = it.Content
        }
        return fragmentView
    }
}