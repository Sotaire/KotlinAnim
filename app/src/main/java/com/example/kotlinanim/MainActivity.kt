package com.example.kotlinanim

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.animated_image);
        animate(imageView,15f,500)
        imageView.setOnClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun animate(imageView: ImageView, degree: Float, duration: Long){
        imageView.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            val x = motionEvent.x
            val y = motionEvent.y
            when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (x >= imageView.width / 3 * 2) {  // нажатие на правую часть view
                            imageView.animate().withLayer()
                                .rotationY(degree)
                                .setDuration(duration).start()
                        }
                        if (x <= imageView.width / 3) {  // нажатие на левую часть view
                            imageView.animate().withLayer()
                                .rotationY(-degree)
                                .setDuration(duration).start()
                        }
                        if (y <= imageView.height / 3) {  // нажатие на верхнюю часть view
                            imageView.animate().withLayer()
                                .rotationX(degree)
                                .setDuration(duration).start()
                        }
                        if (y >= imageView.height / 3 * 2) {  // нажатие на нижнюю часть view
                            imageView.animate().withLayer()
                                .rotationX(-degree)
                                .setDuration(duration).start()
                        }
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        imageView.animate()
                            .withLayer() // возвращаем к исходному положению , когда пользователь отпускает view
                            .rotationY(0f)
                            .rotationX(0f)
                            .setDuration(1800).start()
                    }
                }
            return@OnTouchListener false
        })
    }

}