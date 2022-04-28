package com.example.minichallengechapter6asyntask

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minichallengechapter6asyntask.adapter.GhibliFilmAdapter
import com.example.minichallengechapter6asyntask.viewmodel.ViewModelGhibliFilm
import kotlinx.android.synthetic.main.activity_main.*
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var adapterGhibliFim : GhibliFilmAdapter
    private lateinit var viewModel : ViewModelGhibliFilm
    private lateinit var cont : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cont = this
        ContentAsyncTask().execute()
    }
    inner class ContentAsyncTask : AsyncTask<Void, Void, Void>(){
        private lateinit var pDialog : ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            pDialog = ProgressDialog(cont)
            pDialog.show()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            initRecyclerView()
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            getDataFilm()
            pDialog.dismiss()
        }

        private fun getDataFilm() {
            viewModel = ViewModelProvider(this@MainActivity).get(ViewModelGhibliFilm::class.java)
            viewModel.getLiveGhibliFilmObserver().observe(this@MainActivity, Observer{
                if(it.isNotEmpty()){
                    adapterGhibliFim.setDataGhibliFilm(it)
                    adapterGhibliFim.notifyDataSetChanged()
                }
            })
            viewModel.makeApiGhibliFilm()
        }

        private fun initRecyclerView() {
            rv_film.layoutManager = LinearLayoutManager(cont)
            adapterGhibliFim = GhibliFilmAdapter()
            rv_film.adapter = adapterGhibliFim
        }
    }
}