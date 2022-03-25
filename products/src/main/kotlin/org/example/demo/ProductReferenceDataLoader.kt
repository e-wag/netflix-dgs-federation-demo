package org.example.demo

import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import org.example.demo.dgs.generated.types.Product
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "ProductReferenceLoader")
class ProductReferenceDataLoader(
    private val productGraphQlService: ProductGraphQlService
): MappedBatchLoader<String, Product> {
    override fun load(keys: Set<String>): CompletionStage<Map<String, Product>> {
        return CompletableFuture.supplyAsync { productGraphQlService.getProductsByUpcs(keys) }
    }
}
