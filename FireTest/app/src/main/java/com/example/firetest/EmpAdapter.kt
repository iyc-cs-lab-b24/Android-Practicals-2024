package com.example.firetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firetest.models.EmployeeModel

class EmpAdapter(private val empList: ArrayList<EmployeeModel>) :
    RecyclerView.Adapter<EmpAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = empList[position]
        holder.tvEmpName.text = currentEmp.empName
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    inner class ViewHolder(itemView: View, clickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val tvEmpName: TextView = itemView.findViewById(R.id.tvEmpName)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(position)
                }
            }
        }

    }

}
