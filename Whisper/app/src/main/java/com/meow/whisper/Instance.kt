package com.meow.whisper

import com.google.firebase.firestore.FirebaseFirestore

object Instance {
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    fun FirestoreInstance(): FirebaseFirestore {
        return instance
    }
}