package org.example.demo

import org.example.demo.dgs.generated.types.Product
import org.example.demo.dgs.generated.types.Review
import org.example.demo.dgs.generated.types.User
import org.example.demo.repository.ReviewEntity
import org.example.demo.repository.ReviewRepository
import org.springframework.stereotype.Service

interface ReviewsGraphQlService {
    fun getReviews(): List<Review>

    fun getReviewsByIds(reviewIds: Set<String>): Map<String, Review>
    fun getProductsByReviewIds(reviewIds: Set<String>): Map<String, Product>
    fun getReviewsByUpcs(upcs: Set<String>): Map<String, List<Review>>
}

@Service
class ReviewsGraphQlServiceImpl(
    private val reviewsRepository: ReviewRepository
): ReviewsGraphQlService {
    override fun getReviews(): List<Review> {
        return reviewsRepository.findAll().map { it.toGraph() }
    }

    override fun getReviewsByIds(reviewIds: Set<String>): Map<String, Review> {
        return reviewsRepository.getReviewsByIds(reviewIds)
            .map { it.toGraph() }
            .associateBy { it.id }
    }

    override fun getProductsByReviewIds(reviewIds: Set<String>): Map<String, Product> {
        return reviewsRepository.getReviewsByIds(reviewIds)
            .associate { it.id to Product(upc = it.productUpc) }
    }

    override fun getReviewsByUpcs(upcs: Set<String>): Map<String, List<Review>> {

        return reviewsRepository.getReviewsByUpcs(upcs)
            .groupBy { it.productUpc }
            .mapValues { (_, v) -> v.map { it.toGraph() } }
    }

    fun ReviewEntity.toGraph(): Review = Review(
        id = this.id,
        body = this.body,
        author = User(id = authorId),
        product = Product(upc = this.productUpc)
    )
}
