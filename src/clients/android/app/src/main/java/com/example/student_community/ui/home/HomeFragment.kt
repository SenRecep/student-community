package com.example.student_community.ui.home

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.student_community.MainActivity
import com.example.student_community.adapters.PostListAdapter
import com.example.student_community.databinding.FragmentHomeBinding
import com.example.student_community.models.api.Pager
import com.example.student_community.models.webapi.Post
import com.example.student_community.utility.GlobalApp
import com.example.student_community.utility.HelperService
import com.example.student_community.utility.LoadingState
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: PostListAdapter


    var page: Int = 0
    var isLoading = false
    var isLastPage = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)

        linearLayoutManager = LinearLayoutManager(GlobalApp.getAppContext())
        root.rv.layoutManager = linearLayoutManager

        if (page == 0) {
            viewModel.getPosts(Pager(page))
        } else {
            root.rv.adapter = adapter
        }

        viewModel.posts.observe(viewLifecycleOwner) {
            if (it.size == 0 && page != 0) {
                adapter?.removeLoading()
                isLoading = false
                isLastPage = true
            } else {
                if (page == 0) {
                    root.rv.apply {
                        this.adapter = PostListAdapter(it) { product ->
                            // Recyclerview içerisindeki bir item tıklandığından burası çalışacak
                        }
                        adapter = this.adapter
                    }
                }

                if (page != 0) {
                    adapter?.removeLoading()
                    isLoading = false
                    var isExist = this.adapter!!.posts.contains(it[0])
                    if (!isExist) this.adapter!!.addPosts(it)
                }
            }
        }
        root.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                        adapter?.addLoading()
                        page += 5
                        viewModel.getPosts(Pager(page))
                    }
                }
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}