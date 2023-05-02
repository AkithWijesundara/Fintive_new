package com.example.bottomnav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class income : Fragment(R.layout.fragment_income) {

    private lateinit var adapterInc: incomeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var incomeArrayList: ArrayList<IncomeData>
    private lateinit var dbRef:DatabaseReference

    lateinit var names: Array<String>
    lateinit var dates:Array<String>
    lateinit var costs:Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager= LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.recycle)
        recyclerView.layoutManager=layoutManager
        recyclerView.hasFixedSize()

        incomeArrayList = arrayListOf<IncomeData>()
        dataInitialize()



            val addinc=view.findViewById<Button>(R.id.button)

            addinc.setOnClickListener {
                requireActivity().run{
                    startActivity(Intent(this,addIncome::class.java))
                    finish()
                }
            }


    }


    private fun dataInitialize(){


        dbRef=FirebaseDatabase.getInstance().getReference("Incomes")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                incomeArrayList.clear()
                if(snapshot.exists()){
                    for(incSnap in snapshot.children){
                        val incData=incSnap.getValue(IncomeData::class.java)
                        incomeArrayList.add(incData!!)
                    }
                    adapterInc=incomeAdapter(incomeArrayList)
                    recyclerView.adapter=adapterInc
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

//        names= arrayOf(
//            "Salary",
//            "Bonus",
//            "Dividends",
//            "Rental",
//            "Allowances",
//            "Uber",
//            "FD Intrest"
//        )
//
//        dates= arrayOf(
//            "14/3/2022",
//            "16/3/2022",
//            "15/3/2022",
//            "20/3/2022",
//            "14/3/2022",
//            "14/3/2022",
//            "16/3/2022"
//        )
//
//        costs= arrayOf(
//            "Rs.15,000",
//            "Rs.5,000",
//            "Rs.7,000",
//            "Rs.10,000",
//            "Rs.15,000",
//            "Rs.15,000",
//            "Rs.5,000"
//        )
//
//        for(i in names.indices){
//            val data=incomeData(names[i],dates[i],costs[i])
//            incomeArrayList.add(data)
//        }

    }

}