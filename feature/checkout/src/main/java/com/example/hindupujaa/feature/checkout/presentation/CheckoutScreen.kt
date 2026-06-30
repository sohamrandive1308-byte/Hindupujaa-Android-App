package com.example.hindupujaa.feature.checkout.presentation

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hindupujaa.core.common.Constants
import com.razorpay.Checkout
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    onOrderSuccess: (String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val activity = context as Activity
    val scrollState = rememberScrollState()

    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("Pune") }
    var specialNotes by remember { mutableStateOf("") }
    var agreedToTC by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.isOrderPlaced) {
        if (uiState.isOrderPlaced) {
            Toast.makeText(context, "Order Placed Successfully!", Toast.LENGTH_LONG).show()
            onOrderSuccess("MOCK_ID")
        }
    }

    Scaffold(
        topBar = { 
            TopAppBar(
                title = { Text("Checkout") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            ) 
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Text(text = "Delivery Details", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth(),
                prefix = { Text("+91 ") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Delivery Address") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Order Summary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            
            uiState.cartItems.forEach { item ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "${item.pujaName} x ${item.quantity}", modifier = Modifier.weight(1f))
                    Text(text = "₹${(item.price * item.quantity).toInt()}", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Grand Total", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(
                    text = "₹${uiState.totalPrice.toInt()}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = agreedToTC, onCheckedChange = { agreedToTC = it })
                Text(text = "I agree to Terms & Conditions", style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (fullName.isBlank() || phone.length != 10 || address.isBlank() || !agreedToTC) {
                        Toast.makeText(context, "Please complete all fields correctly", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    
                    // In real app, we'd call the backend to create Razorpay Order
                    // For now, trigger Razorpay immediately for demo
                    startPayment(activity, fullName, phone, uiState.totalPrice) { paymentId ->
                        viewModel.placeOrder(paymentId)
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = !uiState.isLoading,
                shape = RoundedCornerShape(12.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Proceed to Payment")
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

private fun startPayment(activity: Activity, name: String, contact: String, amount: Double, onSuccess: (String) -> Unit) {
    val checkout = Checkout()
    checkout.setKeyID(Constants.RAZORPAY_KEY_ID)
    
    try {
        val options = JSONObject()
        options.put("name", "HinduPujaa")
        options.put("description", "Vedic Ritual Samagri")
        options.put("currency", "INR")
        options.put("amount", (amount * 100).toInt())
        
        val prefill = JSONObject()
        prefill.put("name", name)
        prefill.put("contact", contact)
        options.put("prefill", prefill)
        
        val theme = JSONObject()
        theme.put("color", "#FF9933")
        options.put("theme", theme)
        
        // We'd need to handle the result in MainActivity, 
        // but for this simplified flow we just open it.
        checkout.open(activity, options)
    } catch (e: Exception) {
        Toast.makeText(activity, "Error in payment: ${e.message}", Toast.LENGTH_LONG).show()
    }
}
