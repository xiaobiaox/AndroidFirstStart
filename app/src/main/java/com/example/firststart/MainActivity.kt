package com.example.firststart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.firststart.ui.theme.FirstStartTheme
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstStartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        Log.d("Permissions", "查看位置权限授权状态")
        // 0授权 -1未授权； 首次安装是-1；第二次打开还是-1
        Log.d("Permissions", ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION).toString())


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 是否要展示提示
            // 解决第一次禁止，重新打开 app 后有提示获取权限的问题，该问题导致小米市场审核不通过
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                val permissionList = ArrayList<String>()
                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
                if (permissionList.isNotEmpty()) {
                    val permissions = permissionList.toArray(arrayOfNulls<String>(permissionList.size))
                    ActivityCompat.requestPermissions(this, permissions, 1)
                }
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onRequestPermissionsResult(requestCode, permissions, grantResults)",
        "androidx.activity.ComponentActivity"
    )
    )
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Log.d("onRequestPermissions", requestCode.toString())
        Log.d("onRequestPermissions", permissions.contentToString())
        Log.d("onRequestPermissions", grantResults.contentToString())
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstStartTheme {
        Greeting("Android")
    }
}