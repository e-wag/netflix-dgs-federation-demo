package org.example.demo

import com.fasterxml.jackson.databind.MapperFeature
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import org.example.demo.dgs.generated.types.Product
import org.example.demo.dgs.generated.types.Review
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "ProductReviews")
class ProductReviewsDataLoader(
    private val reviewsGraphQlService: ReviewsGraphQlService
): MappedBatchLoader<String, List<Review>> {
    override fun load(keys: Set<String>): CompletionStage<Map<String, List<Review>>> {
        return CompletableFuture.supplyAsync { reviewsGraphQlService.getReviewsByUpcs(keys) }
    }
}

@DgsDataLoader(name = "ReviewsProduct")
class ReviewsProductDataLoader(
    private val reviewsGraphQlService: ReviewsGraphQlService
): MappedBatchLoader<String, Product> {
    override fun load(keys: Set<String>): CompletionStage<Map<String, Product>> {
        return CompletableFuture.supplyAsync { reviewsGraphQlService.getProductsByReviewIds(keys) }
    }
}

@DgsDataLoader(name = "ReviewLoader")
class ReviewDataLoader(
    private val reviewsGraphQlService: ReviewsGraphQlService
): MappedBatchLoader<String, Review> {
    override fun load(keys: Set<String>): CompletionStage<Map<String, Review>> {
        return CompletableFuture.supplyAsync { reviewsGraphQlService.getReviewsByIds(keys) }
    }

}
