package com.simpletech.simpleassets.fragment


import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.simpletech.simpleassets.R
import com.simpletech.simpleassets.helper.realm
import com.simpletech.simpleassets.model.Items
import com.simpletech.simpleassets.utils.Tools
import com.simpletech.simpleassets.widget.SpacingItemDecoration
import io.realm.Realm
import io.realm.kotlin.delete
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_item_list.*
import kotlinx.android.synthetic.main.item_grid_image_two_line.view.*
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class ItemListFragment : Fragment() {

    private val TAG = "ItemListFragment"
    lateinit var parent_view: View
    val datas: ArrayList<Items> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "message: onActivityCreated")
        parent_view = activity!!.findViewById(android.R.id.content)
        loadData()
    }

    private fun loadData() {
//        val progressDialog =
//            indeterminateProgressDialog(R.string.on_progress).apply { setCancelable(false) }
//        progressDialog.show()

        var rows = realm?.where<Items>()?.findAll()
        if (rows!!.isEmpty()) {
            Snackbar.make(parent_view, R.string.data_empty, Snackbar.LENGTH_SHORT).show()
//            recycleView.visibility = View.GONE
        } else {
            datas.addAll(Realm.getDefaultInstance().copyFromRealm(rows))

//            recycleView.visibility = View.VISIBLE
//            recycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycleView.layoutManager = GridLayoutManager(context, 2)
            recycleView.addItemDecoration(SpacingItemDecoration(2, Tools().dpToPx((activity as AppCompatActivity), 4),true))
            recycleView.setHasFixedSize(true)

            val mAdapter = ListAdapter((activity as AppCompatActivity), datas)
            recycleView.adapter = mAdapter

            mAdapter.setOnItemClickListener(object : ListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, obj: Items, position: Int) {
                    Snackbar.make(parent_view, obj.name + " clicked", Snackbar.LENGTH_SHORT).show()
                }
            })
        }

//        progressDialog.dismiss()
    }

    private fun insertItem(s: String, s1: String) {
        realm?.executeTransaction {
            val obj = realm!!.createObject(Items::class.java)
            obj.name = s
            obj.model = s1
        }
    }


    class ListAdapter(ctx: Context, val rows: ArrayList<Items>) :
        RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

        var mOnItemClickListener: OnItemClickListener? = null

        interface OnItemClickListener {
            fun onItemClick(view: View, obj: Items, position: Int)
        }

        fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
            this.mOnItemClickListener = mItemClickListener
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
            return ListViewHolder(
                LayoutInflater.from(p0.context).inflate(
                    R.layout.item_grid_image_two_line,
                    p0,
                    false
                )
            )
        }

        override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
            val r = rows[p1]
            p0.itemView.apply {

                name.text = r.name
                brief.text = r.model

                lyt_parent.setOnClickListener { mOnItemClickListener?.onItemClick(this, r, p1) }
            }
        }

        override fun getItemCount(): Int = rows.size
        class ListViewHolder(v: View) : RecyclerView.ViewHolder(v)

    }

}
