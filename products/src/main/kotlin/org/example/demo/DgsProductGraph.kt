package org.example.demo

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsEntityFetcher
import com.netflix.graphql.dgs.DgsQuery
import org.example.demo.dgs.generated.DgsConstants
import org.example.demo.dgs.generated.types.Product
import java.util.concurrent.CompletableFuture

@DgsComponent
class DgsProductGraph(
    private val productGraphQlService: ProductGraphQlService
) {


    /**
     * Bad practice: Causes n+1 queries
     */
//    @DgsEntityFetcher(name = DgsConstants.PRODUCT.TYPE_NAME)
//    fun resolveProductReference(values: Map<String, Any?>): Product =
//        productGraphQlService.findByUpc(values[DgsConstants.PRODUCT.Upc] as String)

    /**
     * Good practice: Causes 1 query call
     *
     * Rule of thumb so far: Always use data loaders for data fetchers
     */
    @DgsEntityFetcher(name = DgsConstants.PRODUCT.TYPE_NAME)
    fun resolveProductReference(
        values: Map<String, Any?>,
        dataFetchingEnvironment: DgsDataFetchingEnvironment
    ): CompletableFuture<Product> {
        val loader = dataFetchingEnvironment.getDataLoader<String, Product>(ProductReferenceDataLoader::class.java)
        return loader.load(values[DgsConstants.PRODUCT.Upc] as String)
    }

    @DgsQuery(field = DgsConstants.QUERY.TopProducts)
    fun topProducts(first: Int): List<Product> = productGraphQlService.findTop(first)
}
