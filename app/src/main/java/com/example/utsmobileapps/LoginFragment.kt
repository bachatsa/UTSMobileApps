package com.example.utsmobileapps


import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utsmobileapps.databinding.FragmentLoginBinding
import java.util.jar.Manifest

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val sharePref = "data"
    val USER_LOGIN = "User"
    val USER_PASSWORD = "123"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val edLogin = binding.edtUsername.text.toString()
            val edPassword = binding.edtPassword.text.toString()
            val sharedPreferences : SharedPreferences =
                view.context.getSharedPreferences(sharePref, Context.MODE_PRIVATE)
            if (edLogin.equals("") && edPassword.equals("")){
                Toast.makeText(context,
                    "User dan password tidak boleh kosong",
                    Toast.LENGTH_SHORT)
                    .show()
            }

            else if(!edLogin.equals(USER_LOGIN) && !edPassword.equals(USER_PASSWORD)){
                Toast.makeText(context,
                    "User atau password yang anda masukkan salah",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                val editor : SharedPreferences.Editor= sharedPreferences.edit()
                editor.putString("username",edLogin)
                editor.apply()

                Toast.makeText(context,
                    "Berhasil masuk",
                    Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

            }

        }

    }

}