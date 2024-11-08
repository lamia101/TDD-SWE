package com.devdroid.kikinbo.viewmodel

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
//import org.mockito.kotlin.argumentCaptor



class CancelOrderViewModelTest{
    private lateinit var cancelOrderViewModel: CancelOrderViewModel
    private lateinit var mockDatabaseReference: DatabaseReference
    private lateinit var mockDataSnapshot: DataSnapshot
    private lateinit var mockDatabase: FirebaseDatabase
    private lateinit var mockTask: Task<DataSnapshot>

    @BeforeEach
    fun setUp(){
        // Initialize the CancelOrderViewModel
        cancelOrderViewModel = CancelOrderViewModel()

        // Mock the Firebase Database Reference
        mockDatabase=mock(FirebaseDatabase::class.java)
        mockDatabaseReference=mock(DatabaseReference::class.java)
        mockDataSnapshot= mock(DataSnapshot::class.java)
        mockTask= mock(Task::class.java) as Task<DataSnapshot>
        // Mock the database instance to return the mock reference
        //'when(mockDatabase.getReference(any<String>())).thenReturn(mockDatabaseReference)
        `when`(mockDatabase.getReference(any<String>())).thenReturn(mockDatabaseReference)
    }
//    @Test
//    fun testCancelOrder_SuccessPending() {
//        // Arrange: Create a mock snapshot where the order exists with status "Pending"
//        val orderId = "order001"
//        val orderStatus = "Pending"
//
//        // Simulate the snapshot returning the status
//        `when`(mockDataSnapshot.child("orderStatus").getValue(String::class.java)).thenReturn(orderStatus)
//
//        // Simulate the Task returning the DataSnapshot
//        `when`(mockTask.result).thenReturn(mockDataSnapshot)
//        `when`(mockDatabaseReference.get()).thenReturn(mockTask)
//
//        // Act: Call the cancelOrder method
//        val callbackCaptor = argumentCaptor<(String) -> Unit>()
//        cancelOrderViewModel.cancelOrder(orderId, callbackCaptor.capture())
//
//        // Simulate a successful response when the order status is "Pending"
//        verify(mockDatabaseReference).child(orderId)
//        verify(mockDatabaseReference).get()
//
//        // Trigger the callback with the success message
//        callbackCaptor.firstValue.invoke("Your order is cancelled.")
//
//        // Assert: Verify that the callback is triggered with the correct success message
//        val result = callbackCaptor.firstValue
//        assert(result == "Your order is cancelled.")
//    }

//    @Test
//    fun testCancelOrder_SuccessDelivered() {
//        // Arrange: Create a mock snapshot where the order exists with status "Delivered"
//        val orderId = "order003"
//        val orderStatus = "Delivered"
//
//        // Simulate the snapshot returning the status
//        `when`(mockDataSnapshot.child("orderStatus").getValue(String::class.java)).thenReturn(orderStatus)
//
//        // Simulate the order existing
//        `when`(mockDatabaseReference.get()).thenReturn(mockDataSnapshot)
//
//        // Act: Call the cancelOrder method
//        val callbackCaptor = argumentCaptor<(String) -> Unit>()
//        cancelOrderViewModel.cancelOrder(orderId, callbackCaptor.capture())
//
//        // Simulate failure when the order is "Delivered"
//        callbackCaptor.firstValue.invoke("Order cannot be cancelled as it is already delivered.")
//
//        // Assert: Verify the callback message
//        val result = callbackCaptor.firstValue
//        assert(result == "Order cannot be cancelled as it is already delivered.")
//    }
}