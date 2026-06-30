package com.example.hindupujaa.core.domain.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    @get:PropertyName("order_id") @set:PropertyName("order_id") var orderId: String = "",
    @get:PropertyName("user_id") @set:PropertyName("user_id") var userId: String = "",
    @get:PropertyName("puja_id") @set:PropertyName("puja_id") var pujaId: String = "",
    @get:PropertyName("puja_name") @set:PropertyName("puja_name") var pujaName: String = "",
    @get:PropertyName("puja_date") @set:PropertyName("puja_date") var pujaDate: Long = 0,
    @get:PropertyName("delivery_address") @set:PropertyName("delivery_address") var deliveryAddress: Address = Address(),
    @get:PropertyName("city") @set:PropertyName("city") var city: String = "",
    @get:PropertyName("selected_items") @set:PropertyName("selected_items") var selectedItems: List<OrderItem> = emptyList(),
    @get:PropertyName("total_amount") @set:PropertyName("total_amount") var totalAmount: Double = 0.0,
    @get:PropertyName("payment_status") @set:PropertyName("payment_status") var paymentStatus: String = "pending",
    @get:PropertyName("razorpay_order_id") @set:PropertyName("razorpay_order_id") var razorpayOrderId: String = "",
    @get:PropertyName("razorpay_payment_id") @set:PropertyName("razorpay_payment_id") var razorpayPaymentId: String = "",
    @get:PropertyName("razorpay_signature") @set:PropertyName("razorpay_signature") var razorpaySignature: String = "",
    @get:PropertyName("order_status") @set:PropertyName("order_status") var orderStatus: String = "placed",
    @get:PropertyName("whatsapp_sent") @set:PropertyName("whatsapp_sent") var whatsappSent: Boolean = false,
    @get:PropertyName("notes") @set:PropertyName("notes") var notes: String = "",
    @get:PropertyName("created_at") @set:PropertyName("created_at") var createdAt: Long = 0,
    @get:PropertyName("updated_at") @set:PropertyName("updated_at") var updatedAt: Long = 0
)

@Serializable
data class OrderItem(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("name_en") @set:PropertyName("name_en") var nameEn: String = "",
    @get:PropertyName("price") @set:PropertyName("price") var price: Double = 0.0
)
