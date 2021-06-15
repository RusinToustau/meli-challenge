package com.meli.challenge.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.meli.challenge.R

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun AppCompatActivity.setToolbar(toolbar: Toolbar?) {
    toolbar?.setNavigationIcon(R.drawable.ic_arrow_back)

    setSupportActionBar(toolbar)
    supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        setDisplayShowHomeEnabled(true)
        setDisplayShowTitleEnabled(false)
    }

    toolbar?.setNavigationOnClickListener {
        onBackPressed()
    }
}