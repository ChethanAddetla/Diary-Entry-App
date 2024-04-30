package com.example.additem

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.additem.model.UserData
import com.example.additem.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar

lateinit var bdate : Button
class MainActivity : AppCompatActivity() {
    private var mYear:Int = 0;
    private var mMonth:Int = 0;
    private var mDay:Int = 0;
    var file = "Date"
    var dfile="mydata"
    lateinit var data: String
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = ArrayList()
        /**set find Id*/
        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)
        /**set Adapter*/
        userAdapter = UserAdapter(this,userList)
        /**setRecycler view Adapter*/
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter

        getUserdata()


        userAdapter.setOnItemClickListener(object :UserAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                intent = Intent(this@MainActivity, filedata::class.java)
                intent.putExtra("title",userList[position].userName)
                intent.putExtra("date",userList[position].userMb)
                intent.putExtra("position",position)
                startActivity(intent)
            }

        })
        /**set Dialog*/
        addsBtn.setOnClickListener { addInfo(it)
    }}
    val dates = arrayOf("29-3-2024", "30-3-2024", "31-3-2024")
    val titles = arrayOf("Greate Day", "Memoires in Ink","Dreams and Doodles")
    private fun getUserdata() {
        for(i in dates.indices){
            val news = UserData(titles[i],dates[i],"hello every one")
            userList.add(news)
        }
        userAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.calendar ->{
                var file1 ="31-3-2024"
                val c = Calendar.getInstance()
                mYear = c[Calendar.YEAR]
                mMonth = c[Calendar.MONTH]
                mDay = c[Calendar.DAY_OF_MONTH]


                val datePickerDialog = DatePickerDialog(this,{
                        view,year,monthofYear,dayOfMonth
                    ->
                    file1 = dayOfMonth.toString() + "-"+ (monthofYear+1)+"-"+year
                    intent = Intent(this@MainActivity, filedata::class.java)
                    intent.putExtra("title","Title : Memoreble Day !")
                    intent.putExtra("date","$file1")
                    intent.putExtra("position",1)
                    startActivity(intent)

                },mYear,mMonth,mDay)
                datePickerDialog.show()



                true
            }

            else ->super.onOptionsItemSelected(item)
        }
    }


    private fun addInfo(view:View) {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item,null)
        /**set view*/
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)
        userNo.setSelection(0)
        bdate = v.findViewById(R.id.date)
        bdate.setOnClickListener(){
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(this,{
                    view,year,monthofYear,dayOfMonth
                ->
                file = dayOfMonth.toString() + "-"+ (monthofYear+1)+"-"+year

            },mYear,mMonth,mDay)
            datePickerDialog.show()
        }
        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Save"){
                dialog,_->
            data = userNo.text.toString()
            try {
                val fOut = openFileOutput(file, MODE_APPEND)
                fOut.write(data.toByteArray())
                fOut.close()
                Toast.makeText(baseContext, "file saved", Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }

            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("$names","$file","$number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }


}
