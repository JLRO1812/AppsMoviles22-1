package edu.co.icesi.semana4kotlinv2

import java.util.*

class Task {

    var id:String
    var task:String

    constructor(){
        id = UUID.randomUUID().toString()
        task = "NOT_DEFIED"
    }

    constructor(id:String, task:String){
        this.id = id
        this.task = task
    }

}