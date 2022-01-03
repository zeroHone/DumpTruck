package data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ModelViewContent : ViewModel {
    constructor() : super()
    var arraydataviewmodel = ArrayList<DataBase>()
    var runCommand = false
    var livedataarray = MutableLiveData<ArrayList<DataBase>>()

    val dbHandle = ExternalDataBase("test_db")








    fun refreshData(){
    CoroutineScope(Default).launch {
        while (runCommand) {
            val tempArray = dbHandle.fetchData()
            livedataarray.postValue(tempArray)
            delay(1000L)
        }
    }
    }

    fun getLiveDataValue() : LiveData<ArrayList<DataBase>>{
/*val data1 = DataBase(1.1F, "Salam")
        val data2 = DataBase(2.2F, "Hello")
        arraydata.add(data1)
        arraydata.add(data2)
        livedataarray.value = arraydata*/

      return  livedataarray as LiveData<ArrayList<DataBase>>
    }


}