package com.kudzai.mutswairo.dogpics.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kudzai.mutswairo.dogpics.R
import com.kudzai.mutswairo.dogpics.data.remote.Status
import com.kudzai.mutswairo.dogpics.ui.adapter.DogsAdapter
import com.kudzai.mutswairo.dogpics.ui.viewmodel.DogsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var rvDogs: RecyclerView
    private lateinit var breedsSpinner: Spinner
    private lateinit var homeLayout: ConstraintLayout
    private val dogsViewModel: DogsViewModel by viewModels()
    private var breedsList: List<String> = ArrayList<String>()

    init {
        rvDogs = findViewById(R.id.rvDogsGrid)
        breedsSpinner = findViewById(R.id.spBreeds)
        homeLayout = findViewById(R.id.viewHome)
        dogsViewModel.getDogBreeds()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogsViewModel.dogBreeds.observe(this) {
            val spinnerAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                it
            )

            breedsSpinner.adapter = spinnerAdapter
            breedsList = it
        }
        rvDogs.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        breedsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                showDogs(breedsList[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                showDogs(breedsList[0])
            }
        }
    }

    private fun showDogs(breed: String) {
        dogsViewModel.getDogPics(breed)

        dogsViewModel.dogPics.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    rvDogs.adapter = DogsAdapter(it.data!!)
                }
                Status.LOADING -> {
                    rvDogs.adapter = DogsAdapter(it.data!!)
                }
                Status.ERROR -> {
                    Snackbar.make(this, homeLayout, it.message!!, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}
