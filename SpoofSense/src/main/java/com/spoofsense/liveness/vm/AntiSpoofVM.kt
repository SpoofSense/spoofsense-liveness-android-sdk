package com.spoofsense.liveness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoofsense.liveness.model.Request
import com.spoofsense.liveness.model.Response
import com.spoofsense.liveness.repo.DataProviderImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class AntiSpoofVM: ViewModel() {

    fun antiSpoof(data:String,onResponse:(data: Response?)->Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val response = DataProviderImp.antiSpoof(Request(data))
            withContext(Dispatchers.Main) {
                onResponse(response.data)
            }
        }
    }
}