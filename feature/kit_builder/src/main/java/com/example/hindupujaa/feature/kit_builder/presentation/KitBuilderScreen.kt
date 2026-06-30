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
import com.example.hindupujaa.core.ui.components.ClayCard
import com.example.hindupujaa.core.ui.components.ClayButton

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
                        Text(
                            text = uiState.puja?.nameEn ?: "Kit Builder", 
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black
                        )
                        Text("Personalize your ritual samagri", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = { viewModel.selectAll(uiState.selectedCount < uiState.items.size) }) {
                        Text(
                            text = if (uiState.selectedCount < uiState.items.size) "Select All" else "Deselect All",
                            fontWeight = FontWeight.Bold
                        )
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
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (includedItems.isNotEmpty()) {
                    item { SectionHeader("Included Samagri") }
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
                    item { SectionHeader("Rental Items (24h Return)") }
                    items(rentalItems) { item ->
                        KitItemRow(
                            item = item,
                            isSelected = uiState.selectionMap[item.id] ?: false,
                            onToggle = { viewModel.toggleSelection(item.id) }
                        )
                    }
                }
                
                item { Spacer(modifier = Modifier.height(120.dp)) }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Black,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun KitItemRow(
    item: KitItem,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    ClayCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        cornerRadius = 20.dp,
        elevation = 4.dp,
        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f) 
                         else Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
            )
            
            Spacer(modifier = Modifier.width(4.dp))
            
            AsyncImage(
                model = item.imagePath,
                contentDescription = "Item Image: ${item.nameEn}",
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.nameEn, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(text = item.description, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                if (item.isPerishable) {
                    Surface(
                        color = Color(0xFFFFEBEE),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)) {
                            Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(10.dp), tint = Color.Red)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Fresh Item", fontSize = 8.sp, color = Color.Red, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            
            Text(
                text = "₹${item.price.toInt()}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Black,
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
        shadowElevation = 24.dp,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "$selectedCount Items", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Text(
                    text = "₹$animatedPrice",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = (-0.5).sp
                )
            }
            
            ClayButton(
                onClick = onProceed,
                modifier = Modifier.width(180.dp),
                enabled = selectedCount > 0 && !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Checkout", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
