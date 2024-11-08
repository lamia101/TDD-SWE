package com.devdroid.kikinbo.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.model.ProductDataModel
import com.devdroid.kikinbo.viewmodel.ProductViewModel

/**
 * Activity for displaying detailed information about a selected product.
 * Retrieves product data using the [ProductViewModel] and displays it in the UI.
 */
class ViewProduct : AppCompatActivity() {

    /**
     * ViewModel instance to retrieve product data from the repository.
     */
    private lateinit var productViewModel: ProductViewModel

    /**
     * TextView to display the product's price.
     */
    private lateinit var productPriceId: TextView

    /**
     * TextView to display the product's rating.
     */
    private lateinit var productRatingId: TextView

    /**
     * TextView to display the product's name.
     */
    private lateinit var productNameId: TextView

    /**
     * TextView to display the product's category.
     */
    private lateinit var productCatagoryId: TextView

    /**
     * TextView to display additional details about the product.
     */
    private lateinit var productDetailsId: TextView

    /**
     * The ID of the product to fetch, passed through the intent.
     */
    private lateinit var productIdToFetch: String

    /**
     * Initializes the activity, sets up the UI elements, and retrieves product data using the passed product ID.
     * Also configures window insets for seamless edge-to-edge display.
     *
     * @param savedInstanceState The saved instance state bundle, if available.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_product)

        // Apply padding for system UI elements like the status bar and navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the ViewModel for fetching product data
        productViewModel = ProductViewModel()

        // Retrieve product ID from the intent extras
        productIdToFetch = intent.getStringExtra("productId") ?: ""

        // Bind the TextViews to layout views for displaying product details
        productNameId = findViewById(R.id.productnameid)
        productPriceId = findViewById(R.id.productPriceid)
        productRatingId = findViewById(R.id.ProductRatingid)
        productCatagoryId = findViewById(R.id.tvProductCatagoryid)
        productDetailsId = findViewById(R.id.productDetailsid)

        // Fetch and display the product details if the product ID is valid
        if (productIdToFetch.isNotEmpty()) {
            productViewModel.fetchProductData(productIdToFetch) { product ->
                if (product != null) {
                    displayProductDetails(product)
                } else {
                    Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Product ID not found", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Populates the UI with details of the provided product.
     *
     * @param product The [ProductDataModel] instance containing the product information to display.
     */
    private fun displayProductDetails(product: ProductDataModel) {
        productNameId.text = product.productName
        productPriceId.text = "${product.productPrice} BDT"
        productRatingId.text = "${product.productRating}"
        productCatagoryId.text = "${product.productCategory}"
        productDetailsId.text = "${product.productDetail}"
    }
}
