package com.master.poker

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.database.getValue

data class Game(
    val name: String = ""
)

class GameViewModel : ViewModel() {
    private val database = Firebase.database("https://poker-8ea0b-default-rtdb.firebaseio.com")

    val games = mutableStateListOf<Game>()

    init {
        getGames()
    }
    fun getGames(){
        database.getReference("games")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    task.result.getValue<List<Game>>()?.let{
                        games.clear()
                        games.addAll(it)
                    }
                }
            }
    }
}