package com.example.bai1_tuan4

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.security.MessageDigest
import java.util.UUID

class Auth {
    class AuthenticationManager(private val context: Context) {
        private val auth = FirebaseAuth.getInstance()

        fun signInWithGoogle(): Flow<AuthResponse> = callbackFlow {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(true)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .setAutoSelectEnabled(false)
                .build()

            val request = GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()
            val credentialManager = CredentialManager.create(context)
            try {
                val result = credentialManager.getCredential(context, request)
                val credential = result.credential
                if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                    auth.signInWithCredential(firebaseCredential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            trySend(AuthResponse.Success)
                            Toast.makeText(context,"thành công", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context,"thất bại", Toast.LENGTH_SHORT).show()
                            trySend(AuthResponse.Error(it.exception?.message ?: "Đăng nhập thất bại"))
                        }
                    }
                } else {
                    Log.w("AuthManager", "Credential không phải Google ID Token!")
                    trySend(AuthResponse.Error("Credential không hợp lệ"))
                }
            } catch (e: Exception) {
                Log.e("AuthManager", "Lỗi khi đăng nhập Google: ${e.message}", e)
                trySend(AuthResponse.Error(e.message ?: "Lỗi không xác định"))
            }
            awaitClose()
        }
    }
}

sealed interface AuthResponse {
    object Success : AuthResponse
    data class Error(val message: String) : AuthResponse
}