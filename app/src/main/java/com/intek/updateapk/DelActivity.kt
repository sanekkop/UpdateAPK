package com.intek.updateapk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class DelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val packageURIDel: Uri = Uri.parse("package:com.intek.wpma")
        val uninstallIntent = Intent(Intent.ACTION_DELETE, packageURIDel)
        startActivityForResult(uninstallIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // если пришло ОК
        if (resultCode == 1) {
            val intent = Intent(this, InstActivity::class.java)
            startActivity(intent)
            this.finish()
        } else {
            val intent = Intent(this, InstActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}
