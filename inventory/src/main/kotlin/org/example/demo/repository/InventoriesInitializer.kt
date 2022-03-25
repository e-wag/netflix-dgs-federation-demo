package org.example.demo.repository

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InventoriesInitializer(
    private val inventoryRepository: InventoryRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val inventories = listOf(
            InventoryEntity("UPC001", true),
            InventoryEntity("UPC002", false),
            InventoryEntity("UPC003", false),
            InventoryEntity("UPC004", true),
            InventoryEntity("UPC005", true),
            InventoryEntity("UPC006", true),
            InventoryEntity("UPC007", false),
            InventoryEntity("UPC008", true),
            InventoryEntity("UPC009", true),
            InventoryEntity("UPC010", true)
        )

        inventoryRepository.saveAll(inventories)
    }
}
