package com.euntaek.pokeshop.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Creates a custom NavType for passing serializable objects in Compose Type-Safe Navigation.
 *
 * @param isNullableAllowed Indicates if the type can accept null values.
 * @return A custom NavType for the specified type [T].
 */
inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun get(bundle: Bundle, key: String): T? {
        val serializedValue = bundle.getString(key) ?: return null
        return Json.decodeFromString(serializedValue)
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: T): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(value))
    }
}