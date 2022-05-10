package com.example.student_community.ui.posts.postsAdd

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.student_community.MainActivity
import com.example.student_community.R
import com.example.student_community.models.webapi.dto.AddressCreateDto
import com.example.student_community.models.webapi.dto.AddressUpdateDto
import com.example.student_community.models.webapi.dto.PostCreateDto
import com.example.student_community.services.LocationData
import com.example.student_community.services.LocationService
import kotlinx.android.synthetic.main.post_item.view.*
import kotlinx.android.synthetic.main.posts_add_fragment.*
import kotlinx.android.synthetic.main.posts_add_fragment.view.*
import kotlinx.android.synthetic.main.posts_update_fragment.view.*
import kotlinx.coroutines.launch

class postsAddFragment : Fragment() {
    private lateinit var viewModel: PostsAddViewModel
    private lateinit var locationService: LocationService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostsAddViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.posts_add_fragment, container, false)
        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)
        viewModel.locationService = LocationService(requireActivity())

        viewModel.locationData.observe(viewLifecycleOwner) {
            if (it != null)
                txt_post_add_address.text = "${it.address}\n[${it.longitude}, ${it.latitude}]"
        }
        viewModel.findLocation()

        fragmentView.btn_fragment_post_add.setOnClickListener() {
            var postCreateDto = PostCreateDto(
                Title = fragmentView.txt_post_add_title.editText?.text.toString(),
                Content = fragmentView.txt_post_add_content.editText?.text.toString(),
                Address = AddressCreateDto(
                    Latitude = viewModel.locationData.value!!.latitude,
                    Longitude = viewModel.locationData.value!!.longitude,
                    Address = viewModel.locationData.value!!.address
                ),
            )

            viewModel.createPost(postCreateDto).observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(activity, "Post created", Toast.LENGTH_LONG).show()
                    fragmentView.findNavController().navigate(R.id.postsListFragmentNav)
                }
            }
        }

        return fragmentView
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewModel.requestPermissionsResult(requestCode, grantResults)
    }
}