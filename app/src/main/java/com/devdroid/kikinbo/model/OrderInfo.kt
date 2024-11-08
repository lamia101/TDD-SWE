package com.devdroid.kikinbo.model

import java.sql.Date

data class OrderInfo(
    val userId:String,
    val orderId:String,
    val orderDateId:String,
    val orderStatus:String
)

val order1 = OrderInfo(
    userId = "user1",
    orderId = "order001",
    orderDateId = "2024-11-01",
    orderStatus = "Pending"
)

val order2 = OrderInfo(
    userId = "user456",
    orderId = "order002",
    orderDateId = "2024-11-02",
    orderStatus = "Shipped"
)

val order3 = OrderInfo(
    userId = "user789",
    orderId = "order003",
    orderDateId = "2024-11-03",
    orderStatus = "Delivered"
)

val order4 = OrderInfo(
    userId = "user101",
    orderId = "order004",
    orderDateId = "2024-11-04",
    orderStatus = "Cancelled"
)

val order5 = OrderInfo(
    userId = "user202",
    orderId = "order005",
    orderDateId = "2024-11-05",
    orderStatus = "Pending"
)
