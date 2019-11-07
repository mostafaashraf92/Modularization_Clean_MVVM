package com.starwars.domain.entities

import java.io.Serializable

data class CharacterModel(var name:String?, var height:String?,var mass:String?,var birth_year:String?,var gender:String?,var url:String?,var homeworld:String?,var films:ArrayList<String>?,var species:ArrayList<String>? ) :Serializable
