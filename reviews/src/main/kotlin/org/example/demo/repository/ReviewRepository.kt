package org.example.demo.repository

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.Id

@Entity
class ReviewEntity(
    @Id
    var id: String,
    var body: String,
    var productUpc: String,
    var authorId: String,
)

interface ReviewsQueryCriteria {
    fun getReviewsByIds(ids: Set<String>): List<ReviewEntity>
    fun getReviewsByUpcs(upcs: Set<String>): List<ReviewEntity>
}
interface ReviewRepository : CrudRepository<ReviewEntity, String>, ReviewsQueryCriteria

class ReviewsQueryCriteriaImpl(
    private val entityManager: EntityManager
): ReviewsQueryCriteria {
    private val cb = entityManager.criteriaBuilder
    override fun getReviewsByIds(ids: Set<String>): List<ReviewEntity> {
        val query = cb.createQuery(ReviewEntity::class.java)
        val root = query.from(ReviewEntity::class.java)

        query
            .select(root)
            .where(root.get<String>(ReviewEntity_.ID).`in`(ids))

        return entityManager.createQuery(query).resultList
    }

    override fun getReviewsByUpcs(upcs: Set<String>): List<ReviewEntity> {
        val query = cb.createQuery(ReviewEntity::class.java)
        val root = query.from(ReviewEntity::class.java)

        query
            .select(root)
            .where(root.get<String>(ReviewEntity_.PRODUCT_UPC).`in`(upcs))

        return entityManager.createQuery(query).resultList
    }
}
