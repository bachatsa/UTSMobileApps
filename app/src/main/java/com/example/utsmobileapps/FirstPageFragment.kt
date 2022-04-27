package com.example.utsmobileapps

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utsmobileapps.databinding.FragmentAwalBinding

class FirstPageFragment : Fragment(R.layout.fragment_awal) {
    private lateinit var binding : FragmentAwalBinding
    private val sharePref = "data"
    lateinit var uriImage : Uri
    private val REQ_CODE = 1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding  = FragmentAwalBinding.bind(view)
        val sharedPreferences : SharedPreferences =
            view.context.getSharedPreferences(sharePref, Context.MODE_PRIVATE)
        var namaUser = sharedPreferences.getString("username","")
        Toast.makeText(context,"Hello ! $namaUser",Toast.LENGTH_SHORT).show()


        binding.imageUpload.setOnClickListener {
            checkPermissionForImage()
        }

        binding.btnNext.setOnClickListener {
            val edNama = binding.edtNama.text.toString()
            val edTL = binding.edTL.text.toString()
            val edTgl = binding.edtTgllh.text.toString()
            val edHobi = binding.edtHobi.text.toString()

            if (edNama.equals("") && edHobi.equals("") || edTL.equals("")){
                Toast.makeText(context,"Form tidak boleh ada yang kosong",Toast.LENGTH_SHORT).show()
            }
            else{
                val bundle = Bundle()
                bundle.putString("nama",edNama)
                bundle.putString("tpLahir",edTL)
                bundle.putString("tLahir",edTgl)
                bundle.putString("hobi",edHobi)
                bundle.putString("imgUser",uriImage.toString())
                findNavController().navigate(R.id.action_homeFragment_to_resultFragment, bundle)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQ_CODE){
            binding.imageUpload.setImageURI(data?.data)
            uriImage = data?.data!!
        }
    }
    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((context?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (context?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(permission, 100) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_READ LIKE 1001
                requestPermissions(permissionCoarse, 100) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 1002
            } else {
                openGalleryForImage()
            }
        }
    }
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQ_CODE)
    }
}