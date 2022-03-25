package org.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InventoriesInitializer(
    private val inventoryRepository: InventoryRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val inventories = listOf(
            Inventory("UPC001", true),
            Inventory("UPC002", false),
            Inventory("UPC003", false),
            Inventory("UPC004", true),
            Inventory("UPC005", true),
            Inventory("UPC006", true),
            Inventory("UPC007", false),
            Inventory("UPC008", true),
            Inventory("UPC009", true),
            Inventory("UPC010", true)
        )

        inventoryRepository.saveAll(inventories)
    }
}