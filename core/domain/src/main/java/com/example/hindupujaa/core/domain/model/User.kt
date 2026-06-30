package com.example.hindupujaa.core.domain.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @get:PropertyName("uid") @set:PropertyName("uid") var uid: String = "",
    @get:PropertyName("full_name") @set:PropertyName("full_name") var fullName: String = "",
    @get:PropertyName("phone") @set:PropertyName("phone") var phone: String = "",
    @get:PropertyName("email") @set:PropertyName("email") var email: String = "",
    @get:PropertyName("profile_picture_path") @set:PropertyName("profile_picture_path") var profilePicturePath: String = "",
    @get:PropertyName("fcm_token") @set:PropertyName("fcm_token") var fcmToken: String = "",
    @get:PropertyName("saved_addresses") @set:PropertyName("saved_addresses") var savedAddresses: List<Address> = emptyList(),
    @get:PropertyName("city") @set:PropertyName("city") var city: String = "",
    @get:PropertyName("created_at") @set:PropertyName("created_at") var createdAt: Long = 0,
    @get:PropertyName("last_active") @set:PropertyName("last_active") var lastActive: Long = 0,
    @get:PropertyName("is_blocked") @set:PropertyName("is_blocked") var isBlocked: Boolean = false
)
