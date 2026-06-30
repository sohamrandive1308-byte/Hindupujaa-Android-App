# HinduPujaa - Divine Services App 🌼

HinduPujaa is a production-grade Android application developed using **Kotlin**, **Jetpack Compose**, and **Firebase**. It aims to revolutionize how ritual samagri (kits) are ordered and delivered, bridging ancient Vedic traditions with modern technology.

---

## 🚀 Vision
**"Blinkit for Puja"** - Curated kits, real-time customization, online payments, and doorstep delivery within 24 hours.

---

## 🛠 Tech Stack
- **UI Framework:** Jetpack Compose (Material 3)
- **Architecture:** MVVM + Clean Architecture + Multi-Module
- **Language:** Kotlin 2.0+
- **Dependency Injection:** Hilt
- **Backend:** Firebase (Auth, Firestore, Realtime DB, Storage, FCM)
- **Payments:** Razorpay Android SDK
- **Messaging:** WhatsApp Business API
- **Maps:** Google Maps SDK for Android

---

## 📂 Project Structure
The project follows a **Multi-Module** Gradle structure for scalability and build performance:

- `:app`: Entry point, navigation graph, and Dagger Hilt setup.
- `:core:ui`: Centralized design system, themes (Saffron & Dark Red), and shared components.
- `:core:domain`: Use-cases, repository interfaces, and data models.
- `:core:data`: Repository implementations, Firebase data sources.
- `:core:common`: Constants, extensions, and common utils.
- `:feature:home`: Discovery surface, category filtering, and trending pujas.
- `:feature:puja_detail`: Detailed ritual info, collapsible sections, and backstory.
- `:feature:kit_builder`: Dynamic kit customization with real-time price calculation.
- `:feature:cart`: Persistent cart management.
- `:feature:checkout`: Delivery details and payment integration.
- `:feature:store`: Standalone Hindu Puja Store for individual items.
- `:feature:orders`: Real-time order tracking and history.
- `:feature:auth`: Phone OTP (SMS) authentication and profile setup.

---

## ✅ Completed Features
1. **Branding & UI**: Full integration of Saffron (#FF9933) and Dark Red (#780000) theme.
2. **Real-time OTP Auth**: Working Firebase Phone Authentication for real number verification.
3. **Dynamic Discovery**: Home screen with Trending Pujas, Categories, and Banners.
4. **Catalogue Seeding**: Automatic seeding of all 13 primary pujas into Firestore.
5. **Kit Customization**: Advanced Kit Builder allowing users to toggle individual items.
6. **Checkout Flow**: Complete flow from Cart to Order Success.
7. **Razorpay Integration**: Pre-configured SDK with build fixes for namespace conflicts.

---

## 🚧 What's Remaining
If you are continuing the work on another device, focus on these areas:

### 1. Firebase Rules & Security
- [ ] **Firestore Rules**: Deploy the rules provided in the PRD to restrict write access.
- [ ] **App Check**: Enable Play Integrity in the Firebase Console.

### 2. Assets & Content
- [ ] **High-Res Images**: Replace the placeholder `heroImagePath` and `thumbnailPath` with real high-resolution images in Firebase Storage.
- [ ] **Lottie Animations**: Add `diya_success.json`, `lotus_empty.json`, and `puja_bell_ring.json` to `app/src/main/res/raw/`.

### 3. Location & Maps
- [ ] **Google Maps Key**: Add your real `GOOGLE_MAPS_API_KEY` to `local.properties`.
- [ ] **Address Picker**: Implement the Google Places Autocomplete in the `CheckoutScreen`.

### 4. Admin Workflow
- [ ] **Order Status**: Currently, order status updates are manual in Firestore. Implement a Cloud Function or Admin Panel to update `order_status`.
- [ ] **WhatsApp API**: Register the `hindupujaa_order_confirmed` template in Meta Business Manager and update `WHATSAPP_API_TOKEN` in `local.properties`.

---

## 🏃 How to Run
1. **Sync Gradle**: Ensure you are using the latest Android Studio Koala.
2. **Setup Firebase**:
   - Download `google-services.json` from your Firebase project and place it in the `app/` directory.
   - Add your SHA-1 and SHA-256 to Firebase project settings.
3. **Configure Keys**: Create a `local.properties` file in the root directory and add:
   ```properties
   GOOGLE_MAPS_API_KEY=your_key_here
   WHATSAPP_API_TOKEN=your_token_here
   RAZORPAY_KEY_ID_TEST=rzp_test_...
   ```
4. **Build & Run**: Use the `:app` module to launch.

---

## ॐ Shubh Puja ॐ
Developed by **Sai Randive** (sairandivework@gmail.com)
 v1.0.0 | June 2026 | Confidential
