# ProGuard & R8 Optimization Rules for Farsh Bazaar (Release APK & AAB)

# Room Database
-keep class androidx.room.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-dontwarn androidx.room.paging.**

# Data Models & Entities
-keep class com.example.farshbazar.data.model.** { *; }
-keepclassmembers class com.example.farshbazar.data.model.** { *; }

# BuildConfig for automated API keys
-keep class com.example.farshbazar.BuildConfig { *; }
-keepclassmembers class com.example.farshbazar.BuildConfig { *; }

# Kotlinx Serialization
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod
-keepclassmembers class * {
    @kotlinx.serialization.SerialName <fields>;
    @kotlinx.serialization.Serializable <fields>;
    @kotlinx.serialization.Transient <fields>;
}
-keepclassmembers class * extends kotlinx.serialization.internal.GeneratedSerializer {
    <fields>;
    <methods>;
}

# Coil Image Loader
-dontwarn coil.**
-keep class coil.** { *; }

# Jetpack Compose
-keep class androidx.compose.** { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory { *; }
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler { *; }
