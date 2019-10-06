package com.simpletech.simpleassets.activity.item

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.simpletech.simpleassets.R
import com.simpletech.simpleassets.utils.formatDate
import kotlinx.android.synthetic.main.activity_add_item.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent
import android.app.Activity
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.simpletech.simpleassets.activity.SimpleScannerActivity


class AddItemActivity : AppCompatActivity() {

    private var provinceList: MutableList<String>? = null
    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Adding Item"

        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)

        initSpinner()
        initBarcodeScanner()
        initDatePicker()

    }

    private fun initDatePicker() {
        txt_manufacture_date.setOnClickListener {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    setDate(1, date.formatDate())
                }
                lifecycleOwner(this@AddItemActivity)
            }
        }

        txt_purchase_date.setOnClickListener {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    setDate(2, date.formatDate())
                }
                lifecycleOwner(this@AddItemActivity)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                txt_serialnumber.setText(data?.getStringExtra("result"))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initBarcodeScanner() {
        btn_serial_number.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA), 1
                )
            } else {
                val scanIntent = Intent(this, SimpleScannerActivity::class.java)
                startActivityForResult(scanIntent, 1)
            }
        }
    }

    private fun setDate(type: Int, formatDate: String) {
        when (type) {
            1 -> {
                txt_manufacture_date.setText(formatDate)
            }
            2 -> {
                txt_purchase_date.setText(formatDate)
            }
        }
    }

    private fun initSpinner() {
        provinceList = ArrayList()

        provinceList!!.add("Kampong Thom")
        provinceList!!.add("Kampong Cham")
        provinceList!!.add("Kampong Chhnang")
        provinceList!!.add("Phnom Penh")
        provinceList!!.add("Kandal")
        provinceList!!.add("Kampot")

        spinner1.item = provinceList

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@AddItemActivity, provinceList!![position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
