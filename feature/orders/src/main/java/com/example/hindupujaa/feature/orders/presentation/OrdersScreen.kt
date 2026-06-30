package com.example.hindupujaa.feature.orders.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hindupujaa.core.domain.model.Order
import java.text.SimpleDateFormat
import java.util.*

import com.example.hindupujaa.core.ui.components.ShimmerItem

@Composable
fun OrdersScreen(
    onOrderClick: (String) -> Unit,
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Active Orders", "Past Orders")

    Scaffold(
        topBar = {
            Column {
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        if (uiState.isLoading) {
            Column(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                ShimmerItem(modifier = Modifier.fillMaxWidth().height(100.dp))
                ShimmerItem(modifier = Modifier.fillMaxWidth().height(100.dp))
                ShimmerItem(modifier = Modifier.fillMaxWidth().height(100.dp))
            }
        } else {
            val orders = if (selectedTab == 0) uiState.activeOrders else uiState.pastOrders
            
            if (orders.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No orders yet", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(orders) { order ->
                        OrderListItem(order, onOrderClick)
                    }
                }
            }
        }
    }
}

@Composable
fun OrderListItem(order: Order, onClick: (String) -> Unit) {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val date = dateFormat.format(Date(order.createdAt))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = { onClick(order.orderId) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = order.pujaName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(text = "Order ID: HPJ-${order.orderId.takeLast(6)}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
                
                Surface(
                    color = getStatusColor(order.orderStatus).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = order.orderStatus.uppercase(),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = getStatusColor(order.orderStatus),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = date, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "₹${order.totalAmount.toInt()}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

fun getStatusColor(status: String): Color = when (status.lowercase()) {
    "placed" -> Color(0xFF003049) // Navy
    "confirmed" -> Color(0xFFF77F00) // Orange
    "packed" -> Color(0xFFFF9933) // Saffron
    "out_for_delivery" -> Color(0xFF2196F3) // Blue
    "delivered" -> Color(0xFF15803D) // Green
    else -> Color.Gray
}
