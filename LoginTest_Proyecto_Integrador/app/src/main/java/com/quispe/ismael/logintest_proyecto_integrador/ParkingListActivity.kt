
package com.quispe.ismael.logintest_proyecto_integrador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParkingListActivity : AppCompatActivity() {

    private lateinit var parkingAdapter: ParkingAdapter
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with empty data and set it to the RecyclerView
        parkingAdapter = ParkingAdapter(emptyList()) { parkingSlot ->
            deleteParkingSlot(parkingSlot)
        }
        recyclerView.adapter = parkingAdapter

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            val intent = Intent(this, CreateParkingActivity::class.java)
            startActivity(intent)
        }

        // Fetch data when the activity starts
        fetchParkingSlots()
    }




    private fun fetchParkingSlots() {
        RetrofitClient.apiService.getParkingSlots().enqueue(object : Callback<List<EstacionamientoAuto01>> {
            override fun onResponse(
                call: Call<List<EstacionamientoAuto01>>,
                response: Response<List<EstacionamientoAuto01>>
            ) {
                if (response.isSuccessful) {
                    val parkingSlots = response.body()
                    if (parkingSlots != null) {
                        // Update adapter with the fetched data
                        parkingAdapter.updateData(parkingSlots)
                    }
                } else {
                    Toast.makeText(this@ParkingListActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<EstacionamientoAuto01>>, t: Throwable) {
                Log.e("Retrofit Error", "API Error", t)
                Toast.makeText(this@ParkingListActivity, "API Error", Toast.LENGTH_SHORT).show()
            }
        })
    }



    private fun deleteParkingSlot(parkingSlot: EstacionamientoAuto01) {
        parkingSlot.idEstacionamientoAuto01?.let { idEstacionamientoAuto01 ->
            Log.d("DeleteOperation", "Attempting to delete parking slot with id: $idEstacionamientoAuto01")
            RetrofitClient.apiService.deleteParkingSlot(idEstacionamientoAuto01).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {

                        Toast.makeText(this@ParkingListActivity, "Parking slot deleted!", Toast.LENGTH_SHORT).show()
                        fetchParkingSlots() // Refresh list after deletion
                    } else {
                        Toast.makeText(this@ParkingListActivity, "Error deleting slot", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("DeleteOperation", "API Error during deletion", t)
                    Toast.makeText(this@ParkingListActivity, "API Error", Toast.LENGTH_SHORT).show()
                }
            })
        }   ?: Log.e("DeleteOperation", "Parking slot id is null")
    }
}
