package data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GlobalViewModel : ViewModel {
    constructor() : super()
    var arraydataviewmodel = ArrayList<DataUnit>()
    var runCommand = false
    var livedataarray = MutableLiveData<ArrayList<DataUnit>>()

    var  dbHandle  =ExternalDataBase()

    fun makeConnectionAndGetData(){

        viewModelScope.launch {
           // do {
             //   if(!dbHandle.dbstatus){
               //     dbHandle.connectToDB()
                //}else{
                    val tempArray = dbHandle.fetchData("test_tbl")
                    livedataarray.postValue(tempArray)
                //}

                delay(1000L)

            //}while (runCommand)
           // dbHandle.connect.close()
        }
    }


    fun getLiveDataValue() : LiveData<ArrayList<DataUnit>>{

      return  livedataarray as LiveData<ArrayList<DataUnit>>
    }


}