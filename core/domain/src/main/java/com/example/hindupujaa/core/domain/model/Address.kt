package com.example.hindupujaa.core.domain.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("full_name") @set:PropertyName("full_name") var fullName: String = "",
    @get:PropertyName("phone") @set:PropertyName("phone") var phone: String = "",
    @get:PropertyName("address_line") @set:PropertyName("address_line") var addressLine: String = "",
    @get:PropertyName("city") @set:PropertyName("city") var city: String = "",
    @get:PropertyName("is_default") @set:PropertyName("is_default") var isDefault: Boolean = false
)
