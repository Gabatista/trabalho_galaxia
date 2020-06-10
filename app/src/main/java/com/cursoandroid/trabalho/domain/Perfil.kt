package com.cursoandroid.trabalho.domain

import com.google.firebase.database.Exclude

data class Perfil(
    var id : String ?= null,
    var email: String ?= null,
    var nome: String ?= null
    //var visualizacoes: Int ?= null

)

