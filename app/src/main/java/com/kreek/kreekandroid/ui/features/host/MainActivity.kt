package com.kreek.kreekandroid.ui.features.host

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val phoneNumber = "+201127550196" // replace with the actual phone number
        val auth = FirebaseAuth.getInstance()
        signUpWithPhoneNumber(phoneNumber, auth)
        setContent {
            KreekandroidTheme {
                navController = rememberNavController().also { _navController ->
                    viewModel.updateNavController(_navController)
                    Box(modifier = Modifier.fillMaxSize()) {
                        MainScreen(
                            navController = _navController,
                        )
                    }
                }
            }
        }
    }

    fun signUpWithPhoneNumber(phoneNumber: String, auth: FirebaseAuth) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                signInWithPhoneAuthCredential(credential, auth)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                val credential = PhoneAuthProvider.getCredential(verificationId, "666666") // replace with the actual otp
                signInWithPhoneAuthCredential(credential, auth)

                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
            }
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            callbacks) // OnVerificationStateChangedCallbacks
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, auth: FirebaseAuth) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    user?.getIdToken(true)?.addOnCompleteListener { tokenResultTask ->
                        if (tokenResultTask.isSuccessful) {
                            val idToken = tokenResultTask.result?.token
                            Log.d("AccessToken", "idToken: $idToken") // Log the idToken
                            // You can now use the idToken for your needs
                        } else {
                            // Handle error in getting the token
                        }
                    }
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }
}