package com.example.ScanPower.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ScanPower.Upc.UpcModel
import com.example.ScanPower.data.RapidModel

class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<UpcModel>()
    val amazonItem = MutableLiveData<List<RapidModel>>()


    fun setItem(item: UpcModel) {
        selected.value = item
    }

    fun getItem(): MutableLiveData<UpcModel> {
        return selected
    }

    fun setRapid(item: List<RapidModel>) {
        amazonItem.value = item
    }

    fun getRapid(): MutableLiveData<List<RapidModel>> {
        return amazonItem
    }



}