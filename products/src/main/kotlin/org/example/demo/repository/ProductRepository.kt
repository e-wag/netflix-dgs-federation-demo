package org.example.demo.repository

import org.example.demo.repository.ProductEntity_.upc
import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.Id

@Entity
class ProductEntity(
    @Id
    var upc: String,
    var name: String,
    var price: Int,
    var weight: Int
)

interface ProductCriteriaQuery {
    fun findByUpcs(upcs: Set<String>): List<ProductEntity>
    fun findByUpc(upc: String): ProductEntity
    fun findTop(first: Int): List<ProductEntity>
}
interface ProductRepository : CrudRepository<ProductEntity, String>, ProductCriteriaQuery

class ProductCriteriaQueryImpl(private val entityManager: EntityManager) : ProductCriteriaQuery {

    private val cb = entityManager.criteriaBuilder
    override fun findByUpcs(upcs: Set<String>): List<ProductEntity> {
        val query = cb.createQuery(ProductEntity::class.java)
        val root = query.from(ProductEntity::class.java)

        query
            .select(root)
            .where(root.get<String>(ProductEntity_.UPC).`in`(upcs))

        return entityManager.createQuery(query).resultList
    }

    override fun findByUpc(upc: String): ProductEntity {
        val query = cb.createQuery(ProductEntity::class.java)
        val root = query.from(ProductEntity::class.java)

        query
            .select(root)
            .where(
                cb.equal(root.get<String>(ProductEntity_.UPC), upc)
            )

        return entityManager.createQuery(query).resultList.first()
    }

    override fun findTop(first: Int): List<ProductEntity> {
        val query = cb.createQuery(ProductEntity::class.java)
        val product = query.from(ProductEntity::class.java)

        query.select(product)

        return entityManager.createQuery(query)
            .also { it.maxResults = first }
            .resultList
    }

}
