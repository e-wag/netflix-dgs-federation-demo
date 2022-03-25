package org.example.demo.repository

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.Id

@Entity
class InventoryEntity(
    @Id
    var upc: String,
    var inStock: Boolean,
)

interface InventoryQueryCriteria {
    fun findByUpc(upc: String): InventoryEntity

    fun findByUpcs(upcs: Set<String>): List<InventoryEntity>
}
interface InventoryRepository : CrudRepository<InventoryEntity, String>, InventoryQueryCriteria

class InventoryQueryCriteriaImpl(private val entityManager: EntityManager): InventoryQueryCriteria {
    private val cb = entityManager.criteriaBuilder
    override fun findByUpc(upc: String): InventoryEntity {
        val query = cb.createQuery(InventoryEntity::class.java)
        val root = query.from(InventoryEntity::class.java)

        query
            .select(root)
            .where(cb.equal(root.get<String>(InventoryEntity_.UPC), upc))

        return entityManager.createQuery(query).singleResult
    }

    override fun findByUpcs(upcs: Set<String>): List<InventoryEntity> {
        val query = cb.createQuery(InventoryEntity::class.java)
        val root = query.from(InventoryEntity::class.java)

        query
            .select(root)
            .where(root.get<Boolean?>(InventoryEntity_.UPC).`in`(upcs))

        return entityManager.createQuery(query).resultList
    }
}
