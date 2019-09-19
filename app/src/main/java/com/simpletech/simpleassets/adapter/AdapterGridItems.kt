package com.simpletech.simpleassets.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simpletech.simpleassets.R
import com.simpletech.simpleassets.model.Items
import kotlinx.android.synthetic.main.item_grid_image_two_line.view.*

/**
 * Created by antho.firuze@gmail.com on 10/09/2019.
 */
class AdapterGridItems(val ctx: Context, val rows: ArrayList<Items>) : RecyclerView.Adapter<AdapterGridItems.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_grid_image_two_line, p0, false))
    }

    override fun getItemCount(): Int = rows.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val r = rows[p1]
        p0.itemView.apply {

            name.text = r.name
            brief.text = r.model

            lyt_parent.setOnClickListener { mOnItemClickListener?.onItemClick(this, r, p1) }
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    // Additional process -------------------------------------------------------------------------
    var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Items, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }


}