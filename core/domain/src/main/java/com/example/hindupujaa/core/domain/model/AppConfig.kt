package com.example.hindupujaa.core.domain.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class AppConfig(
    @get:PropertyName("delivery_cities") @set:PropertyName("delivery_cities") var deliveryCities: List<String> = emptyList(),
    @get:PropertyName("whatsapp_number") @set:PropertyName("whatsapp_number") var whatsappNumber: String = "",
    @get:PropertyName("razorpay_key_id") @set:PropertyName("razorpay_key_id") var razorpayKeyId: String = "",
    @get:PropertyName("min_app_version") @set:PropertyName("min_app_version") var minAppVersion: Int = 0,
    @get:PropertyName("maintenance_mode") @set:PropertyName("maintenance_mode") var maintenanceMode: Boolean = false,
    @get:PropertyName("banner_title") @set:PropertyName("banner_title") var bannerTitle: String = "",
    @get:PropertyName("banner_image_path") @set:PropertyName("banner_image_path") var bannerImagePath: String = "",
    @get:PropertyName("banner_link") @set:PropertyName("banner_link") var bannerLink: String = "",
    @get:PropertyName("perishable_disclaimer") @set:PropertyName("perishable_disclaimer") var perishableDisclaimer: String = "",
    @get:PropertyName("terms_and_conditions") @set:PropertyName("terms_and_conditions") var termsAndConditions: String = ""
)
