package com.cursoandroid.trabalho.view.activity

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cursoandroid.trabalho.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception
import java.util.*

class Mapa : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var locationManager : LocationManager?= null
    var localListener : LocationListener?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapLongClickListener ( listener )
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        localListener = object  : LocationListener{

            override fun onLocationChanged(p0: Location?) {

                if(p0 != null){
                    mMap.clear()

                    val localUsuario = LatLng(p0!!.latitude,p0!!.longitude)
                    mMap.addMarker(MarkerOptions().position(localUsuario).title("Seu local"))
                    //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localUsuario,15f))

                    val planetario_brasilia = LatLng(-15.7877, -47.8989)
                    mMap.addMarker(MarkerOptions().position(planetario_brasilia).title("Planetário de Brasília"))
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(planetario_brasilia))

                    //observatório Luís Cruls
                    val observatorio_luis_cruls = LatLng(-15.952730, -47.931749)
                    mMap.addMarker(MarkerOptions().position(observatorio_luis_cruls).title("Observatório Luís Cruls"))

                    //observatório da UNB
                    val observatorio_unb = LatLng(-15.948996, -47.935075)
                    mMap.addMarker(MarkerOptions().position(observatorio_unb).title("Observatório da UNB"))


                    val geocoder = Geocoder(applicationContext, Locale.getDefault())

                    try {
                        val ListaEndereco = geocoder.getFromLocation(p0.latitude,p0.longitude,1)

                        if(ListaEndereco != null && ListaEndereco.size > 0){
                            println("endereço" + ListaEndereco[0].toString())
                        }


                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }

            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

            }

            override fun onProviderEnabled(p0: String?) {

            }

            override fun onProviderDisabled(p0: String?) {

            }

        }

        //verifica se possui permissões
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
        } else{
            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,localListener)
            val lastLocation = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            var UltimoLocalUsuario = LatLng(lastLocation.latitude,lastLocation.longitude)
            mMap.addMarker(MarkerOptions().position(UltimoLocalUsuario).title("Seu local"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UltimoLocalUsuario,15f))
        }

    }


    //listener para indicar o endereço com base no google maps
    val listener = object : GoogleMap.OnMapLongClickListener{
        override fun onMapLongClick(p0: LatLng?) {

            mMap.clear()

            val geocoder = Geocoder(applicationContext,Locale.getDefault())
            var endereço = ""

            try {
                val listaEndereço = geocoder.getFromLocation(p0!!.latitude,p0!!.longitude,1)

                if(listaEndereço != null && listaEndereço.size > 0){
                    if(listaEndereço[0].thoroughfare != null){
                        endereço += listaEndereço[0].thoroughfare

                        if(listaEndereço[0].subThoroughfare != null){
                            endereço += listaEndereço[0].subThoroughfare
                        }
                    }
                }

            }catch (e: Exception){

            }

            if(endereço.equals("")){
                endereço = "Sem endereço identificado"
            }

            mMap.addMarker(MarkerOptions().position(p0!!).title(endereço))
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode != 1){
            if(grantResults.size > 0){
                //tem resultados
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,localListener)
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
