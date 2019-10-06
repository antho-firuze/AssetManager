package com.simpletech.simpleassets.activity

import android.app.Activity
import android.content.Intent
import android.media.RingtoneManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.simpletech.simpleassets.R
import com.simpletech.simpleassets.activity.item.AddItemActivity
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlinx.android.synthetic.main.activity_simple_scanner.*
import kotlinx.android.synthetic.main.activity_simple_scanner.toolbar
import me.dm7.barcodescanner.zxing.ZXingScannerView

/**
 * Created by antho.firuze@gmail.com on 4/10/2019.
 */
class SimpleScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val FLASH_STATE = "FLASH_STATE"
    private var mScannerView: ZXingScannerView? = null
    private var mFlash: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scaling_scanner)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Simple Scanner"

        mScannerView = ZXingScannerView(this)
        content_frame.addView(mScannerView)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(FLASH_STATE, mFlash)
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this)
        // You can optionally set aspect ratio tolerance level
        // that is used in calculating the optimal Camera preview size
        mScannerView?.setAspectTolerance(0.2f)
        mScannerView?.startCamera()
        mScannerView?.flash = mFlash
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        try {
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()
        } catch (e: Exception) {
        }

        val intent = Intent()
        intent.putExtra("result", rawResult?.text)
        setResult(Activity.RESULT_OK, intent)

//        Toast.makeText(
//            this, "Contents = " + (rawResult?.getText() ?: "") +
//                    ", Format = " + rawResult?.getBarcodeFormat().toString(), Toast.LENGTH_SHORT
//        ).show()
        finish()

        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
//        val handler = Handler()
//        handler.postDelayed(
//            Runnable { mScannerView?.resumeCameraPreview(this@SimpleScannerActivity) },
////            Runnable { finish() },
//            2000
//        )
    }

    fun toggleFlash(v: View) {
        mFlash = !mFlash
        mScannerView?.flash = mFlash
    }

}