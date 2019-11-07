package com.starwars.domain.entities

import java.io.Serializable

class CharacterSearchModel(var count: Int, var results: ArrayList<CharacterModel>?) : Serializable