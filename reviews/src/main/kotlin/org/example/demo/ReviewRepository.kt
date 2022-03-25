package org.example.demo

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Review(
    @Id
    var id: String,
    var body: String,
    var productUpc: String,
    var authorId: String,
)

interface ReviewRepository : CrudRepository<Review, String>
