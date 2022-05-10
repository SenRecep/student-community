package com.example.student_community.ui.posts.postsUpdate

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
import com.example.student_community.models.webapi.dto.AddressCreateDto
import com.example.student_community.models.webapi.dto.AddressUpdateDto
import com.example.student_community.models.webapi.dto.PostUpdateDto
import com.example.student_community.services.LocationService
import com.example.student_community.utility.GlobalApp
import kotlinx.android.synthetic.main.posts_add_fragment.*
import kotlinx.android.synthetic.main.posts_update_fragment.view.*

class postsUpdateFragment : Fragment() {

    private val args: postsUpdateFragmentArgs by navArgs()

    private lateinit var viewModel: PostsUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostsUpdateViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.posts_update_fragment, container, false)
        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)
        viewModel.locationService = LocationService(requireActivity())
        viewModel.findLocation()
        viewModel.locationData.observe(viewLifecycleOwner) {
            if (it != null)
                fragmentView.txt_post_update_address.text =
                    "${it.address}\n[${it.longitude}, ${it.latitude}]"
        }

        var currentAddress = args.post.Address
        fragmentView.txt_post_update_current_address.text =
            "${currentAddress.Address}\n[${currentAddress.Longitude}, ${currentAddress.Latitude}]"
        fragmentView.txt_post_update_title.editText?.setText(args.post.Title)
        fragmentView.txt_post_update_content.editText?.setText(args.post.Content)

        fragmentView.btn_fragment_post_update.setOnClickListener() {
            var postUpdateDto = PostUpdateDto(
                Title = fragmentView.txt_post_update_title.editText?.text.toString(),
                Content = fragmentView.txt_post_update_content.editText?.text.toString()
            )

            if (fragmentView.cb_getnewlocation.isChecked)
                postUpdateDto.Address = AddressUpdateDto(
                    Latitude = viewModel.locationData.value!!.latitude,
                    Longitude = viewModel.locationData.value!!.longitude,
                    Address = viewModel.locationData.value!!.address
                )
            else
                postUpdateDto.Address = AddressUpdateDto(
                    Latitude = args.post.Address.Latitude,
                    Longitude = args.post.Address.Longitude,
                    Address = args.post.Address.Address
                )
            viewModel.updatePost(args.post.Id.toInt(), postUpdateDto).observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(activity, "Post Updated", Toast.LENGTH_LONG).show()
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