package org.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ProductsInitializer(
    private val productRepository: ProductRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val products = listOf(
            Product("UPC001", "Product 1", 10, 10),
            Product("UPC002", "Product 2", 50, 9),
            Product("UPC003", "Product 3", 60, 15),
            Product("UPC004", "Product 4", 35, 20),
            Product("UPC005", "Product 5", 48, 4),
            Product("UPC006", "Product 6", 20, 6),
            Product("UPC007", "Product 7", 100, 3),
            Product("UPC008", "Product 8", 140, 5),
            Product("UPC009", "Product 9", 28, 12),
            Product("UPC010", "Product 10", 56, 9)
        )
        productRepository.saveAll(products)
    }
}