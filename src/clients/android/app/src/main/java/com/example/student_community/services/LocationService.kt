package com.example.student_community.services

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.math.roundToInt

data class LocationData(
    var latitude: Float,
    var longitude: Float,
    var address: String
)

class LocationService {
    private var fusedLocationProviderClient: FusedLocationProviderClient
    private val context: CoroutineContext = Dispatchers.Main
    private var activity: Activity

    constructor(activity: Activity) {
        this.activity = activity
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity)
    }

    fun getCompleteAddressString(
        LATITUDE: Double,
        LONGITUDE: Double,
        context: Context
    ): String {
        var strAdd = ""
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses: List<Address>? = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
            if (addresses != null) {
                val returnedAddress: Address = addresses[0]
                val strReturnedAddress = StringBuilder("")
                for (i in 0..returnedAddress.maxAddressLineIndex) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                }
                strAdd = strReturnedAddress.toString()
                Log.w("My Current loction address", strReturnedAddress.toString())
            } else {
                Log.w("My Current loction address", "No Address returned!")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.w("My Current loction address", "Canont get Address!")
        }
        return strAdd
    }


     fun getCurrentLocation():Task<Location> {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                }

                return fusedLocationProviderClient.lastLocation

            } else {
                Toast.makeText(activity, "Turn on location", Toast.LENGTH_SHORT).show()
                var intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(activity, intent, null)
            }
        } else {
            requestPermission()
        }
        return fusedLocationProviderClient.lastLocation
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            activity,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            activity,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    suspend fun requestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "Granted", Toast.LENGTH_SHORT).show()
            getCurrentLocation()
        } else {
            Toast.makeText(activity, "Denied", Toast.LENGTH_SHORT).show()
        }
    }

    public fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val startPoint = Location("locationA")
        startPoint.latitude = lat1
        startPoint.longitude = lon1

        val endPoint = Location("locationA")
        endPoint.latitude = lat2
        endPoint.longitude = lon2

        return startPoint.distanceTo(endPoint)
    }

    public fun distanceText(distance: Float): String {
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


}