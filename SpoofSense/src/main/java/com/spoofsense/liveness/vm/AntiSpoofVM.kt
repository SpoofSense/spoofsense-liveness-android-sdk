package com.spoofsense.liveness.vm

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoofsense.liveness.model.Request
import com.spoofsense.liveness.model.Response
import com.spoofsense.liveness.repo.DataProviderImp
import com.spoofsense.liveness.util.ImageUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class AntiSpoofVM : ViewModel() {

    fun antiSpoof(bitmap: Bitmap, onResponse: (data: Response?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = ImageUtil.bitmapToBase64(bitmap)
            val response = DataProviderImp.antiSpoof(Request(data))
            withContext(Dispatchers.Main) {
                onResponse(response.data)
            }
        }
    }
}