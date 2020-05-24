package com.shopping.baseproject.core.main.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.shopping.baseproject.MapBinding
import com.shopping.baseproject.R
import com.shopping.baseproject.shared.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<MapBinding, MapViewModel>(R.layout.fr_map), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    override val viewModel by viewModel<MapViewModel>()
    private var markers: MutableMap<Marker, Int> = HashMap()
    private var selectedStoreId: Int? = null
    private lateinit var googleMap: GoogleMap

    companion object {
        const val TAG_CODE_PERMISSION_LOCATION = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding?.mapView?.onCreate(savedInstanceState)
        binding?.mapView?.getMapAsync(this)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding?.mapView?.onSaveInstanceState(outState)
    }

    override fun setupViews() {
        setupListeners()
    }

    private fun setupObservers(googleMap: GoogleMap?) {
        viewModel.stores.observeNonNull(this) { stores ->
            for (store in stores) {
                googleMap?.let {
                    val marker = it.addMarker(
                        MarkerOptions().position(LatLng(store.latitude, store.longitude))
                            .title(store.name)
                    )
                    markers.put(marker, store.id)
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.setOnMarkerClickListener(this)
        setupObservers(googleMap)
        if (isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION) && isPermissionGranted(
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
            showCurrentLocation()
        else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                TAG_CODE_PERMISSION_LOCATION
            )
        }
    }

    private fun showCurrentLocation() {
        googleMap.isMyLocationEnabled = true
        googleMap.uiSettings?.isMyLocationButtonEnabled = true
        googleMap.setOnMarkerClickListener(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            TAG_CODE_PERMISSION_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    showCurrentLocation()
                }
                return
            }
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        context?.let {
            return ContextCompat.checkSelfPermission(
                it,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        binding?.mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding?.mapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        binding?.mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding?.mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding?.mapView?.onLowMemory()
    }

    private fun setupListeners() = with(findNavController()) {
        binding?.fabMap?.setOnClickListener {
            navigate(MapFragmentDirections.actionMapFragmentToShoppingRouteFragment(selectedStoreId.toString()))
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        marker?.let {
            selectedStoreId = markers[it]
            binding?.fabMap?.enable()
            it.showInfoWindow()
        }
        return true
    }
}
