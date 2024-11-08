package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel
import com.google.firebase.database.*

/**
 * ViewModel responsible for managing and fetching product data from Firebase.
 * Connects to the "Products" reference in Firebase Realtime Database to retrieve
 * product details based on a given product ID.
 */
class ProductViewModel {

    /** Reference to the Firebase "Products" database node. */
    val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Products")

    /**
     * Fetches product data for a specified product ID.
     *
     * Queries the Firebase database for a product matching the provided product ID.
     * Once data is retrieved, it invokes a callback with the resulting [ProductDataModel]
     * if found, or null if no matching product exists or an error occurs.
     *
     * @param productId The unique ID of the product to be fetched.
     * @param callback A callback function that is invoked with the [ProductDataModel] if
     *                 found, or null if the product does not exist or if an error occurs.
     */
    fun fetchProductData(productId: String, callback: (ProductDataModel?) -> Unit) {
        databaseRef.orderByChild("productId").equalTo(productId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (child in snapshot.children) {
                            val product = child.getValue(ProductDataModel::class.java)
                            callback(product)
                            return // Exit after finding the product
                        }
                    }
                    callback(null) // No product found
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null) // Handle error by returning null
                }
            })
    }
}
