package data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModelContent : ViewModel {
    constructor() : super()
    var arraydata = ArrayList<DataBase>()
    var livedataarray = MutableLiveData<ArrayList<DataBase>>()

    fun refreshData(){



    }


}