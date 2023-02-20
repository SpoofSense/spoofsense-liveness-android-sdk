package com.spoofsense.liveness.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

internal object ImageUtil {

    private fun compressImage(image: Bitmap, quality: Int = 75): Bitmap {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, quality, baos)
        val imageBytes = baos.toByteArray()
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    fun bitmapToBase64(bitmap: Bitmap): String {
        val convertedBitmap = convertTo4By3(bitmap)
        val compressedImage = compressImage(convertedBitmap)
        val baos = ByteArrayOutputStream()
        compressedImage.compress(Bitmap.CompressFormat.JPEG, 75, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    private fun convertTo4By3(bitmap: Bitmap): Bitmap {
        val originalHeight = bitmap.height

        val targetWidth = originalHeight * 3 / 4

        return Bitmap.createScaledBitmap(bitmap, targetWidth, originalHeight, true)
    }
}