package com.example.farshbazar.data.service

import android.util.Log
import com.example.farshbazar.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object GeminiStoryService {

    private const val TAG = "GeminiStoryService"
    private const val GEMINI_MODEL = "gemini-2.5-flash"

    /**
     * Checks if a valid, non-placeholder Gemini API key has been injected via BuildConfig.
     */
    fun isApiKeyConfigured(): Boolean {
        return try {
            val key = BuildConfig.GEMINI_API_KEY
            key.isNotBlank() && !key.contains("YOUR_") && key != "null"
        } catch (e: Throwable) {
            false
        }
    }

    /**
     * Returns a safely masked version of the configured API key (e.g. "AQ.Ab8...uQg").
     */
    fun getMaskedApiKey(): String {
        return try {
            val key = BuildConfig.GEMINI_API_KEY
            if (isApiKeyConfigured() && key.length > 8) {
                "${key.take(6)}...${key.takeLast(4)}"
            } else {
                "Not Configured (Using Local AI Engine)"
            }
        } catch (e: Throwable) {
            "Not Configured"
        }
    }

    /**
     * Generates an evocative carpet story using live Gemini API with automated fallback.
     */
    suspend fun generateCarpetStory(carpetType: String, style: String): String = withContext(Dispatchers.IO) {
        val targetType = carpetType.ifBlank { "Persian Handwoven Silk Masterpiece" }

        if (isApiKeyConfigured()) {
            try {
                val apiKey = BuildConfig.GEMINI_API_KEY
                val endpoint = "https://generativelanguage.googleapis.com/v1beta/models/$GEMINI_MODEL:generateContent?key=$apiKey"
                val url = URL(endpoint)
                val conn = (url.openConnection() as HttpURLConnection).apply {
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json; charset=utf-8")
                    connectTimeout = 12000
                    readTimeout = 15000
                    doOutput = true
                    doInput = true
                }

                val prompt = """
                    Write a brief, evocative story about an authentic Iranian handwoven carpet ($targetType) in a '$style' style. 
                    Highlight Persian weaving heritage, natural dyes, silk or wool knotting, and emotional resonance. 
                    Keep the response under 120 words. Provide both a Persian poetic quote and English reflection.
                """.trimIndent()

                val requestJson = JSONObject().apply {
                    val contents = JSONArray().apply {
                        val contentObj = JSONObject().apply {
                            val parts = JSONArray().apply {
                                put(JSONObject().apply { put("text", prompt) })
                            }
                            put("parts", parts)
                        }
                        put(contentObj)
                    }
                    put("contents", contents)
                }

                OutputStreamWriter(conn.outputStream, "UTF-8").use { writer ->
                    writer.write(requestJson.toString())
                    writer.flush()
                }

                val responseCode = conn.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(conn.inputStream, "UTF-8"))
                    val responseStr = reader.use { it.readText() }
                    conn.disconnect()

                    val rootObj = JSONObject(responseStr)
                    val candidates = rootObj.optJSONArray("candidates")
                    if (candidates != null && candidates.length() > 0) {
                        val firstCandidate = candidates.getJSONObject(0)
                        val content = firstCandidate.getJSONObject("content")
                        val parts = content.getJSONArray("parts")
                        if (parts.length() > 0) {
                            val generatedText = parts.getJSONObject(0).getString("text")
                            return@withContext generatedText.trim()
                        }
                    }
                } else {
                    Log.w(TAG, "Gemini API returned code: $responseCode, falling back to curated local story.")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed calling Gemini API, falling back safely: ${e.message}")
            }
        }

        // Graceful fallback to offline authentic Persian story generator
        getLocalCuratedStory(targetType, style)
    }

    /**
     * Authentic offline Persian carpet story generator.
     */
    fun getLocalCuratedStory(carpetType: String, style: String): String {
        return when (style.lowercase()) {
            "poetic" -> """
                Woven beneath the starlit skies of Isfahan,
                Each silk thread sings of ancient dawns and roses red.
                In the quiet knots of the $carpetType,
                Time stands still, a sacred tapestry of soul and legend.
                
                The central medallion mirrors the celestial dome,
                Where master fingers turned pure wool to timeless poetry.
            """.trimIndent()

            "historical" -> """
                The artistry of the $carpetType traces back through centuries of guild tradition. 
                Crafted during the golden ages of Persian knotting, master artisans utilized madder root for deep crimson and wild indigo for celestial blues. 
                This carpet represents an unbroken heritage preserved through rural workshops and family looms across generations.
            """.trimIndent()

            "mystical" -> """
                Look closely at the garden motif within this $carpetType. It is not merely wool and silk, but a mirror of Paradise. 
                Every floral palmette represents a step along the seeker's path, where knot by knot, the artisan breathes life into geometric unity. 
                To walk upon this rug is to enter a living dream of eternity.
            """.trimIndent()

            else -> """
                In a quiet courtyard workshop, a multi-generational family of weavers dedicated fourteen months to creating the $carpetType. 
                Using hand-spun mountain wool and pomegranate dye highlights, they bound over 450 knots per square inch. 
                The pattern symbolizes harmony and prosperity, created to endure as a cherished heirloom for centuries.
            """.trimIndent()
        }
    }
}
