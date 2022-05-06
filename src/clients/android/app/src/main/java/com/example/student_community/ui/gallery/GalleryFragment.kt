package com.example.student_community.ui.gallery

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.student_community.databinding.FragmentGalleryBinding
import com.example.student_community.services.LocationService
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    private lateinit var viewModel: GalleryViewModel
    private lateinit var locationService: LocationService

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val latitude: TextView = binding.textLatitude
        val longitude: TextView = binding.textLongitude
        val address: TextView = binding.textAddress
        val fark: TextView = binding.textFark
        viewModel.latitude.observe(viewLifecycleOwner) {
            latitude.text = it
        }
        viewModel.longitude.observe(viewLifecycleOwner) {
            longitude.text = it
        }
        viewModel.address.observe(viewLifecycleOwner) {
            address.text = it
        }
        viewModel.fark.observe(viewLifecycleOwner) {
            fark.text = it
        }
        locationService = LocationService(requireActivity())

        viewLifecycleOwner.lifecycleScope.launch {
            locationService.getCurrentLocation().apply {
                viewModel.latitude.value = this?.latitude.toString()
                viewModel.longitude.value = this?.longitude.toString()
                viewModel.address.value = this?.address
                val distance = locationService.distance(this!!.latitude,this!!.longitude, 38.5002044, 27.7102244)
                viewModel.fark.value = distanceText(distance)
            }
        }


        return root
    }

    private fun distanceText(distance: Float): String {
        return if (distance < 1000)
            if (distance < 1)
                String.format(Locale.GERMANY, "%dm", 1)
            else
                String.format(Locale.GERMANY, "%dm", distance.roundToInt())
        else if (distance > 10000)
            if (distance < 1000000)
                String.format(Locale.GERMANY, "%dkm", (distance / 1000).roundToInt())
            else
                "Uzak"
        else
            String.format(Locale.GERMANY, "%.2fkm", distance / 1000)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewLifecycleOwner.lifecycleScope.launch {
            locationService.requestPermissionsResult(requestCode, grantResults)
        }
    }
}