# HinduPujaa — Divine Services App 🌼

**HinduPujaa** is a production-grade, multi-module Android application (v2.0.0) developed using **Kotlin**, **Jetpack Compose**, and **Neon PostgreSQL**. It bridges ancient Vedic traditions with modern on-demand delivery technology.

---

## 🚀 Vision
**"Blinkit for Puja"** — Real-time kit customization, online payments, and doorstep delivery within 24 hours.

---

## 🛠 Project Architecture (v2.0.0)
The app has been re-architected from Firebase to a custom **REST API** backend to minimize recurring costs and improve data scalability.

- **Architecture:** MVVM + Clean Architecture + Multi-Module Gradle.
- **Backend:** Node.js (or Ktor) hosted on Render/Railway.
- **Database:** Neon Serverless PostgreSQL.
- **Local Cache:** Room DB (Full Offline Browsing support).
- **Security:** EncryptedSharedPreferences for JWT session management.
- **Push Notifications:** OneSignal (Unlimited free subscribers).
- **Animations:** Claymorphism v2 + Canvas-based Invoice Unroll.

---

## 📂 Multi-Module Structure
- `:app`: Entry point, Hilt setup, and `NavHost`.
- `:core:ui`: Theme, `ClayComponents`, and shared animations.
- `:core:domain`: Data models and Repository interfaces.
- `:core:data`: Ktor Client (REST), Room Local DB, and Repository implementations.
- `:feature:*`: Modularized features (Home, PujaDetail, KitBuilder, Store, Orders, Auth, Profile).

---

## ⚙️ Setup & Environment Variables

### 1. Android Configuration (`local.properties`)
Create a `local.properties` file in the root directory (Git Ignored):
```properties
# Backend API URL
API_BASE_URL=https://api.hindupujaa.com

# Payment Keys
RAZORPAY_KEY_ID_TEST=rzp_test_...
RAZORPAY_KEY_ID_LIVE=rzp_live_...

# Google Services (Get from Google Cloud Console)
GOOGLE_OAUTH_CLIENT_ID=your_web_client_id.apps.googleusercontent.com
GOOGLE_MAPS_API_KEY=your_android_maps_key

# Push Notifications
ONESIGNAL_APP_ID=your_onesignal_id
```

### 2. Backend Configuration (`.env`)
The backend requires these keys for critical flows like WhatsApp confirmation:
```bash
DATABASE_URL=postgresql://user:pass@host.neon.tech/db
JWT_SECRET=your_long_random_string
WHATSAPP_API_TOKEN=meta_permanent_token
OWNER_WHATSAPP_NUMBER=919175799251
```

---

## 🖼 Asset & Image Management
The app uses **Cloudflare R2** (or Supabase) for high-res images. The backend stores public URLs.

| Folder | Naming Convention | Use Case |
| :--- | :--- | :--- |
| `pujas/hero/` | `satyanarayan-puja.png` | Detail screen top image |
| `pujas/thumbnail/` | `satyanarayan-puja.png` | Home screen cards |
| `store_products/` | `brass-diya.png` | Individual store items |
| `res/raw/` | `puja_bell_ring.json` | Lottie success animations |

---

## 👩‍💻 Development Guide

### Option A: Using Android Studio (Recommended)
1. Install **Android Studio Koala (2024.1.2+)**.
2. Open the project and wait for **Gradle Sync**.
3. Place your `google-services.json` in the `app/` folder.
4. Build and Run the `:app` module.

### Option B: Using VS Code (For Testing/Light Development)
If you do not have Android Studio installed:
1. **Requirements**: 
   - Install **JDK 17** (or 21).
   - Install **Android SDK Platform-Tools**.
   - Install VS Code Extensions: `Kotlin`, `Gradle for Java`.
2. **Setup**:
   - Create an Android Emulator via `avdmanager` or connect a physical phone.
   - Set `ANDROID_HOME` environment variable to your SDK path.
3. **Build & Install via Terminal**:
   ```bash
   # Clean build
   ./gradlew clean
   
   # Assemble Debug APK
   ./gradlew :app:assembleDebug
   
   # Install on connected device
   ./gradlew installDebug
   ```

---

## 🚧 Roadmap: Remaining Production Steps
1. **Backend Deployment**: Host the provided Node.js/Ktor server and migrate the Neon DB schema.
2. **Seeding**: Trigger the `FirebaseSeeder` (or SQL script) to populate the 13 pujas and 32 products.
3. **Razorpay Webhooks**: Configure the webhook URL in the Razorpay dashboard to ensure WhatsApp messages send even if the app is closed.
4. **App Check**: Enable **Play Integrity** in the Firebase/Google Console to secure your API endpoints.

---

## ॐ Shubh Puja ॐ
Designed & Developed for **Sai Randive** (sairandivework@gmail.com)
*Confidential © 2026 HinduPujaa*
