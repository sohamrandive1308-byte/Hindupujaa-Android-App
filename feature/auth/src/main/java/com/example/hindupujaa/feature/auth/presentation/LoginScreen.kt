package com.example.hindupujaa.feature.auth.presentation

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.launch
import com.example.hindupujaa.feature.auth.R

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val activity = context as Activity
    val scope = rememberCoroutineScope()
    val credentialManager = CredentialManager.create(context)
    val webClientId = stringResource(id = com.example.hindupujaa.core.ui.R.string.default_web_client_id)

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (uiState.error != null) {
            Text(
                text = uiState.error!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Text(
            text = if (uiState.isOtpSent) "Verify OTP" else "Welcome to HinduPujaa",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = if (uiState.isOtpSent) "Enter the code sent to ${uiState.phoneNumber}" else "Sign in with your phone number",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        if (!uiState.isOtpSent) {
            OutlinedTextField(
                value = uiState.phoneNumber,
                onValueChange = { if (it.length <= 10) viewModel.onPhoneNumberChange(it) },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone Icon") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                prefix = { Text("+91 ") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.sendOtp(activity) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = uiState.phoneNumber.length == 10 && !uiState.isLoading
            ) {
                if (uiState.isLoading) CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp))
                else Text("Get OTP", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Text(text = "OR", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = {
                    val googleIdOption = GetGoogleIdOption.Builder()
                        .setServerClientId(webClientId)
                        .setFilterByAuthorizedAccounts(false)
                        .build()

                    val request = GetCredentialRequest.Builder()
                        .addCredentialOption(googleIdOption)
                        .build()

                    scope.launch {
                        try {
                            val result = credentialManager.getCredential(context, request)
                            val credential = result.credential
                            if (credential is CustomCredential && 
                                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                                viewModel.signInWithGoogle(googleIdTokenCredential.idToken)
                            }
                        } catch (e: Exception) {
                            // Handle error
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Sign in with Google")
            }
        } else {
            var timerValue by remember { mutableIntStateOf(60) }
            LaunchedEffect(uiState.isOtpSent) {
                while (timerValue > 0) {
                    kotlinx.coroutines.delay(1000)
                    timerValue--
                }
            }

            OutlinedTextField(
                value = uiState.otp,
                onValueChange = { if (it.length <= 6) viewModel.onOtpChange(it) },
                label = { Text("6-Digit OTP") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.verifyOtp() },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = uiState.otp.length == 6 && !uiState.isLoading
            ) {
                if (uiState.isLoading) CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp))
                else Text("Verify & Login", fontSize = 18.sp)
            }
            
            TextButton(
                onClick = { 
                    timerValue = 60
                    viewModel.sendOtp(activity) 
                },
                enabled = timerValue == 0 && !uiState.isLoading
            ) {
                Text(if (timerValue > 0) "Resend OTP in ${timerValue}s" else "Resend OTP")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onSignUpClick) {
            Text("Don't have an account? Sign Up")
        }
    }
}
