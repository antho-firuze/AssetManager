package com.simpletech.simpleassets.fragment

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.simpletech.simpleassets.R
import com.simpletech.simpleassets.helper.realm
import com.simpletech.simpleassets.model.Category
import com.simpletech.simpleassets.utils.Tools
import com.simpletech.simpleassets.widget.SpacingItemDecoration
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_category_list.*
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryListFragment : Fragment() {

    private val TAG = "CategoryFragment"
    lateinit var parent_view: View
    private val datas: ArrayList<Category> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false)
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

        var rows = realm?.where<Category>()?.findAll()
        if (rows!!.isEmpty()) {
            Snackbar.make(parent_view, R.string.data_empty, Snackbar.LENGTH_SHORT).show()
//            recycleView.visibility = View.GONE
        } else {
            datas.addAll(Realm.getDefaultInstance().copyFromRealm(rows))

//            recycleView.visibility = View.VISIBLE
            recycleView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                context,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )
//            recycleView.layoutManager = GridLayoutManager(context, 2)
//            recycleView.addItemDecoration(SpacingItemDecoration(2, Tools().dpToPx((activity as AppCompatActivity), 4),true))
//            recycleView.setHasFixedSize(true)

            val mAdapter = ListAdapter((activity as AppCompatActivity), datas)
            recycleView.adapter = mAdapter

            mAdapter.setOnItemClickListener(object : ListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, obj: Category, position: Int) {
                    Snackbar.make(parent_view, obj.name + " clicked", Snackbar.LENGTH_SHORT).show()
                }
            })
        }

//        progressDialog.dismiss()
    }

    class ListAdapter(ctx: Context, val rows: ArrayList<Category>) :
        androidx.recyclerview.widget.RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

        var mOnItemClickListener: OnItemClickListener? = null

        interface OnItemClickListener {
            fun onItemClick(view: View, obj: Category, position: Int)
        }

        fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
            this.mOnItemClickListener = mItemClickListener
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
            return ListViewHolder(
                LayoutInflater.from(p0.context).inflate(
                    R.layout.item_category,
                    p0,
                    false
                )
            )
        }

        override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
            val r = rows[p1]
            p0.itemView.apply {

                title.text = r.name
//                brief.text = r.model

                lyt_parent.setOnClickListener { mOnItemClickListener?.onItemClick(this, r, p1) }
            }
        }

        override fun getItemCount(): Int = rows.size
        class ListViewHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v)

    }
}
