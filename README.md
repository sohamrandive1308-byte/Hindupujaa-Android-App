# HinduPujaa - Divine Services App 🌼

**Version 2.0.0 — Neon Postgres & Independent Auth Edition**

HinduPujaa is a production-grade Android application developed using **Kotlin**, **Jetpack Compose**, and a **RESTful Backend**. This version has been re-architected to remove Firebase dependencies, moving to a self-hosted API and Neon Postgres database for better cost control and scalability.

---

## 🚀 Vision
**"Blinkit for Puja"** - Curated kits, real-time customization, online payments, and doorstep delivery within 24 hours.

---

## 🛠 Tech Stack v2.0
- **UI Framework:** Jetpack Compose (Material 3) with **Claymorphism v2**
- **Architecture:** MVVM + Clean Architecture + Multi-Module
- **Language:** Kotlin 2.0+
- **Dependency Injection:** Hilt
- **Backend:** Node.js/Express or Ktor (Self-hosted)
- **Database:** Neon Serverless PostgreSQL
- **Local Cache:** Room DB (Offline support)
- **Authentication:** Custom Email/Pass (Bcrypt) + Google Sign-In + SMS OTP (2Factor.in)
- **Storage:** Cloudflare R2 / Supabase Storage (S3 Compatible)
- **Push:** OneSignal
- **Payments:** Razorpay Android SDK

---

## 📂 Project Structure
- `:app`: Entry point, navigation, and Dagger Hilt setup.
- `:core:ui`: Design system, themes (Saffron & Dark Red), **ClayComponents**, and **Custom Animations**.
- `:core:domain`: Use-cases, repository interfaces, and relational data models.
- `:core:data`: REST API integration (Ktor), Room Local DB, and repository implementations.
- `:core:common`: Constants, extensions, and common utils.
- `:feature:*`: Modularized features (Home, PujaDetail, KitBuilder, Store, Orders, Auth, Profile).

---

## ✅ v2.0 Key Enhancements
1. **REST Re-Architecture**: Ready to consume custom REST APIs instead of Firebase SDKs.
2. **Offline Mode**: Room DB implemented to cache Pujas and Products for seamless browsing without internet.
3. **Advanced UI**: Claymorphic cards with soft shadows and interactive bounce animations.
4. **Independent Auth**: Support for Email, Google, and SMS OTP through our own backend.
5. **Comprehensiveness**: All 13 pujas and 32 store products fully mapped to the new relational schema.

---

## 🚧 Next Steps (Continuing the Project)
If you are moving to another device, focus on these areas:

### 1. Backend Setup
- [ ] **Deploy API**: Host the Node.js or Ktor backend on Render/Railway.
- [ ] **Neon DB**: Create a project at `neon.tech` and run the Prisma migrations provided in the SRS.
- [ ] **Environment**: Update `API_BASE_URL` in `local.properties`.

### 2. Assets & Media
- [ ] **R2 Upload**: Upload high-res images to Cloudflare R2 and update the URLs in Postgres.
- [ ] **Lottie**: Ensure `puja_bell_ring.json` and `diya_success.json` are in the `raw` resource folder.

### 3. Maps & SMS
- [ ] **API Keys**: Add `GOOGLE_MAPS_API_KEY` and `ONESIGNAL_APP_ID` to `local.properties`.
- [ ] **OTP Provider**: Register with `2Factor.in` and configure the key on the backend.

---

## 🏃 How to Run
1. **Sync Gradle**: Ensure Android Studio Koala is updated.
2. **Local Properties**: Create `local.properties` with your respective API keys.
3. **Build & Run**: Use the `:app` module.

---

## ॐ Shubh Puja ॐ
Developed by **Sai Randive** (sairandivework@gmail.com)
 v2.0.0 | June 2026 | Confidential
