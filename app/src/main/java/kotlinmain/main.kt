package kotlinmain

import data.ExternalDataBase
import java.lang.Exception
import java.sql.DriverManager

fun main() {


val num1 = (5.0/11.0)-(6.0/22.0)*(6.0/22.0)
    println("num1 = $num1")
    val num2 = (2.0/11.0)-(4.0/22.0)*(4.0/22.0)
    println("num2 = $num2")
    val num3 = (4.0/11.0)-(5.0/22.0)*(5.0/22.0)
    println("num3 = $num3")


try {
val mydb = ExternalDataBase()

    mydb.connectToDB()
    if(mydb.dbstatus){
        println("Successful")
    }else{
        println("Error")
    }
}catch (exp :Exception){
    println("Error")
}





}


