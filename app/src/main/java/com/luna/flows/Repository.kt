package com.luna.flows

class Repository {
    private val names: List<String> = List(3) { "Name $it" }

    fun getNames(): List<String> {
        return names
    }


}