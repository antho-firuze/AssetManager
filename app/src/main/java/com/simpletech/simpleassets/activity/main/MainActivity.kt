package com.simpletech.simpleassets.activity.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.simpletech.simpleassets.R
import com.simpletech.simpleassets.activity.AboutActivity
import com.simpletech.simpleassets.activity.FirstRunActivity
import com.simpletech.simpleassets.activity.SettingActivity
import com.simpletech.simpleassets.fragment.ItemListFragment
import com.simpletech.simpleassets.helper.RealmDBHelper
import com.simpletech.simpleassets.helper.session
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.search_bar
import kotlinx.android.synthetic.main.include_card_view_search_bar.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val fragment1: Fragment = ItemListFragment()
    var activeFragment: Fragment = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RealmDBHelper().deleteItemsAll()
        RealmDBHelper().initDatabase()

        initComponent()
        initNavigationMenu()

        if (session!!.isFirstLaunch) {
            startActivity(Intent(this, FirstRunActivity::class.java))
        }
    }

    private fun initNavigationMenu() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_rate_app -> {
                    try {
                        var playstoreuri1: Uri = Uri.parse("market://details?id=$packageName")
                        //or you can add
                        var playstoreIntent1: Intent = Intent(Intent.ACTION_VIEW, playstoreuri1)
                        startActivity(playstoreIntent1)
                        //it genrate exception when devices do not have playstore
                    }catch (exp:Exception){
                        var playstoreuri2: Uri = Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                        var playstoreIntent2: Intent = Intent(Intent.ACTION_VIEW, playstoreuri2)
                        startActivity(playstoreIntent2)
                    }
                }
                R.id.nav_share -> {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=$packageName")
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Simple Asset Apps")
                    startActivity(Intent.createChooser(intent, "Share via"))
                }
                R.id.nav_settings -> {
                    val intent = Intent(this, SettingActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_about -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            false
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initComponent() {
        supportFragmentManager.beginTransaction().add(R.id.container, fragment1, "1").commit()
//        supportFragmentManager.beginTransaction().add(R.id.container, fragment2, "2").hide(fragmentFaq).commit()

        bt_menu.setOnClickListener { drawer_layout.openDrawer(GravityCompat.START) }

        nested_scroll_view.setOnScrollChangeListener { v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            Log.d(TAG, "scrollY: $scrollY")
            if (scrollY < oldScrollY) { // up
                animateNavigation(false)
                animateSearchBar(false)
            }
            if (scrollY > oldScrollY) { // down
                animateNavigation(true)
                animateSearchBar(true)
            }
        }

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_movie -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment1).commit()
                    activeFragment = fragment1
                    search_text.setText(item.title)
                    navigation.setBackgroundColor(resources.getColor(R.color.blue_grey_700))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_music -> {
                    search_text.setText(item.title)
                    navigation.setBackgroundColor(resources.getColor(R.color.pink_800))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_books -> {
                    search_text.setText(item.title)
                    navigation.setBackgroundColor(resources.getColor(R.color.grey_700))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_newsstand -> {
                    search_text.setText(item.title)
                    navigation.setBackgroundColor(resources.getColor(R.color.teal_800))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    private var isNavigationHide = false

    private fun animateNavigation(hide: Boolean) {
        if (isNavigationHide && hide || !isNavigationHide && !hide) return
        isNavigationHide = hide
        val moveY = if (hide) 2 * navigation.height else 0
        navigation.animate().translationY(moveY.toFloat()).setStartDelay(100).setDuration(300)
            .start()
        val moveYY = if (hide) navigation.height else 0
        fab_add.animate().translationY(moveYY.toFloat()).setStartDelay(100).setDuration(300)
            .start()
    }

    private var isSearchBarHide = false

    private fun animateSearchBar(hide: Boolean) {
        if (isSearchBarHide && hide || !isSearchBarHide && !hide) return
        isSearchBarHide = hide
        val moveY = if (hide) -(2 * search_bar.height) else 0
        search_bar.animate().translationY(moveY.toFloat()).setStartDelay(100).setDuration(300)
            .start()
    }

}
