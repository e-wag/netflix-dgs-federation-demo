package org.example.demo

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsEntityFetcher
import org.example.demo.dgs.generated.DgsConstants
import org.example.demo.dgs.generated.types.Product
import java.util.concurrent.CompletableFuture

@DgsComponent
class DgsInventoryGraph(
    private val inventoryGraphQlService: InventoryGraphQlService
) {

    @DgsEntityFetcher(name = DgsConstants.PRODUCT.TYPE_NAME)
    fun product(values: Map<String, Any?>): Product = Product(
        upc = values[DgsConstants.PRODUCT.Upc] as String,
        price = values[DgsConstants.PRODUCT.Price] as Int?,
        weight = values[DgsConstants.PRODUCT.Weight] as Int?
    )

    /**
     * Bad practice to call inventoryService here as this will cause n+1 queries
     */
    /*@DgsData(parentType = DgsConstants.PRODUCT.TYPE_NAME, field = DgsConstants.PRODUCT.InStock)
    fun isProductInStock(dfe: DgsDataFetchingEnvironment): List<Boolean> {
        val product: Product = dfe.getSource()
        val inventory = inventoryService.findByUpc(product.upc)

        return inventory.inStock
    }*/

    /**
     * Keep in mind that this function will be called as many times as products are requested.
     * So product amount == function calls
     * Use data loader to prevent n+1 queries!
     * The dataloader adds every requested product upc to a batch request resulting in a single query.
     */
    @DgsData(parentType = DgsConstants.PRODUCT.TYPE_NAME, field = DgsConstants.PRODUCT.InStock)
    fun isProductInStock(dfe: DgsDataFetchingEnvironment): CompletableFuture<Boolean> {
        val product: Product = dfe.getSource()
        val inventoryStockDataLoader = dfe.getDataLoader<String, Boolean>(InventoryStockDataLoader::class.java)

        return inventoryStockDataLoader.load(product.upc)
    }

    @DgsData(parentType = DgsConstants.PRODUCT.TYPE_NAME, field = DgsConstants.PRODUCT.ShippingEstimate)
    fun estimateShipment(dfe: DgsDataFetchingEnvironment): Int {
        val product: Product = dfe.getSource()

        return if(product.price.orDefault(0) > 100) 0 else product.weight.orDefault(0) * 2
    }
}

fun Int?.orDefault(default: Int = 0) = this ?: default
