package com.devdroid.kikinbo.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference

class CancelOrderViewModel : ViewModel() {

    // Firebase Database reference for orders
    private val database = FirebaseDatabase.getInstance()
    private val orderInfoRef: DatabaseReference = database.getReference("OrderInfo")

    // Function to attempt to cancel an order based on its orderId
    fun cancelOrder(
        orderId1: String,
        callback: (String) -> Unit
    ) {
        // Directly reference the order by orderId
        val orderRef = orderInfoRef.child(orderId1)

        // Retrieve the order data
        orderRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                // Extract the order status, handle null case with safe call
                val orderStatus = snapshot.child("orderStatus").getValue(String::class.java)

                // Check the order status and proceed accordingly
                when (orderStatus) {
                    "Pending" -> {
                        // If order status is "Pending", update it to "Cancelled"
                        orderRef.child("orderStatus").setValue("Cancelled")
                            .addOnSuccessListener {
                                callback("Your order is cancelled.")
                            }
                            .addOnFailureListener { error ->
                                callback("Unable to cancel your order due to a technical issue: ${error.message}. Please try again later.")
                            }
                    }
                    "Shipped" -> callback("Your order cannot be cancelled because it has already been shipped.")
                    "Delivered" -> callback("Order cannot be cancelled as it is already delivered.")
                    "Cancelled" -> callback("Order has already been cancelled.")
                    else -> callback("Unknown order status. Unable to process cancellation.")
                }
            } else {
                // If the order does not exist in the database
                callback("Order not found. Please verify the order number.")
            }
        }.addOnFailureListener { error ->
            // Handle failure to fetch order
            callback("Error: ${error.message}")
        }
    }
}
