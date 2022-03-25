package org.example.demo

import org.example.demo.repository.InventoryEntity
import org.example.demo.repository.InventoryRepository
import org.springframework.stereotype.Service

interface InventoryGraphQlService {
    fun findByUpc(upc: String): InventoryEntity

    fun findProductsInStock(upcs: Set<String>): Map<String, Boolean>
}

@Service
class InventoryGraphQlServiceImpl(
    private val inventoryRepository: InventoryRepository
): InventoryGraphQlService {
    override fun findByUpc(upc: String): InventoryEntity = inventoryRepository.findByUpc(upc)
    override fun findProductsInStock(upcs: Set<String>): Map<String, Boolean> {
        return inventoryRepository.findByUpcs(upcs)
            .associate { it.upc to it.inStock }
    }
}
