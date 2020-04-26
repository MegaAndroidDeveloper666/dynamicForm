package ru.mysmartflat.kortros.view.customViews.dynamic

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.markstudio.marksdynamicforms.R
import ru.markstudio.marksdynamicforms.views.AppUtils
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field
import ru.markstudio.marksdynamicforms.views.dynamicFields.MapField


class MapViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Field) {
        (itemView as MapView).onCreate(Bundle())
        (itemView as MapView).getMapAsync(OnMapReadyCallback { googleMap: GoogleMap ->
            (item as MapField).pointList?.map {
                googleMap.addMarker(MarkerOptions().position(LatLng(it.x, it.y)).icon(AppUtils.bitmapDescriptorFromVector(itemView.context, R.drawable.icon_map_pin)))
            }
            (item as MapField).center?.let {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.x, it.y), it.zoom))
            }
            googleMap.uiSettings.setAllGesturesEnabled(false)
            (itemView as MapView).onResume()
        })

    }

}