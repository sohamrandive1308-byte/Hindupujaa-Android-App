package com.example.hindupujaa.feature.kit_builder.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.hindupujaa.core.domain.model.KitItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitBuilderScreen(
    pujaId: String,
    onBackClick: () -> Unit,
    onProceedClick: (String, List<String>) -> Unit,
    viewModel: KitBuilderViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(uiState.puja?.nameEn ?: "Kit Builder", style = MaterialTheme.typography.titleMedium)
                        Text("Customize your samagri", style = MaterialTheme.typography.bodySmall)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = { viewModel.selectAll(uiState.selectedCount < uiState.items.size) }) {
                        Text(if (uiState.selectedCount < uiState.items.size) "Select All" else "Deselect All")
                    }
                }
            )
        },
        bottomBar = {
            KitSummaryBottomBar(
                selectedCount = uiState.selectedCount,
                totalPrice = uiState.totalPrice,
                isLoading = uiState.isLoading,
                onProceed = {
                    viewModel.proceedToCheckout {
                        val selectedIds = uiState.items.filter { uiState.selectionMap[it.id] == true }.map { it.id }
                        onProceedClick(pujaId, selectedIds)
                    }
                }
            )
        }
    ) { padding ->
        if (uiState.isLoading && uiState.items.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            val includedItems = uiState.items.filter { it.itemType == "included" }
            val rentalItems = uiState.items.filter { it.itemType == "rental" }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (includedItems.isNotEmpty()) {
                    item { SectionHeader("Included Items") }
                    items(includedItems) { item ->
                        KitItemRow(
                            item = item,
                            isSelected = uiState.selectionMap[item.id] ?: false,
                            onToggle = { viewModel.toggleSelection(item.id) }
                        )
                    }
                }

                if (rentalItems.isNotEmpty()) {
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                    item { SectionHeader("Rental Items (Return in 24h)") }
                    items(rentalItems) { item ->
                        KitItemRow(
                            item = item,
                            isSelected = uiState.selectionMap[item.id] ?: false,
                            onToggle = { viewModel.toggleSelection(item.id) }
                        )
                    }
                }
                
                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun KitItemRow(
    item: KitItem,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f) 
                             else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            AsyncImage(
                model = item.imagePath,
                contentDescription = "Item Image: ${item.nameEn}",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.nameEn, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                Text(text = item.nameMr, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                if (item.isPerishable) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(12.dp), tint = Color.Red)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Perishable", fontSize = 10.sp, color = Color.Red)
                    }
                }
            }
            
            Text(
                text = "₹${item.price.toInt()}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun KitSummaryBottomBar(
    selectedCount: Int,
    totalPrice: Double,
    isLoading: Boolean,
    onProceed: () -> Unit
) {
    val animatedPrice by animateIntAsState(targetValue = totalPrice.toInt(), label = "price")
    
    Surface(
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "$selectedCount items selected", style = MaterialTheme.typography.labelSmall)
                Text(
                    text = "₹$animatedPrice",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            Button(
                onClick = onProceed,
                modifier = Modifier.height(50.dp).width(180.dp),
                enabled = selectedCount > 0 && !isLoading,
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Checkout")
                }
            }
        }
    }
}
