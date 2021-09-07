package com.intek.updateapk

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import java.io.File

class InstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inst)
        var yourFilePath: String = Environment.getExternalStorageDirectory().path
        yourFilePath += "/Download/wpm.apk"
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = Uri.fromFile(File(yourFilePath))
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        val IntentInstal= Intent.createChooser(intent, "Установка")

        startActivityForResult(IntentInstal,2);

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // если пришло ОК
        if (resultCode == 1)
        {
            //android.os.Process.killProcess(android.os.Process.myPid());
            this.finish()
        }
        else
        {
            this.finish()
        }

    }
}
