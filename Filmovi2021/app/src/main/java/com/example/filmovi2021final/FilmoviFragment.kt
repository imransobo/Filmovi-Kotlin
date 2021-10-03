package com.example.filmovi2021final

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.filmovi2021final.databinding.FragmentFilmoviBinding
import com.example.filmovi2021final.film.*
import kotlinx.coroutines.InternalCoroutinesApi


class FilmoviFragment : Fragment(R.layout.fragment_filmovi), OnItemClickedListener {

    private lateinit var binding: FragmentFilmoviBinding

    private lateinit var viewModel: FilmoviViewModel

    private lateinit var  database: FilmoviDatabase

    private lateinit var databaseDao: FilmoviDatabaseDao

    private lateinit var filmAdapter: FilmAdapter


    @InternalCoroutinesApi
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFilmoviBinding.inflate(inflater, container, false)

        binding.rvListaFilmova.layoutManager = LinearLayoutManager(context)
        binding.rvListaFilmova.setHasFixedSize(true)


        val rootView =  inflater.inflate(R.layout.fragment_filmovi, container, false)
        val recyclerView = rootView?.findViewById<RecyclerView>(R.id.rv_lista_filmova)

        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)



        //instanca
        viewModel = ViewModelProvider(this).get(FilmoviViewModel::class.java)



        //funkciju getMovieData prebacili u ViewModel i pozivamo je preko instance.
        viewModel.getMovieData { film: List<FilmPodaci> ->
            recyclerView?.adapter = FilmAdapter(film, this)
        }

        viewModel.getAllMoviesObservers().observe(viewLifecycleOwner, Observer {
            filmAdapter.setListData(ArrayList(it))

        })

        //val application = requireNotNull(this.activity).application
        //val dataSource = FilmoviDatabase.getInstance(application).filmoviDatabaseDao
        //val viewModelFactory = FilmoviViewModelFactory(dataSource, application)

        //osiguravamo da viewmodel zivi dugo toliko da prezivi izmjene u konfiguraciji fragmenta
        val filmViewModel = ViewModelProvider(this).get(FilmoviViewModel::class.java)
        binding.lifecycleOwner = this
        binding.filmViewModel = filmViewModel








        setHasOptionsMenu(true)

        return rootView

    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.filmovi_menu, menu)


        val search = menu?.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView

        //prikazivace submit button sve dok query nije prazan
        /*searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)*/





    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }

    override fun onItemClick(film: FilmPodaci, position: Int) {
        //Log.i("KLIK", "Kliknuo na item")
        //radi

        val intent = Intent(context, DetaljiActivity::class.java)
        intent.putExtra("Naslov", film.title)
        intent.putExtra("Godina", film.release)
        intent.putExtra("Radnja", film.overview)
        intent.putExtra("Slika", film.posterPath)

        startActivity(intent)
    }


    //Implementiramo SearchView

    //Poziva se samo kad submitamo search
    /*override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null) {
            searchDataBase(query)
        }
        return true
    }

    //Poziva se kad napisemo bilo sta u search bar
    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null) {
            searchDataBase(query)
        }
        return true
    }

    private fun searchDataBase(query: String) {
        //dodajemo %% jer customSQLQuery zahtijeva, moramo ga ovako formatirati
        val searchQuery = "%$query%"



    }*/


}