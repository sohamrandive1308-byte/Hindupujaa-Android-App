package com.example.hindupujaa.feature.checkout.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hindupujaa.core.ui.components.ClayButton
import com.example.hindupujaa.core.ui.components.ClayCard

@Composable
fun OrderSuccessScreen(
    orderId: String,
    onViewOrderClick: () -> Unit,
    onBackHomeClick: () -> Unit
) {
    var revealInvoice by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (revealInvoice) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = EaseOutCubic),
        label = "invoice"
    )

    LaunchedEffect(Unit) {
        revealInvoice = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Invoice Paper-Scroll Animation
        InvoicePaperScroll(progress = progress, orderId = orderId)

        Spacer(modifier = Modifier.height(48.dp))

        AnimatedVisibility(
            visible = progress > 0.9f,
            enter = fadeIn() + slideInVertically { it / 2 }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ClayButton(
                    onClick = onBackHomeClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Back to Home", fontWeight = FontWeight.Bold)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                TextButton(onClick = onViewOrderClick) {
                    Text("Track Order on WhatsApp", color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun InvoicePaperScroll(progress: Float, orderId: String) {
    val paperColor = Color(0xFFFFFDE7)
    val saffron = Color(0xFFFF9933)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width * 0.85f
            val maxHeight = size.height * 0.9f
            val currentHeight = maxHeight * progress
            val left = (size.width - width) / 2
            
            // Draw unrolling paper
            drawRoundRect(
                color = paperColor,
                topLeft = Offset(left, 0f),
                size = Size(width, currentHeight),
                cornerRadius = CornerRadius(8f, 8f)
            )

            // Draw top "scroll" cylinder
            drawOval(
                color = Color.LightGray,
                topLeft = Offset(left - 10f, -20f),
                size = Size(width + 20f, 40f)
            )
            
            // Staggered lines for invoice content (Visual representation)
            if (progress > 0.3f) {
                val lineStart = left + 40f
                val lineEnd = left + width - 40f
                for (i in 0..8) {
                    val lineY = 60f + (i * 40f)
                    if (lineY < currentHeight - 20f) {
                        drawLine(
                            color = Color.Gray.copy(alpha = 0.3f),
                            start = Offset(lineStart, lineY),
                            end = Offset(if (i % 3 == 0) lineStart + 100f else lineEnd, lineY),
                            strokeWidth = 4f
                        )
                    }
                }
            }

            // Bottom "seal"
            if (progress >= 1f) {
                drawCircle(
                    color = saffron.copy(alpha = 0.2f),
                    radius = 40f,
                    center = Offset(left + width / 2, maxHeight - 60f)
                )
            }
        }

        // Overlay text content
        if (progress > 0.5f) {
            Column(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .width(IntrinsicSize.Max)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "INVOICE",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Black,
                    color = saffron
                )
                Text(
                    text = "HPJ-${orderId.takeLast(6)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "Payment Received",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF15803D)
                )
            }
        }
    }
}
