package com.example.hindupujaa.feature.home.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.hindupujaa.core.domain.model.Category
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.ui.components.ShimmerItem
import com.example.hindupujaa.core.ui.components.ClayCard
import androidx.compose.ui.graphics.Brush
import com.example.hindupujaa.core.ui.theme.Saffron

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPujaClick: (String) -> Unit,
    onCategoryClick: (String) -> Unit,
    onCateringClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 0.dp, // Minimalist
                color = MaterialTheme.colorScheme.surface
            ) {
                TopAppBar(
                    title = { 
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = com.example.hindupujaa.core.ui.R.drawable.hindupujalogo,
                                contentDescription = "HinduPujaa Logo",
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "HinduPujaa", 
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = com.example.hindupujaa.core.ui.theme.MartelFontFamily,
                                letterSpacing = (-0.5).sp
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = onCartClick) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = onProfileClick) {
                            Icon(Icons.Default.Person, contentDescription = "Profile")
                        }
                    }
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (uiState.isLoading) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    ShimmerItem(modifier = Modifier.fillMaxWidth().height(180.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        ShimmerItem(modifier = Modifier.weight(1f).height(100.dp))
                        ShimmerItem(modifier = Modifier.weight(1f).height(100.dp))
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        HomeBanner()
                    }

                    item {
                        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                            Text(
                                text = "Ritual Categories", 
                                style = MaterialTheme.typography.titleLarge, 
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = (-0.5).sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                items(uiState.categories) { category ->
                                    ClayCard(
                                        modifier = Modifier.width(100.dp),
                                        cornerRadius = 20.dp,
                                        elevation = 4.dp,
                                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.clickable { onCategoryClick(category.id) }
                                        ) {
                                            Text(
                                                text = category.name, 
                                                style = MaterialTheme.typography.labelMedium,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Trending Pujas 🔥",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = (-0.5).sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(uiState.trendingPujas) { puja ->
                                TrendingPujaCard(puja, onPujaClick)
                            }
                        }
                    }

                    item {
                        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                            CateringBanner(onClick = onCateringClick)
                        }
                    }

                    item {
                        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                            Text(
                                text = "All Rituals",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = (-0.5).sp
                            )
                        }
                    }
                    
                    val chunks = uiState.trendingPujas.chunked(2)
                    items(chunks) { rowPujas ->
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(), 
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowPujas.forEach { puja ->
                                Box(modifier = Modifier.weight(1f)) {
                                    PujaCard(puja, onPujaClick)
                                }
                            }
                            if (rowPujas.size == 1) Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeBanner() {
    Box(modifier = Modifier.padding(20.dp)) {
        ClayCard(
            modifier = Modifier.fillMaxWidth().height(160.dp),
            containerColor = Saffron,
            cornerRadius = 32.dp,
            elevation = 10.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Surface(
                        color = Color.White.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "24H DELIVERY",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Pure Vedic\nSamagri Kits",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 28.sp
                    )
                }
                Text(
                    text = "ॐ",
                    modifier = Modifier.align(Alignment.BottomEnd).graphicsLayer { alpha = 0.2f },
                    fontSize = 80.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CateringBanner(onClick: () -> Unit) {
    ClayCard(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        containerColor = MaterialTheme.colorScheme.secondary,
        cornerRadius = 24.dp,
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Vedic Catering", color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = "Sattvic Traditional Meals", color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.bodySmall)
            }
            Surface(
                color = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(40.dp).clickable { onClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Search, 
                    contentDescription = null, 
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun TrendingPujaCard(puja: Puja, onClick: (String) -> Unit) {
    ClayCard(
        modifier = Modifier.width(260.dp).height(200.dp).clickable { onClick(puja.id) },
        cornerRadius = 28.dp,
        elevation = 8.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = puja.heroImagePath,
                contentDescription = "Trending Puja: ${puja.nameEn}",
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)))
            ))
            Column(modifier = Modifier.align(Alignment.BottomStart).padding(12.dp)) {
                Text(text = puja.nameEn, color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                Text(text = "₹${puja.basePrice.toInt()}", color = Saffron, fontWeight = FontWeight.Black, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun PujaCard(puja: Puja, onClick: (String) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(stiffness = 400f),
        label = "scale"
    )

    ClayCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(interactionSource = interactionSource, indication = null) { onClick(puja.id) },
        cornerRadius = 28.dp,
        elevation = 6.dp
    ) {
        AsyncImage(
            model = puja.heroImagePath,
            contentDescription = "Puja Image: ${puja.nameEn}",
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = puja.nameEn,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Text(
            text = "₹${puja.basePrice.toInt()}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Explore →",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )
    }
}
