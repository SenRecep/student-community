package com.example.student_community.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.student_community.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var rv: RecyclerView? = null
    private var postsArrayList: ArrayList<Posts> = ArrayList()
    private var adapter: PostAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        rv = binding.rv;
        rv!!.setHasFixedSize(true)

        rv!!.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        val f1 = Posts(1,"okeye 4","izmir","4 ünfggvbhgjbjhbjhbjhbjhbhjbcü lazım");
        val f2 = Posts(1,"okeye 4","izmir","4 üncü lazım");
        val f3 = Posts(1,"okeye 4","izmir","4 üncü lazım");
        val f4 = Posts(1,"okeye 4","izmir","4 üncü lazım");
        val f5 = Posts(1,"okeye 4","izmir","4 üncü lazım");
        val f6 = Posts(1,"okeye 4","izmir","4 üncü lazım");

        postsArrayList = ArrayList();
        postsArrayList.add(f1);
        postsArrayList.add(f2);
        postsArrayList.add(f3);
        postsArrayList.add(f4);
        postsArrayList.add(f5);
        postsArrayList.add(f6);

        adapter = PostAdapter(requireContext(), postsArrayList)

        rv!!.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}