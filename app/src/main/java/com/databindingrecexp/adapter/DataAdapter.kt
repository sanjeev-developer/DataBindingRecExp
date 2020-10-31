package com.databindingrecexp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.databindingexp.model.ApiResponse
import com.databindingrecexp.R
import com.squareup.picasso.Picasso


class DataAdapter(private val myDataset: ApiResponse) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.MyViewHolder {
        // create a new view
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rec_layout, parent, false)
        return MyViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.setText(myDataset.jsondata.get(position).name)
     //   holder.score.setText(myDataset.get(position).score.toString())

        Picasso.get()
            .load(myDataset.jsondata.get(position).image)
            .into(holder.image)

        //to load image with custom size
        //        .resize(100, 100)
        //            .centerCrop()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.jsondata.size

    class MyViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

        //intilazing the ui component of adapter layout
        var name: TextView
        var image: ImageView


        init {

            image = itemLayoutView.findViewById(R.id.img_view)
            name = itemLayoutView.findViewById(R.id.txt_name)

        }
    }
}