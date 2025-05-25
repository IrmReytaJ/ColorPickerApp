package com.example.colorpickerapp

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ColorListActivity : AppCompatActivity() {

    private lateinit var db: ColorDatabase
    private lateinit var listContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_list)

        listContainer = findViewById(R.id.colorListLayout)
        db = ColorDatabase.getDatabase(this)

        lifecycleScope.launch {
            val colors = db.colorDao().getAllColors()
            colors.forEach { color ->
                val item = TextView(this@ColorListActivity).apply {
                    text = "${color.name} - ${color.hex}"
                    textSize = 18f
                    setPadding(16, 16, 16, 16)
                    setBackgroundColor(Color.rgb(color.red, color.green, color.blue))
                    setTextColor(Color.WHITE)
                }
                listContainer.addView(item)
            }
        }
    }
}
