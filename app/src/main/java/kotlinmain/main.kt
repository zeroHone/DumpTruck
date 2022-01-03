package kotlinmain

fun main() {

    val data1 = MyData(12.25F, "Salam")
    val data2 = MyData(12.25F, "Salam")
    val myarray1 = ArrayList<MyData>()
    myarray1.add(data1)

    val myarray2 = ArrayList<MyData>()
    myarray2.add(data2)

    if(myarray1 == myarray2){
        println("Equals")
    }else{
        println("Different")
    }





}

data class MyData(val value : Float, val name : String)
