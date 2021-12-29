package database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import data.SettingsData
import java.lang.StringBuilder

class DataBaseHandler(context: Context ) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    companion object{
        val DATABASE_NAME = "maindatabase.db"
        val DATABASE_VERSION =1
        val DATABASE_TABLE="maintable"

    }


object DataColumns : BaseColumns{


        val COLUMN_IP = "ipaddress"
        val COLUMN_PORT = "port"
        val COLUMN_TRUCK = "truck"
        val COLUMN_USERNAME="username"
        val COLUMN_PASSWORD="password"
        val COLUMN_ROW="row"


    }

override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = "CREATE TABLE $DATABASE_TABLE ("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,"+
            "${DataColumns.COLUMN_IP} TEXT,"+
            "${DataColumns.COLUMN_PORT} TEXT,"+
                "${DataColumns.COLUMN_TRUCK} TEXT,"+
                "${DataColumns.COLUMN_USERNAME} TEXT,"+
                "${DataColumns.COLUMN_PASSWORD} TEXT,"+
        "${DataColumns.COLUMN_ROW} TEXT)"

    p0!!.execSQL(CREATE_TABLE_QUERY)
    val currentSettings = ContentValues()
    currentSettings.put(DataColumns.COLUMN_IP, SettingsData.IP)
    currentSettings.put(DataColumns.COLUMN_PORT, SettingsData.PORT)
    currentSettings.put(DataColumns.COLUMN_USERNAME, SettingsData.USERNAME)
    currentSettings.put(DataColumns.COLUMN_PASSWORD, SettingsData.PASSWORD)
    currentSettings.put(DataColumns.COLUMN_TRUCK, SettingsData.TRUCK)
    currentSettings.put(DataColumns.COLUMN_ROW, SettingsData.ROW)

    val success = p0!!.insert(DATABASE_TABLE,null,currentSettings)


    }

override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

fun checkCompleteSettings():Boolean{
val str = StringBuilder()

    val db = readableDatabase

   val projection = arrayOf(
       BaseColumns._ID,
       DataColumns.COLUMN_IP,
       DataColumns.COLUMN_PORT,
       DataColumns.COLUMN_PASSWORD,
       DataColumns.COLUMN_USERNAME,
       DataColumns.COLUMN_TRUCK,
       DataColumns.COLUMN_ROW)

// Filter results WHERE "title" = 'My Title'
    val selection = "${DataColumns.COLUMN_ROW} = ?"
    val selectionArgs = arrayOf("1")

    val cursor = db.query(
        DATABASE_TABLE,   // The table to query
        projection,             // The array of columns to return (pass null to get all)
        selection,              // The columns for the WHERE clause
        selectionArgs,          // The values for the WHERE clause
        null,                   // don't group the rows
        null,                   // don't filter by row groups
        null               // The sort order
    )

if(cursor.moveToFirst()){do {

    SettingsData.IP = cursor.getString(cursor.getColumnIndex(DataColumns.COLUMN_IP) as Int)
    SettingsData.PORT = cursor.getString(cursor.getColumnIndex(DataColumns.COLUMN_PORT) as Int)
    SettingsData.USERNAME = cursor.getString(cursor.getColumnIndex(DataColumns.COLUMN_USERNAME) as Int)
    SettingsData.PASSWORD = cursor.getString(cursor.getColumnIndex(DataColumns.COLUMN_PASSWORD) as Int)
    SettingsData.TRUCK = cursor.getString(cursor.getColumnIndex(DataColumns.COLUMN_TRUCK) as Int)
    SettingsData.ROW = cursor.getString(cursor.getColumnIndex(DataColumns.COLUMN_ROW) as Int)

    str.append("***IP = ${SettingsData.IP}\n")
    str.append("**ROW = ${SettingsData.PORT}\n")
    Log.i("settingHHF",str.toString())

    }while (cursor.moveToNext())
}
db.close()
    return true
}

fun insertSettings() : Long{
    val db = writableDatabase
val currentSettings = ContentValues()
    currentSettings.put(DataColumns.COLUMN_IP, SettingsData.IP)
    currentSettings.put(DataColumns.COLUMN_PORT, SettingsData.PORT)
    currentSettings.put(DataColumns.COLUMN_USERNAME, SettingsData.USERNAME)
    currentSettings.put(DataColumns.COLUMN_PASSWORD, SettingsData.PASSWORD)
    currentSettings.put(DataColumns.COLUMN_TRUCK, SettingsData.TRUCK)
    currentSettings.put(DataColumns.COLUMN_ROW, SettingsData.ROW)

    val success = db.insert(DATABASE_TABLE,null,currentSettings)

db.close()
return success
}
fun refreshSettings() : Int{


        val db = writableDatabase

Log.i("settingHHF","refresh")

        val values = ContentValues().apply {
            put(DataColumns.COLUMN_IP, SettingsData.IP)
            put(DataColumns.COLUMN_PORT, SettingsData.PORT)
            put(DataColumns.COLUMN_USERNAME, SettingsData.USERNAME)
            put(DataColumns.COLUMN_PASSWORD, SettingsData.PASSWORD)
            put(DataColumns.COLUMN_TRUCK, SettingsData.TRUCK)
            put(DataColumns.COLUMN_ROW, SettingsData.ROW)
        }


        val selection = "${DataColumns.COLUMN_ROW} LIKE ?"
        val selectionArgs = arrayOf(SettingsData.ROW)
        val count = db.update(
            DATABASE_TABLE,
            values,
            selection,
            selectionArgs)
        db.close()

        Log.i("settingHHF","count ==  ${count.toString()}")
return count
    }

}