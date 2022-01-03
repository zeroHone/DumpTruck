package data

import android.util.Log
import java.lang.Exception
import java.sql.DriverManager
import java.sql.Statement

class ExternalDataBase(dataBaseName : String) {
    val ipAddress = SettingsData.IP
    val portAddress = SettingsData.PORT
    val dbName = dataBaseName
    val password = SettingsData.PASSWORD
    val username = SettingsData.USERNAME
    var dbstatus = "-"


    lateinit var sqlStatement : Statement
    fun connectToDB():Boolean{

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
            val connect = DriverManager
                .getConnection(
                    "jdbc:mysql://172.17.180.100:3306/test_db",
                    "tamsidb_admin",
                    "1234!@#$")


            sqlStatement = connect.createStatement()
            dbstatus ="OK"

            return true
        }catch (exp : Exception){
            dbstatus="ERROR"

            return false
        }


    }
    fun fetchData():ArrayList<DataBase>{
        val arrayOfResults = ArrayList<DataBase>()

        if(connectToDB()){
            val queryResult = sqlStatement.executeQuery("SELECT * FROM test_tbl")

            while (queryResult.next()){
              //  val name = queryResult.getString("_name").toString()
                val tempName = queryResult.getBytes("_name")

                val charset = Charsets.UTF_8

                val name = tempName.toString(charset)

                val value = queryResult.getFloat("_value")
                val data = DataBase(value, name)

       arrayOfResults.add(data)

            }


        }


        return arrayOfResults
    }



}