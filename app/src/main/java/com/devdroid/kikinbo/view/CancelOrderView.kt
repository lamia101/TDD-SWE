package com.devdroid.kikinbo.view

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.viewmodel.CancelOrderViewModel
import com.google.android.material.textfield.TextInputEditText

class CancelOrderView : AppCompatActivity() {

    private lateinit var orderId: TextInputEditText
    private lateinit var btnSubmitId: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cancel_order_view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        orderId = findViewById(R.id.tiOrderid)
        btnSubmitId = findViewById(R.id.btnSubminid)

        btnSubmitId.setOnClickListener {
            val orderId1 = orderId.text.toString().trim() // Ensure you're getting the latest text input

            if (orderId1.isNotEmpty()) {
                // Call cancelOrder with the entered order ID
                CancelOrderViewModel().cancelOrder(orderId1) { message ->
                    // Display the result of the cancellation attempt
                    Toast.makeText(this@CancelOrderView, message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a valid Order ID", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
