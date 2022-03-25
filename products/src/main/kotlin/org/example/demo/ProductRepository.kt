package org.example.demo

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product(
    @Id
    var upc: String,
    var name: String,
    var price: Int,
    var weight: Int
)

interface ProductRepository : CrudRepository<Product, String>