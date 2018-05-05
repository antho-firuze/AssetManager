package com.firuze.ahmad.assetmanager

import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by antho.firuze@gmail.com on 2018-04-26.
 */
class Demo : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {

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
    }
}