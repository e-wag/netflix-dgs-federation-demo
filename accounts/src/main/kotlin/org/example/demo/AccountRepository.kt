package org.example.demo

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Account(
    @Id
    var id: String,
    var name: String,
    var username: String,
)

interface AccountRepository : CrudRepository<Account, String>
