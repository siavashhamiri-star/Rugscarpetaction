
# Farsh Bazaar (فرش بازار) - Android App

**Connecting Iranian handwoven carpet artisans directly with global enthusiasts.**

---

## 📱 Features & Highlights

- **Marketplace & Virtual Showrooms**: Browse authentic Persian handwoven carpets and global vendor profiles with verified weaver badges.
- **AI Carpet Story Generator ("The Magic Carpet's Tale")**: Weaves rich narrative, historical, poetic, and mystical stories based on carpet origin and style.
- **FB New Meta Ecosystem**: Directory of 11 interconnected apps from Farsh Bazaar to Tavana City with unified identity features.
- **The Book of Creation ("کتاب آفرینش")**: Interactive manifesto detailing origin stories, cosmic philosophy, and creator creed in English & Persian.
- **Client Reviews & Showroom Management**: Artisans can add carpets, edit virtual showrooms, and receive verified customer ratings.
- **Offline Persistence & Room Database**: Complete local cache with reactive Flow streams.

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material Design 3)
- **Local Database:** Room Database with KSP
- **Architecture:** MVVM + Clean Repository Pattern
- **Image Loading:** Coil
- **Navigation:** Navigation Compose with `@Serializable` type-safe routes
- **Secret Management:** Google Maps Platform Secrets Gradle Plugin (`.env` & `BuildConfig`)
- **Build System:** Gradle (Kotlin DSL) with ProGuard & Android App Bundle (AAB) support

---

## 🔐 Automated API Key Management (اتوماسیون کلیدهای API)

The project uses the **Secrets Gradle Plugin** to automatically inject API keys into `BuildConfig` at compile-time from `.env` or AI Studio Secrets, ensuring secrets are never hardcoded or committed to git.

### Supported Keys:
- `GEMINI_API_KEY`: Injected into `BuildConfig.GEMINI_API_KEY` for live AI storytelling via Gemini 2.5.
- `FARSH_BAZAR_API_KEY`: Injected into `BuildConfig.FARSH_BAZAR_API_KEY` for guild verification.

### How to Configure Keys:
1. In Google AI Studio: Enter your keys into the **Secrets panel**.
2. Or locally: Copy `.env.example` to `.env` and fill in your keys:
   ```bash
   cp .env.example .env
   ```
3. The app automatically detects keys in `GeminiStoryService`. If offline or without a key, it seamlessly falls back to the curated local legend engine without crashing.

> ⚠️ **Security Warning (هشدار امنیتی)**:
> In client-side Android applications, compiled APKs and AABs can be reverse-engineered or decompiled, and embedded keys could be inspected by anyone with access to the raw package file. For high-security enterprise deployments, proxy your API requests through a secure server-side backend. Never publicly distribute release packages with unrestricted production API keys.

---

## 📦 Building APK and AAB (آماده‌سازی فایل‌های APK و AAB)

The project is pre-configured with Android App Bundle optimization, bilingual resource preservation (English & Persian), ProGuard/R8 rules, and release signing fallbacks.

### 1. Build Release APK (فایل نصبی مستقیم):
```bash
gradle :app:assembleRelease
```
*Output location:* `app/build/outputs/apk/release/app-release.apk`

### 2. Build Release AAB (Android App Bundle برای گوگل پلی):
```bash
gradle :app:bundleRelease
```
*Output location:* `app/build/outputs/bundle/release/app-release.aab`

### 3. Build Debug APK (برای تست و توسعه):
```bash
gradle :app:assembleDebug
```
*Output location:* `app/build/outputs/apk/debug/app-debug.apk`

