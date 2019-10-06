package com.simpletech.simpleassets.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simpletech.simpleassets.R
import kotlinx.android.synthetic.main.activity_store.*

class StoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Store"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
