package com.example.filmovi2021final

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.filmovi2021final.databinding.FragmentPocetniBinding


class PocetniFragment : Fragment(R.layout.fragment_pocetni) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentPocetniBinding.inflate(inflater, container, false)
        binding.pocetnaDugmeKreni.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_pocetniFragment_to_filmoviFragment2)
        }
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if(id == R.id.predefinisana_poruka) {
            val poruka = "Skinite našu aplikaciju potpuno besplatno na : https://ufile.io/ikv27f0c"
            val intent_podijeli = Intent()

            intent_podijeli.action = Intent.ACTION_SEND
            intent_podijeli.type = "text/plain"

            intent_podijeli.putExtra(Intent.EXTRA_TEXT, poruka)
            startActivity(Intent.createChooser(intent_podijeli, "Podijeli poruku preko"))

        }
        if(id == R.id.Informacije) {
            findNavController().navigate(R.id.action_pocetniFragment_to_informacijeFragment)
        }
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }






}