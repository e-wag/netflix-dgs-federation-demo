package org.example.demo.repository

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.Id

@Entity
class Account(
    @Id
    var id: String,
    var name: String,
    var username: String,
)

interface AccountCriteriaQuery{
    fun findByUsername(username: String): Account
}
interface AccountRepository : CrudRepository<Account, String>, AccountCriteriaQuery

class AccountCriteriaQueryImpl(private val entityManager: EntityManager): AccountCriteriaQuery {

    private val cb = entityManager.criteriaBuilder
    override fun findByUsername(username: String): Account {
        val query = cb.createQuery(Account::class.java)
        val root = query.from(Account::class.java)

        query
            .select(root)
            .where(cb.equal(root.get<String>(Account_.USERNAME), username))

        return entityManager.createQuery(query).singleResult
    }

}
