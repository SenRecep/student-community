package com.example.student_community.ui.slideshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.student_community.databinding.FragmentSlideshowBinding
import com.example.student_community.models.api.Pager
import com.example.student_community.utility.HelperService
import com.example.student_community.utility.LoadingState
import kotlinx.android.synthetic.main.sign_in_fragment.view.*
import kotlinx.coroutines.launch

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.errorState.observe(viewLifecycleOwner) {
            HelperService.showErrorMessageByToast(it)
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState.Loading -> _binding!!.txtLoadingState.text = "Loading"
                LoadingState.Loaded -> _binding!!.txtLoadingState.text = "Loaded"
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPosts(Pager(0)).observe(viewLifecycleOwner) {
                if (it == null) return@observe;
                Log.i("posts", it.toString())
                _binding!!.txtPostCount.text = it.count().toString()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}