package org.example.demo

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.DgsEntityFetcher
import com.netflix.graphql.dgs.DgsQuery
import org.example.demo.dgs.generated.DgsConstants
import org.example.demo.dgs.generated.types.Product
import org.example.demo.dgs.generated.types.Review
import org.example.demo.dgs.generated.types.User
import java.util.concurrent.CompletableFuture

@DgsComponent
class DgsReviewsGraph(
    private val reviewsGraphQlService: ReviewsGraphQlService
) {

    /**
     * This is also unnecessary as this is defined in the product service!
     */
//    @DgsEntityFetcher(name = DgsConstants.PRODUCT.TYPE_NAME)
//    fun product(values: Map<String, Any?>): Product = Product(
//        upc = values[DgsConstants.PRODUCT.Upc] as String
//    )

//    @DgsEntityFetcher(name = DgsConstants.USER.TYPE_NAME)
//    fun user(values: Map<String, Any?>): User = User(
//        id = values[DgsConstants.USER.Id] as String
//    )

    /**
     * This will trigger when another services extends Review type in their service.
     * As this is not the case anywhere, this won't be called.
     */
    @DgsEntityFetcher(name = DgsConstants.REVIEW.TYPE_NAME)
    fun review(value: Map<String, Any?>, dfe: DgsDataFetchingEnvironment): CompletableFuture<Review> {
        val loader = dfe.getDataLoader<String, Review>(ReviewDataLoader::class.java)
        return loader.load(value[DgsConstants.REVIEW.Id] as String)
    }

    @DgsQuery(field = DgsConstants.QUERY.Reviews)
    fun getReviews(): List<Review> = reviewsGraphQlService.getReviews()

    /**
     * This will trigger when you add reviews to a product
     */
    @DgsData(parentType = DgsConstants.PRODUCT.TYPE_NAME, field = DgsConstants.PRODUCT.Reviews)
    fun getProductReviews(dfe: DgsDataFetchingEnvironment): CompletableFuture<List<Review>> {
        val product: Product = dfe.getSource()
        val productReviewsLoader = dfe.getDataLoader<String, List<Review>>(ProductReviewsDataLoader::class.java)

        return productReviewsLoader.load(product.upc)
    }

    /**
     * This seems unnecessary as @DgsEntityDataFetcher in product service will be called
     *
     */
//    @DgsData(parentType = DgsConstants.REVIEW.TYPE_NAME, field = DgsConstants.REVIEW.Product)
//    fun getReviewProduct(dfe: DgsDataFetchingEnvironment): CompletableFuture<Product> {
//        val review: Review = dfe.getSource()
//        val reviewProductsLoader = dfe.getDataLoader<String, Product>(ReviewsProductDataLoader::class.java)
//
//        return reviewProductsLoader.load(review.product?.upc)
//    }
}
