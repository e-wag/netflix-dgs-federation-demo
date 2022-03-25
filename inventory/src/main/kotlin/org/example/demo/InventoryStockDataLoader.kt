package org.example.demo

import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "inventory")
class InventoryStockDataLoader(
    private val inventoryGraphQlService: InventoryGraphQlService
): MappedBatchLoader<String, Boolean> {
    override fun load(keys: Set<String>): CompletionStage<Map<String, Boolean>> {
        return CompletableFuture.supplyAsync { inventoryGraphQlService.findProductsInStock(keys) }
    }
}
