package com.triarc.android.lib.application

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Devanshu Verma on 29 Apr, 2020
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(applicationContext, "Main Activity", Toast.LENGTH_SHORT).show()
    }
}
