package com.intek.updateapk

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity()
{

    public var actyvityClose = false

   override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val PermTest = ContextCompat . checkSelfPermission ( this , Manifest.permission.WRITE_EXTERNAL_STORAGE )
        if ((ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE ) == -1)
            ||(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE ) == -1)
            ||(ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET ) == -1))
        {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.INTERNET),0)
        }
        val conn = ConnectFtp()
        var vers:String
        try
        {
            vers = intent.extras!!.getString("tsdVers")!!
            vers = vers.toString().trim()
        }
        catch (e: Exception)
        {
            vers = "5.04"
        }
        conn.execute(vers)

        while (!conn.get())
        {
            TimeUnit.SECONDS.sleep(5);
        }

        val intent = Intent(this, InstActivity::class.java)
        startActivityForResult(intent, 10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // если пришло ОК
        if (resultCode == 10)
        {
            val intent = Intent()
            intent.component = ComponentName("com.intek.wpma", "com.intek.wpma.MainActivity")
            startActivity(intent)
            this.finish()
        }
        else
        {
            val intent = Intent()
            intent.component = ComponentName("com.intek.wpma", "com.intek.wpma.MainActivity")
            startActivity(intent)
            this.finish()
        }

    }
}
class ConnectFtp :AsyncTask<String,Any,Boolean> ()
{
    override fun doInBackground(vararg params: String): Boolean
    {
        var yourFilePath: String = Environment.getExternalStorageDirectory().path
        yourFilePath += "/Download/wpm.apk"
       // yourFilePath = "/storage/ipsm/android/download/wpm.apk"
        // var fInput = FileOutputStream(yourFilePath)
        val fs: String = "wpma.apk"

        var mFTPClient = FTPClient()
       // connecting to the host
        mFTPClient.autodetectUTF8 = true
        mFTPClient.connect("192.168.8.59",21)
        mFTPClient.login("robot_zyxel","AC22fv#\$")
        mFTPClient.setFileType(FTP.BINARY_FILE_TYPE)
        mFTPClient.enterLocalPassiveMode()
        var pathname = "/claim/wpm/" + params[0] + "/"
        mFTPClient.changeWorkingDirectory(pathname)
        val ftpFiles: Array<FTPFile> = mFTPClient.listFiles(pathname)
        val length = ftpFiles.size
        var fileList = arrayOfNulls<String>(length)
        for (i in 0 until length) {
            val name: String = ftpFiles[i].getName()
            val isFile: Boolean = ftpFiles[i].isFile()
            if (name == fs)
            {
                var desFileStream = FileOutputStream(yourFilePath)
                mFTPClient.retrieveFile(name, desFileStream)
                mFTPClient.getReplyCode()
                desFileStream.close()
            }
        }
        mFTPClient.logout()
        mFTPClient.disconnect()
        return true
    }
}