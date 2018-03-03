package com.example.logonrm.demoaacretrofit.api

import com.example.logonrm.demoaacretrofit.entities.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by logonrm on 03/03/2018.
 */

interface EnderecoAPI {
        @GET("/ws/{cep}/json")
        fun pesquisar(@Path("cep") cep: String): Call<Endereco>
}