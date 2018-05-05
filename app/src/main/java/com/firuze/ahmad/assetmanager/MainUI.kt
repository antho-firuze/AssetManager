package com.firuze.ahmad.assetmanager

import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.verticalLayout

/**
 * Created by antho.firuze@gmail.com on 2018-04-26.
 */
class MainUI : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        //        val url = "http://www.infovesta.com/index2/mutualfund/SH%20/%200"

        verticalLayout{
            imageView(R.drawable.anko_logo).
                    lparams(width= matchParent) {
                        padding = dip(20)
                        margin = dip(15)
                    }
            button("Tap to Like") {
                onClick { toast("Thanks for the love!") }
            }
        }

//        drawerLayout {
//            linearLayout {
//                orientation = LinearLayout.VERTICAL
//                toolbar {
//                    backgroundColor = resources.getColor(R.color.colorPrimary)
//                    elevation = dip(4).toFloat()
//                }.lparams(width = matchParent, height = matchParent)
//                frameLayout {
////                    id = R.id.content
//                }.lparams(width = matchParent, height = matchParent) {
//                    weight = 1f
//                }
//                floatingActionButton {
//                }.lparams {
//                    gravity = Gravity.BOTTOM or Gravity.RIGHT
//                    bottomMargin = dip(20)
//                    rightMargin = dip(20)
//                }
//                bottomNavigationView {
//                    backgroundColor = R.attr.background
//                }.lparams(width = matchParent) {
//                    gravity = Gravity.BOTTOM
//                }
//            }.lparams(width = matchParent, height = matchParent)
//            navigationView {
//            }.lparams(height = matchParent) {
////                gravity = start //not support value
//            }
//        }
    }
}
