package org.example.demo

import org.example.demo.dgs.generated.types.Product
import org.example.demo.repository.ProductEntity
import org.example.demo.repository.ProductRepository
import org.springframework.stereotype.Service

interface ProductGraphQlService {

    fun getProductsByUpcs(upcs: Set<String>): Map<String, Product>
    fun findByUpc(upc: String): Product
    fun findTop(first: Int): List<Product>
}

@Service
class ProductGraphQlServiceImpl(
    private val productRepository: ProductRepository
): ProductGraphQlService {
    override fun getProductsByUpcs(upcs: Set<String>): Map<String, Product> {
        return productRepository.findByUpcs(upcs)
            .map { it.toGraph() }
            .associateBy { it.upc }
    }

    override fun findByUpc(upc: String): Product {
        return productRepository.findByUpc(upc).toGraph()
    }

    override fun findTop(first: Int): List<Product> = productRepository.findTop(first)
        .map { it.toGraph() }

    private fun ProductEntity.toGraph(): Product = this
        .let {
            Product(
                upc = it.upc,
                name = it.name,
                price = it.price,
                weight = it.weight,
            )
        }
}
