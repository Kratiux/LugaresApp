package com.example.lugares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(context:this)
        auth = Firebase.auth


        //Se define el método para el login
        binding.btLogin.setOnClickListener{
            haceLogin();
        }

        //Se define el método para el registro
        binding.btRegister.setOnClickListener{
            haceRegister();
        }


    }

    private fun haceRegister(){
        val email = binding.et_email.text.toString()
        val clave = binding.et_clave.text.toString()

        //Se hace el registro
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccesful) {
                  Log.d(tag:"creando usuario",msg:"Falló")
                  Toast.makeText(baseContext, text:"Falló",Toast.LENGTH_LONG).show()
                  actualiza(user:null)
                }

            }
    }

    private fun haceLogin(){
        val email = binding.et_email.text.toString()
        val clave = binding.et_clave.text.toString()

        //Se hace el login
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { ask ->
                if (task.isSuccesful) {
                    Log.d(tag:"Autenticando",msg:"Autenticado")
                    val user = auth.currentUser
                    actualiza(user)
                } else {
                    Log.d(tag:"Autenticando",msg:"Falló")
                    Toast.makeText(baseContext, text:"Falló",Toast.LENGTH_LONG).show()
                    actualiza(user:null)
                }

            }
    }
}