package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.OrderInfo
import com.devdroid.kikinbo.model.order1
import com.devdroid.kikinbo.model.order2
import com.devdroid.kikinbo.model.order3
import com.devdroid.kikinbo.model.order4
import com.devdroid.kikinbo.model.order5
import com.google.firebase.database.FirebaseDatabase

class PushOrderInfo {
    //    private val database=FirebaseDatabase.getInstance()
//    private val databaseRef=database.getReference("OrderInfo")
//
    fun fPushOrderInfo() {
//        databaseRef.push().setValue(order1)
//        databaseRef.push().setValue(order2)
//        databaseRef.push().setValue(order3)
//        databaseRef.push().setValue(order4)
//        databaseRef.push().setValue(order5)
//    }

        val database = FirebaseDatabase.getInstance()
        val orderInfoRef = database.getReference("OrderInfo")

        // List of orders to add
        val orders = listOf(
            OrderInfo(
                userId = "user1",
                orderId = "order001",
                orderDateId = "2024-11-01",
                orderStatus = "Pending"
            ),
            OrderInfo(
                userId = "user456",
                orderId = "order002",
                orderDateId = "2024-11-02",
                orderStatus = "Shipped"
            ),
            OrderInfo(
                userId = "user789",
                orderId = "order003",
                orderDateId = "2024-11-03",
                orderStatus = "Delivered"
            ),
            OrderInfo(
                userId = "user101",
                orderId = "order004",
                orderDateId = "2024-11-04",
                orderStatus = "Cancelled"
            ),
            OrderInfo(
                userId = "user202",
                orderId = "order005",
                orderDateId = "2024-11-05",
                orderStatus = "Pending"
            )
        )

// Add each order to Firebase
        for (order in orders) {
            orderInfoRef.child(order.orderId).setValue(order)
                .addOnSuccessListener {
                    // Successfully added order
                    println("Order ${order.orderId} added successfully")
                }
                .addOnFailureListener { error ->
                    // Handle failure
                    println("Failed to add order ${order.orderId}: ${error.message}")
                }
        }

    }
}
