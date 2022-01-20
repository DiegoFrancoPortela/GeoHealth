package com.geohealth

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.firebase.auth.FirebaseAuth

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap : GoogleMap
    private lateinit var mMapFragment : SupportMapFragment

    //Firebase
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setTitle("Mapa")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Maps
        val mMapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mMapFragment.getMapAsync(this)
        //

    }

    fun logout() {
        // Metodo para cerrar sesión y cambiar de actividad.
        mAuth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // Finish para terminar esta Activity
        finish()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            logout()
        }
        return super.onOptionsItemSelected(item)
    }

    // Función para comprobar permiso de localización
    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED // Si permisos están activados o no.

    // Función para comprobar si se ha iniciado mapa
    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (!::mMap.isInitialized) return
        if (isPermissionsGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            requestLocationPermision()
        }
    }

    // Función para solicitar los permisos
    companion object {
        const val REQUEST_CODE_LOCATION = 0 // Código para saber si es nuestro permiso el aceptado
    }

    private fun requestLocationPermision() {
        // Si entra en if ya se habrían rechazado permisos
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(
                this,
                " Please go to settings and accept the permissions",
                Toast.LENGTH_SHORT
            ).show()
        } else { // Si entra en else nunca se han pedido permisos, se hace a traves de ActivityCompact.requestPermisions pasando activity (this), permiso que queremos que acepte, y código creado.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "To activate the location go to settings and accept the permissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    // Función para que no rompa app en caso anulación de perms en settings
    @SuppressLint("MissingPermission")
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::mMap.isInitialized) return // Si el mapa ha sido cargado
        if (!isPermissionsGranted()) { // Si los permisos están activos
            mMap.isMyLocationEnabled = false // Si no, desactivamos localización en tiempo real
            Toast.makeText(
                this,
                "To activate the location go to settings and accept the permissions",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}