package com.spoofsense.liveness.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

internal object ImageUtil {

    fun compressImage(image: Bitmap, quality: Int = 75): Bitmap {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, quality, baos)
        val imageBytes = baos.toByteArray()
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    fun bitmapToBase64(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }
}