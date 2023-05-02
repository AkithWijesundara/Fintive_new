package com.example.bottomnav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HashGoalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var ret =  inflater.inflate(R.layout.fragment_hash_goal, container, false)

        //add button
        var addGoals = ret.findViewById<Button>(R.id.add_btn)
        addGoals.setOnClickListener {
            val intent = Intent(context, AddGoal::class.java)
            requireContext().startActivity(intent)
        }

        return ret
    }

}