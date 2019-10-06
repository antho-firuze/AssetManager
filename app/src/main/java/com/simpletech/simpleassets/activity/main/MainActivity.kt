package com.simpletech.simpleassets.activity.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.simpletech.simpleassets.R
import com.simpletech.simpleassets.activity.AboutActivity
import com.simpletech.simpleassets.activity.FirstRunActivity
import com.simpletech.simpleassets.activity.SettingActivity
import com.simpletech.simpleassets.activity.StoreActivity
import com.simpletech.simpleassets.activity.item.AddItemActivity
import com.simpletech.simpleassets.fragment.CategoryListFragment
import com.simpletech.simpleassets.fragment.ItemListFragment
import com.simpletech.simpleassets.helper.RealmDBHelper
import com.simpletech.simpleassets.helper.session
import com.simpletech.simpleassets.utils.launchActivity
import com.simpletech.simpleassets.utils.rateApp
import com.simpletech.simpleassets.utils.sharedApp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.search_bar
import kotlinx.android.synthetic.main.include_card_view_search_bar.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val fragment1: Fragment = ItemListFragment()
    private val fragment2: Fragment = CategoryListFragment()
    var activeFragment: Fragment = fragment1
    var activePage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initNavigationMenu()
        initFloatingButton()

//        session!!.isFirstLaunch = true
        if (session!!.isFirstLaunch) {
            RealmDBHelper().initDatabaseFirstLaunch()
            startActivity(Intent(this, FirstRunActivity::class.java))

            session!!.isFirstLaunch = false
        }
    }

    private fun initFloatingButton() {
        fab_add.setOnClickListener {
            when (activePage) {
                1 -> {
//                    Snackbar.make(drawer_layout, "Add Items", Snackbar.LENGTH_SHORT).show()
                    launchActivity(AddItemActivity::class.java)
                }
                2 -> {
                    Snackbar.make(drawer_layout, "Add Category", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initNavigationMenu() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled = true
        toggle.isDrawerSlideAnimationEnabled = true
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_rate_app -> {
                    rateApp()
                }
                R.id.nav_share -> {
                    sharedApp()
                }
                R.id.nav_store -> {
                    launchActivity(StoreActivity::class.java)
                }
                R.id.nav_settings -> {
                    launchActivity(SettingActivity::class.java)
                }
                R.id.nav_about -> {
                    launchActivity(AboutActivity::class.java)
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
        supportFragmentManager.beginTransaction().add(R.id.container, fragment2, "2").hide(fragment2).commit()

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
                R.id.navigation_asset -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment1).commit()
                    activeFragment = fragment1
                    search_text.text = item.title
                    navigation.setBackgroundColor(resources.getColor(R.color.blue_grey_700))
                    activePage = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_category -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit()
                    activeFragment = fragment2
                    search_text.text = item.title
                    navigation.setBackgroundColor(resources.getColor(R.color.pink_800))
                    activePage = 2
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_summary -> {
                    search_text.text = item.title
                    navigation.setBackgroundColor(resources.getColor(R.color.grey_700))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_report -> {
                    search_text.text = item.title
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
