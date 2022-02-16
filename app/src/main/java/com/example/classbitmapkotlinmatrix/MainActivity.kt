package com.example.classbitmapkotlinmatrix

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.leg)
        val bitmap = (drawable as BitmapDrawable).bitmap
        imageView.setImageBitmap(bitmap)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                imageView.setImageBitmap(rotateBitmap(bitmap, progress.toFloat()))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun rotateBitmap(src: Bitmap, degree: Float = 180F): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree)

        return Bitmap.createBitmap(
                src,
                0, 0,
                src.width, src.height,
                matrix,
                true
        )
    }
}