package org.example.demo

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Inventory(
    @Id
    var upc: String,
    var inStock: Boolean,
)

interface InventoryRepository : CrudRepository<Inventory, String>
