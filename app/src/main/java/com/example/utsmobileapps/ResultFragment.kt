package com.example.utsmobileapps

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utsmobileapps.databinding.FragmentResultBinding

class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var binding : FragmentResultBinding
    private val sharePref = "data"
    lateinit var uriImage : Uri
    lateinit var nama: String
    lateinit var tLahir : String
    lateinit var  tlLahir : String
    lateinit var  hobi : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        val sharedPreferences : SharedPreferences =
            view.context.getSharedPreferences(sharePref, Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("username","")
        nama = arguments?.getString("nama").toString()
        tLahir = arguments?.getString("tpLahir").toString()
        tlLahir = arguments?.getString("tLahir").toString()
        hobi = arguments?.getString("hobi").toString()
        uriImage = Uri.parse(arguments?.getString("imgUser").toString())

        binding.textHello.text = "Hello @$username"
        binding.textNama.text = "Nama : $nama"
        binding.textTempatLahir.text = "Tempat Lahir : $tLahir"
        binding.textTgllh.text = "Tanggal Lahir : $tlLahir"
        binding.textHobi.text = " Hobi : $hobi"
        binding.imageProfile.setImageURI(uriImage)

        binding.btnBacktohome.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_HomeFragment)
        }



    }
}