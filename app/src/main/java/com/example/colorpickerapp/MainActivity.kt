package com.example.colorpickerapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var preview: View
    private lateinit var hexCode: TextView
    private lateinit var nameInput: EditText
    private var red = 0
    private var green = 0
    private var blue = 0
    private lateinit var db: ColorDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = ColorDatabase.getDatabase(this)

        val seekRed = findViewById<SeekBar>(R.id.seekRed)
        val seekGreen = findViewById<SeekBar>(R.id.seekGreen)
        val seekBlue = findViewById<SeekBar>(R.id.seekBlue)
        preview = findViewById(R.id.previewColor)
        hexCode = findViewById(R.id.hexCode)
        nameInput = findViewById(R.id.inputName)

        val listener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, p: Int, fromUser: Boolean) {
                red = seekRed.progress
                green = seekGreen.progress
                blue = seekBlue.progress
                updateColor()
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        }

        seekRed.setOnSeekBarChangeListener(listener)
        seekGreen.setOnSeekBarChangeListener(listener)
        seekBlue.setOnSeekBarChangeListener(listener)

        findViewById<Button>(R.id.btnAddColor).setOnClickListener {
            val hex = String.format("#%02X%02X%02X", red, green, blue)
            val name = nameInput.text.toString()

            if (name.isBlank()) {
                Toast.makeText(this, "Nama warna wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val color = ColorEntity(
                hex = hex,
                name = name,
                red = red,
                green = green,
                blue = blue
            )

            lifecycleScope.launch {
                db.colorDao().insertColor(color)
                Toast.makeText(this@MainActivity, "Warna berhasil disimpan!", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnViewColors).setOnClickListener {
            startActivity(Intent(this, ColorListActivity::class.java))
        }
    }

    private fun updateColor() {
        val hex = String.format("#%02X%02X%02X", red, green, blue)
        preview.setBackgroundColor(Color.rgb(red, green, blue))
        hexCode.text = hex
    }
}
