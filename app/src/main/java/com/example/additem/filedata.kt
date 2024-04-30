package com.example.additem

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.additem.model.UserData
import com.example.additem.view.UserAdapter
import java.util.Calendar

class filedata : AppCompatActivity() {
    private var mYear:Int = 0;
    private var mMonth:Int = 0;
    private var mDay:Int = 0;
    var file = "Date"
    var Mtitle ="title"
    lateinit var data: String
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filedata)


        //val title : TextView=findViewById(R.id.gettext)
        val topic : TextView=findViewById(R.id.gettext)
        val title : TextView=findViewById(R.id.title)
        val date : TextView=findViewById(R.id.date)

        val bundle:Bundle?=intent.extras
        val fdate= bundle!!.getString("date")
        val Ttitle= bundle.getString("title")
        var position1:Int = bundle.getInt("position")
//        val position = userList[position1]
//        val popupMenus = PopupMenu(c,v)

//        topic.text = fdate

        title.text = Ttitle
        date.text=fdate
        try {
            val fin = openFileInput(fdate)
            var c: Int
            var temp = ""
            while (fin.read().also { c = it } != -1) {
                temp += c.toChar().toString()
            }
            fin.close()
            topic.text = temp

        } catch (e: Exception) {
            e.printStackTrace()
        }



    }


//    private fun addInfo(view: View) {
//        val inflter = LayoutInflater.from(this)
//        val v = inflter.inflate(R.layout.add_item,null)
//        /**set view*/
//        val userName = v.findViewById<EditText>(R.id.userName)
//        val userNo = v.findViewById<EditText>(R.id.userNo)
//        val edate = v.findViewById<TextView>(R.id.datetext)
//        val bundle:Bundle?=intent.extras
//        val fdate= bundle!!.getString("date")
//        val Ttitle= bundle.getString("title")
//
//
//        edate.text = fdate
//        userName.setText(Ttitle)
//        bdate = v.findViewById(R.id.date)
//        bdate.setOnClickListener(){
//            val c = Calendar.getInstance()
//            mYear = c[Calendar.YEAR]
//            mMonth = c[Calendar.MONTH]
//            mDay = c[Calendar.DAY_OF_MONTH]
//
//
//            val datePickerDialog = DatePickerDialog(this,{
//                    view,year,monthofYear,dayOfMonth
//                ->
//                file = dayOfMonth.toString() + "-"+ (monthofYear+1)+"-"+year
//
//            },mYear,mMonth,mDay)
//            datePickerDialog.show()
//        }
//        val addDialog = AlertDialog.Builder(this)
//
//        addDialog.setView(v)
//        addDialog.setPositiveButton("Save"){
//                dialog,_->
//            data = userNo.text.toString()
//            try {
//                val fOut = openFileOutput(file, MODE_APPEND)
//                fOut.write(data.toByteArray())
//                fOut.close()
//                Toast.makeText(baseContext, "file saved", Toast.LENGTH_SHORT).show()
//            }
//            catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//            val names = userName.text.toString()
//            val number = userNo.text.toString()
//            userList.add(UserData("Title: $names","$file"))
//            userAdapter.notifyDataSetChanged()
//            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
//            dialog.dismiss()
//        }
//        addDialog.setNegativeButton("Cancel"){
//                dialog,_->
//            dialog.dismiss()
//            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
//
//        }
//        addDialog.create()
//        addDialog.show()
//    }
}