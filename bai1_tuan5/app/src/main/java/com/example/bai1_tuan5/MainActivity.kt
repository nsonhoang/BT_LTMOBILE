package com.example.bai1_tuan5

import CourseViewModel
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log


import android.widget.Toast
import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text


import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.ViewModel

import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.*
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.DocumentSnapshot
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private  val viewModel : CourseViewModel by viewModels()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        val db = Firebase.firestore


        enableEdgeToEdge()
        setContent {
            val courseViewModel: CourseViewModel = viewModel()
            LaunchedEffect(Unit) {
                courseViewModel.fetchCourses()
            }

            val course =courseViewModel.courses.collectAsState().value

            println(course)
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = {
                        db.collection("Course")
                            .get()
                            .addOnSuccessListener { querySnapshot ->
                                if (!querySnapshot.isEmpty) {
                                    for (document in querySnapshot.documents) {

                                        val course = document.toObject(Course::class.java)
                                        val documentId = document.id //
                                        // Lấy ID của từng tài liệu trong kết quả truy vấn
                                        Log.d(TAG, "Found document with ID: $documentId")
                                        Log.d(TAG, "Document data: ${document.data}")
                                        db.collection("Course").document(documentId)
                                            .collection("List")
                                            .get().addOnSuccessListener {
                                                querySnapshot->
                                                if(!querySnapshot.isEmpty){
                                                    for(itemDocument in querySnapshot.documents){
                                                        val itemdocumentId = itemDocument.id
                                                        Log.d(TAG, "Found document with ID: $itemdocumentId")
                                                        Log.d(TAG, "Document data: ${itemDocument.data}")
                                                        val listItem = mutableListOf<Alo?>()
                                                        val itemCourse = itemDocument.toObject(Alo::class.java)
                                                        listItem.add(itemCourse)

                                                        if (course != null) {
                                                            course.aloList = listItem.filterNotNull()// Gán nếu course không null
                                                        } else {
                                                            // Xử lý trường hợp course là null (nếu cần)
                                                        }
                                                    }

                                                }

                                            }
                                    }
                                } else {
                                    Log.d(TAG, "No documents found matching the query.")
                                }
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error querying documents", e)
                            }
                    }
                ) { }
            }

        }
    }


}
