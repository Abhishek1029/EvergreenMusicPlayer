package com.abhishek.evergreenmusicplayer.utils

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.abhishek.evergreenmusicplayer.data.Songs
import com.google.gson.Gson

class AssetParamType : NavType<Songs>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Songs? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Songs {
        return Gson().fromJson(value,Songs::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Songs) {
        bundle.putParcelable(key, value)
    }
}
