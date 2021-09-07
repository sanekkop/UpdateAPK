package com.intek.updateapk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class DelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_del)
        val packageURIDel: Uri = Uri.parse("package:com.intek.wpma")
        val uninstallIntent: Intent = Intent(Intent.ACTION_DELETE, packageURIDel)
        startActivityForResult(uninstallIntent,1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // если пришло ОК
        if (resultCode == 1)
        {
            //почему-то когда уже удалено, то возвращается 1
            //val intentTest = packageManager.getLaunchIntentForPackage("com.intek.wpma")
           /* try {
                val pm = packageManager
                val pi = pm.getPackageInfo("com.intek.wpma", 0)

                if (pi != null) {
                    // TODO: Обработать
                }
                this.finish()
            }
            catch (e:Exception)
            {*/
                val intent = Intent(this, InstActivity::class.java)
                startActivity(intent)
                this.finish()
            //}


        }
        else {
            //почему-то когда удаляется, то возвращается 0
           // val intentTest = packageManager.getLaunchIntentForPackage("com.intek.wpma")
           /* try {
                val pm = packageManager
                val pi = pm.getPackageInfo("com.intek.wpma", 0)

                if (pi != null) {
                    // TODO: Обработать
                }
                this.finish()
            }
            catch (e:Exception)
            {
               */ val intent = Intent(this, InstActivity::class.java)
                startActivity(intent)
                this.finish()
          //  }
        }
    }
}
