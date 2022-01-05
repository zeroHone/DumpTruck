package data


import android.util.Log
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class ExternalDataBase() {

    val ipAddress = DataStorage.IP
    val portAddress = DataStorage.PORT
    val dbName = "dataBaseName"
    val password = DataStorage.PASSWORD
    val username = DataStorage.USERNAME
    var dbstatus = false
    lateinit var connect : Connection


    lateinit var sqlStatement : Statement



    fun connectToDB(){


        try {
            Class.forName("com.mysql.jdbc.Driver")
/*
            // Setup the connection with the DB
            val connect = DriverManager
                .getConnection(
                    "jdbc:mysql://${ipAddress}:${portAddress}/${dbName}",
                             "${username}",
                             "${password}")
*/
            // Setup the connection with the DB
            connect = DriverManager
                .getConnection(
                    "jdbc:mysql://172.17.180.100:3306/test_db",
                    "tamsidb_admin",
                    "1234!@#$")


            sqlStatement = connect.createStatement()
            dbstatus =true


        }catch (exp : Exception){
            dbstatus=false
            Log.d("ABCDEFG","${exp.message}")


        }


    }
    fun fetchData(tbl : String):ArrayList<DataUnit>{
        val tblName = tbl

        val arrayOfResults = ArrayList<DataUnit>()

        if(dbstatus){

            val queryResult = sqlStatement.executeQuery("SELECT * FROM ${tblName}")

            while (queryResult.next()){
              //  val name = queryResult.getString("_name").toString()
                val tempName = queryResult.getBytes("_name")

                val charset = Charsets.UTF_8

                val name = tempName.toString(charset)

                val value = queryResult.getFloat("_value")
                val data = DataUnit(value, name)

       arrayOfResults.add(data)

            }


        }


        return arrayOfResults
    }



}