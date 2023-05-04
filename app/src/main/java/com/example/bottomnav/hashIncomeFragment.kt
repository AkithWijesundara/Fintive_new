package com.example.bottomnav

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


lateinit var pieChart: PieChart
class hashIncomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val abc =  inflater.inflate(R.layout.fragment_hash_income, container, false)

        pieChart = abc.findViewById(R.id.pieChartGraph2)
        val list: ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(200f, "Salary"))
        list.add(PieEntry(100f, "Shop"))
        list.add(PieEntry(60f, "Bonus"))



        val pieDataSet = PieDataSet(list, "List")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextSize=25f
        pieDataSet.valueTextColor= Color.BLACK

        val pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieChart.description.text = "Pie Chart"
        pieChart.centerText = "List"
        pieChart.animateY(2000)

        return abc
    }


}