# HinduPujaa — Divine Services Android Application 🌼

**HinduPujaa** is a production-grade, multi-module Android application developed using **Kotlin**, **Jetpack Compose**, and **Firebase**. It bridges ancient Vedic tradition with modern on-demand delivery technology.

> "Blinkit for Puja" — Real-time kit customization, online payments, and doorstep delivery within 24 hours.

---

## 🎨 Design System: Claymorphism & Minimalism
The app follows a modern **Claymorphic** design language, combining soft, rounded 3D surfaces with a clean, **Minimalist** layout.

### Brand Identity
- **Primary Color (Saffron):** `#FF9933` — Used for CTAs, FABs, and primary branding.
- **Secondary Color (Dark Red):** `#780000` — Used for section headers and premium elements.
- **Background (OffWhite):** `#FDFBF7` — Soft, warm spiritual base.
- **Typography:**
  - **Martel Bold:** Sacred hero text and screen titles.
  - **Poppins:** Modern, highly readable body and interface text.

### Visual Components
- **ClayCards:** Soft-shadowed, highly rounded (28dp-32dp) containers for a "squishy" tactile feel.
- **Gradients:** Saffron-to-Crimson linear gradients used in banners and splash screens.
- **Animations:** 0.96x scale press effects, skeleton shimmer loaders, and smooth horizontal slide transitions.

---

## 📂 Project Architecture & Multi-Module Structure
The project uses **MVVM + Clean Architecture** and is separated into feature-based modules for maximum scalability.

- `:app` — Entry point, Dagger Hilt Application, and AppNavGraph.
- `:core:ui` — Theme, Typography, and shared `ClayComponents`.
- `:core:domain` — Pure Kotlin models and repository interfaces.
- `:core:data` — Firebase implementations (Firestore, RTDB, Auth).
- `:feature:home` — Discovery surface with "Trending 🔥" and "Categories".
- `:feature:puja_detail` — Collapsible ritual info and Parallax Hero view.
- `:feature:kit_builder` — Real-time customization engine with price animation.
- `:feature:store` — Standalone store for individual puja essentials.
- `:feature:auth` — SMS-based OTP Auth & Google Sign-In via Credential Manager.
- `:feature:orders` — Real-time order tracking with status chips.

---

## ⚙️ Application Workflow
The app follows a strict production flow:
1. **Splash (2s)**: Brand intro + Auth check.
2. **Onboarding**: Value proposition (Delivery, Authenticity, Kits).
3. **Authentication**: Verify real numbers via **Firebase SMS OTP** or **Google One Tap**.
4. **Profile Setup**: Collect name and city (Pune/Nagpur).
5. **Discovery (Home)**: High-density browsing of 13 primary pujas.
6. **Selection**: Detailed ritual information (Backstory, Vidhi, Benefits).
7. **Customization**: Toggle specific items in the kit (Included vs Rental).
8. **Checkout**: Delivery details + Razorpay Payment Gateway.
9. **Success**: Celebratory Lottie animation + WhatsApp tracking trigger.

---

## 🛠️ API & Integration Details

### 1. Firebase Firestore (Primary Data)
- **`/pujas`**: Stores the 13 ritual kits.
- **`/store_products`**: Stores individual items for sale.
- **`/users`**: User profiles and saved addresses.
- **`/orders`**: Order history and real-time tracking status.

### 2. Firebase Auth (Identity)
- **Phone Auth**: Real SMS verification (+91 prefix enforced).
- **Google Sign-In**: Credential Manager API integration.

### 3. Razorpay SDK
- **Environment**: Configured for `rzp_test` and `rzp_live`.
- **Paise Logic**: Automatically multiplies total by 100 for API calls.
- **Fix**: Built-in packaging excludes for `META-INF` to prevent namespace conflicts.

### 4. WhatsApp Business API
- **Endpoint**: `https://graph.facebook.com/v19.0/{phone_id}/messages`
- **Owner Number**: `919175799251` (Configured in local.properties).

---

## ✅ What's Done (Production Ready)
- [x] **Redesigned UI**: Complete Claymorphic & Minimalist overhaul.
- [x] **Real-time OTP**: SMS verification for real numbers working via Firebase.
- [x] **Type-Safe Navigation**: Resolved all crashes when switching bottom bar tabs.
- [x] **Auto-Seeding**: All 13 pujas and store products populated automatically on first run.
- [x] **Accessibility**: `contentDescription` added to every image/icon.
- [x] **Optimization**: Resource shrinking and minification enabled for release.

---

## 🚧 Roadmap (To be completed on other device)

### 🔑 Local Keys (`local.properties`)
Create this file and add:
```properties
GOOGLE_MAPS_API_KEY=your_key_here
RAZORPAY_KEY_ID_TEST=rzp_test_...
WHATSAPP_API_TOKEN=your_token_here
```

### 📍 Maps & Location
- [ ] **Google Places**: Integrate the autocomplete picker into `CheckoutScreen`.
- [ ] **Markers**: Show the delivery origin/destination on a map in `OrderDetail`.

### 🛡️ Security
- [ ] **Firestore Rules**: Deploy the rules from Section 3.7 of PRD.
- [ ] **App Check**: Enable Play Integrity in Firebase Console.

### 🍱 Assets
- [ ] **Lottie Files**: Replace placeholder text with `.json` files in `res/raw`.
- [ ] **Hero Images**: Replace placeholder URLs with actual high-res Firebase Storage paths.

---

## 🏃 How to Run
1. **Gradle Sync**: Use Android Studio Koala (2024.1.2+).
2. **Google Services**: Place your `google-services.json` in the `app/` directory.
3. **SHA-1**: Run `./gradlew signingReport` and add the SHA-1 to your Firebase Console.
4. **Build**: Run the `:app` module.

---

## ॐ Shubh Puja ॐ
Designed & Developed for **Sai Randive** (sairandivework@gmail.com)
*Confidential © 2026 HinduPujaa*
