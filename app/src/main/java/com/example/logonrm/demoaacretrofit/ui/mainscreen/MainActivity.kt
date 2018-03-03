package com.example.logonrm.demoaacretrofit.ui.mainscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.logonrm.demoaacretrofit.R
import kotlinx.android.synthetic.main.activity_main.*
import com.muddzdev.styleabletoastlibrary.StyleableToast


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        btPesquisar.setOnClickListener {
            mainViewModel.pesquisarEndereco(etCEP.text.toString())
        }

        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading!!) {
                llLoading.visibility = View.VISIBLE
            } else {
                llLoading.visibility = View.GONE
            }
        })

        mainViewModel.apiResponse.observe(this, Observer { apiResponse ->
            if (apiResponse?.erro == null) {
                Log.i("TAG", "SUCESSO")
                tvResultado.text = "${apiResponse?.endereco?.logradouro}, ${apiResponse?.endereco?.bairro}\n" +
                        "${apiResponse?.endereco?.localidade}/${apiResponse?.endereco?.uf}"

                StyleableToast.Builder(this)
                        .text("Recebeu o endereço!")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.RED)
                        .show()


            } else {
                Log.i("TAG", "ERRO: ${apiResponse.erro}")
                StyleableToast.Builder(this)
                        .text("Não recebeu o endereço!")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.RED)
                        .show()
            }
        })

    }
}
