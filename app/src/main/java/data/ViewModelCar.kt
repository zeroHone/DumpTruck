package data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelCar : ViewModel() {

    var arraydataviewmodel = ArrayList<DataBase>()

    var livedataarray = MutableLiveData<ArrayList<DataBase>>()

    val dbHandle = ExternalDataBase("test_db")



    fun refreshData(){
        CoroutineScope(Dispatchers.Default).launch {

                val tempArray = dbHandle.fetchData()
                livedataarray.postValue(tempArray)


        }
    }

    fun getLiveDataValue() : LiveData<ArrayList<DataBase>> {
/*val data1 = DataBase(1.1F, "Salam")
        val data2 = DataBase(2.2F, "Hello")
        arraydata.add(data1)
        arraydata.add(data2)
        livedataarray.value = arraydata*/

        return  livedataarray as LiveData<ArrayList<DataBase>>
    }

}