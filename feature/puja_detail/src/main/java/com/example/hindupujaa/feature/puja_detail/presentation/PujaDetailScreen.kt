package com.example.hindupujaa.feature.puja_detail.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.hindupujaa.core.ui.components.ClayCard
import com.example.hindupujaa.core.ui.components.ClayButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PujaDetailScreen(
    pujaId: String,
    onBackClick: () -> Unit,
    onBookNowClick: (String) -> Unit,
    viewModel: PujaDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(pujaId) {
        viewModel.loadPujaDetail(pujaId)
    }

    Scaffold(
        bottomBar = {
            if (uiState.puja != null) {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp,
                    shadowElevation = 16.dp,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "Total Amount", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                            Text(
                                text = "₹${uiState.puja?.basePrice?.toInt()}",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Black
                            )
                        }
                        ClayButton(
                            onClick = { onBookNowClick(pujaId) },
                            modifier = Modifier.width(180.dp)
                        ) {
                            Text("Book Ritual", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (uiState.puja != null) {
            val puja = uiState.puja!!
            Column(
                modifier = Modifier
                    .padding(bottom = padding.calculateBottomPadding())
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Box(modifier = Modifier.height(350.dp)) {
                    AsyncImage(
                        model = puja.heroImagePath,
                        contentDescription = "Puja Hero Image: ${puja.nameEn}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(modifier = Modifier.fillMaxSize().background(
                        Brush.verticalGradient(listOf(Color.Transparent, MaterialTheme.colorScheme.background))
                    ))
                    
                    Surface(
                        modifier = Modifier.padding(16.dp).statusBarsPadding().align(Alignment.TopStart),
                        color = Color.White.copy(alpha = 0.9f),
                        shape = CircleShape,
                        onClick = onBackClick
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier.padding(12.dp))
                    }
                }

                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = puja.nameEn,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Black,
                        fontFamily = com.example.hindupujaa.core.ui.theme.MartelFontFamily,
                        letterSpacing = (-1).sp
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Badge(containerColor = MaterialTheme.colorScheme.primaryContainer) {
                            Text(puja.durationLabel, modifier = Modifier.padding(4.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Badge(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
                            Text(puja.category, modifier = Modifier.padding(4.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    
                    ClayCard(
                        modifier = Modifier.fillMaxWidth(),
                        cornerRadius = 24.dp,
                        elevation = 4.dp,
                        containerColor = Color.White
                    ) {
                        Text(text = "Sacred Backstory", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = puja.backstory,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.DarkGray,
                            lineHeight = 24.sp
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    CollapsibleInfoSection(title = "Benefits", items = puja.benefits)
                    CollapsibleInfoSection(title = "Who Can Perform", items = puja.whoCanPerform)
                    CollapsibleInfoSection(title = "Preparation", items = puja.whenToPerform)
                    
                    if (puja.aartiText.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(32.dp))
                        ClayCard(
                            modifier = Modifier.fillMaxWidth(),
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                            cornerRadius = 28.dp
                        ) {
                            Text(text = "Sacred Aarti", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = puja.aartiText,
                                style = MaterialTheme.typography.bodyLarge,
                                fontFamily = com.example.hindupujaa.core.ui.theme.MartelFontFamily,
                                lineHeight = 30.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }
    }
}

@Composable
fun CollapsibleInfoSection(title: String, items: List<String>) {
    if (items.isEmpty()) return
    var expanded by remember { mutableStateOf(false) }
    
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable { expanded = !expanded }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, 
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
                items.forEach { item ->
                    Row(modifier = Modifier.padding(vertical = 6.dp), verticalAlignment = Alignment.Top) {
                        Text(text = "•", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, modifier = Modifier.width(20.dp))
                        Text(text = item, style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray)
                    }
                }
            }
        }
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
    }
}
