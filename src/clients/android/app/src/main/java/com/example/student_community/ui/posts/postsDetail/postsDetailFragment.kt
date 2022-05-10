package com.example.student_community.ui.posts.postsDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.student_community.MainActivity
import com.example.student_community.R
import com.example.student_community.utility.GlobalApp
import kotlinx.android.synthetic.main.posts_detail_fragment.*
import kotlinx.android.synthetic.main.posts_detail_fragment.view.*

class postsDetailFragment : Fragment() {

    val args: postsDetailFragmentArgs by navArgs()

    private lateinit var viewModel: PostsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[PostsDetailViewModel::class.java]
        var fragmentView = inflater.inflate(R.layout.posts_detail_fragment, container, false)

        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)

        viewModel.getPost(args.postId)
        viewModel.checkOwner(args.postId)

        viewModel.isOwner.observe(viewLifecycleOwner) {
            if (it) {
                btn_detail_delete.visibility = View.VISIBLE
                btn_detail_update.visibility = View.VISIBLE
            }
        }
        viewModel.post.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            txt_detail_Title.text = it.Title
            txt_detail_address.text = it.Address.Address
            txt_detail_content.text = it.Content
        }

        fragmentView.btn_detail_delete.setOnClickListener() {
            viewModel.deletePost(args.postId).observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(context, "Post deleted", Toast.LENGTH_SHORT).show()
                    fragmentView.findNavController().navigate(R.id.postsListFragmentNav)
                }
            }
        }

        fragmentView.btn_detail_update.setOnClickListener() {
            if (viewModel.post == null) return@setOnClickListener
            var action =
                postsDetailFragmentDirections.actionPostsDetailFragmentToPostsUpdateFragment(
                    viewModel.post.value!!
                )
            fragmentView.findNavController().navigate(action)
        }

        return fragmentView
    }
}