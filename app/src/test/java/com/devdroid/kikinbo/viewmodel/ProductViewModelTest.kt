package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.spy
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ProductViewModelTest {

    private lateinit var viewModel: ProductViewModel

    @Mock
    private lateinit var mockDatabaseRef: DatabaseReference

    @Mock
    private lateinit var mockDataSnapshot: DataSnapshot

    @Mock
    private lateinit var mockDatabaseError: DatabaseError

    @Captor
    private lateinit var captor: ArgumentCaptor<ValueEventListener>

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ProductViewModel()

        // Override Firebase database reference in the view model
        viewModel = spy(viewModel)
        doReturn(mockDatabaseRef).`when`(viewModel).databaseRef
    }

    @Test
    fun `fetchProductData - product found`() {
        // Arrange
        val productId = "Mouseid"
        val expectedProduct = ProductDataModel(productId = productId, productName = "Mouse")
        `when`(mockDataSnapshot.exists()).thenReturn(true)
        `when`(mockDataSnapshot.children).thenReturn(listOf(mockDataSnapshot))
        `when`(mockDataSnapshot.getValue(ProductDataModel::class.java)).thenReturn(expectedProduct)

        doAnswer {
            captor.value.onDataChange(mockDataSnapshot)
        }.`when`(mockDatabaseRef).addListenerForSingleValueEvent(captor.capture())

        var result: ProductDataModel? = null

        // Act
        viewModel.fetchProductData(productId) { product ->
            result = product
        }

        // Assert
        assertEquals(expectedProduct, result)
    }

    @Test
    fun `fetchProductData - product not found`() {
        // Arrange
        val productId = "nonExistentProductId"
        `when`(mockDataSnapshot.exists()).thenReturn(false)

        doAnswer {
            captor.value.onDataChange(mockDataSnapshot)
        }.`when`(mockDatabaseRef).addListenerForSingleValueEvent(captor.capture())

        var result: ProductDataModel? = null

        // Act
        viewModel.fetchProductData(productId) { product ->
            result = product
        }

        // Assert
        assertNull(result)
    }

    @Test
    fun `fetchProductData - database error`() {
        // Arrange
        val productId = "errorProductId"
        doAnswer {
            captor.value.onCancelled(mockDatabaseError)
        }.`when`(mockDatabaseRef).addListenerForSingleValueEvent(captor.capture())

        var result: ProductDataModel? = null

        // Act
        viewModel.fetchProductData(productId) { product ->
            result = product
        }

        // Assert
        assertNull(result)
    }
}
