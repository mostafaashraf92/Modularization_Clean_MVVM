package com.starwars.core.interfaces


interface IOnCharacterClickedListener<T> {

    fun onListItemClicked(item: T)

}