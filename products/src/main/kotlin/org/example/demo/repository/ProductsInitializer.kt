package org.example.demo.repository

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ProductsInitializer(
    private val productRepository: ProductRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val productEntities = listOf(
            ProductEntity("UPC001", "Product 1", 10, 10),
            ProductEntity("UPC002", "Product 2", 50, 9),
            ProductEntity("UPC003", "Product 3", 60, 15),
            ProductEntity("UPC004", "Product 4", 35, 20),
            ProductEntity("UPC005", "Product 5", 48, 4),
            ProductEntity("UPC006", "Product 6", 20, 6),
            ProductEntity("UPC007", "Product 7", 100, 3),
            ProductEntity("UPC008", "Product 8", 140, 5),
            ProductEntity("UPC009", "Product 9", 28, 12),
            ProductEntity("UPC010", "Product 10", 56, 9)
        )
        productRepository.saveAll(productEntities)
    }
}
