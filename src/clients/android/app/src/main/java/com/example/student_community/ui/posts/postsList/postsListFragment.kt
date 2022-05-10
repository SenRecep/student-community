package com.example.student_community.ui.posts.postsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_community.MainActivity
import com.example.student_community.R
import com.example.student_community.adapters.PostListAdapter
import com.example.student_community.models.api.Pager
import com.example.student_community.utility.GlobalApp
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.posts_list_fragment.view.*

class postsListFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var postListRecyclerAdapter: PostListAdapter

    var page: Int = 0
    var isLoading = false
    var isLastPage = false

    private lateinit var viewModel: PostsListViewModel
    private  lateinit var fragmentView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostsListViewModel::class.java)
        fragmentView = inflater.inflate(R.layout.posts_list_fragment, container, false)

        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)

        fragmentView.btn_post_add.setOnClickListener {
            it.findNavController().navigate(R.id.postsAddFragmentNav)
        }


        linearLayoutManager = LinearLayoutManager(GlobalApp.getAppContext())

        fragmentView.rv.layoutManager = linearLayoutManager



        viewModel.posts.observe(viewLifecycleOwner) {
            if (it.size == 0 && page != 0) {
                postListRecyclerAdapter?.removeLoading()
                isLoading = false
                isLastPage = true
            } else {
                if (page == 0) {
                    fragmentView.rv.apply {
                        postListRecyclerAdapter = PostListAdapter(it) { post ->
                            // Recyclerview içerisindeki bir item tıklandığından burası çalışacak
                            var action =
                                postsListFragmentDirections.actionPostsListFragmentToPostsDetailFragment(
                                    post.Id.toInt()
                                )

                            var navHostFragment =
                                requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment

                            var navController = navHostFragment.navController


                            navController.navigate(action)

                        }
                        adapter = postListRecyclerAdapter
                    }
                }
                if (page != 0) {
                    postListRecyclerAdapter?.removeLoading()
                    isLoading = false
                    var isExist = postListRecyclerAdapter!!.posts.contains(it[0])
                    if (!isExist) postListRecyclerAdapter!!.addPosts(it)
                }
            }
        }


        fragmentView.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var visibleItemCount = linearLayoutManager.childCount
                var totalItemCount = linearLayoutManager.itemCount
                var firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (!isLoading && !isLastPage) {
                    Log.i(
                        "okhttp",
                        "$visibleItemCount + $firstVisibleItemPosition >= $totalItemCount"
                    )
                    if ((visibleItemCount + firstVisibleItemPosition >= totalItemCount)) {
                        isLoading = true
                        postListRecyclerAdapter?.addLoading()
                        page += 5
                        viewModel.getPosts(Pager(page))
                    }
                }
            }
        })

        return fragmentView
    }

    override fun onResume() {
        page=0
        isLoading=false
        isLastPage=false
        if (page == 0) {
            viewModel.getPosts(Pager(page))
        } else {
            fragmentView.rv.adapter = postListRecyclerAdapter
        }

        super.onResume()
    }
}