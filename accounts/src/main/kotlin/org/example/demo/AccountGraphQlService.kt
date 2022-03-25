package org.example.demo

import org.example.demo.dgs.generated.types.User
import org.example.demo.repository.Account
import org.example.demo.repository.AccountRepository
import org.springframework.stereotype.Service

interface AccountGraphQlService {
    fun findById(id: String): User
    fun findByUsername(username: String): User
}

@Service
class AccountGraphQlServiceImpl(
    private val accountRepository: AccountRepository,
): AccountGraphQlService {
    override fun findById(id: String): User = accountRepository.findById(id).orElse(null).toGraph()

    override fun findByUsername(username: String): User = accountRepository.findByUsername(username).toGraph()

    private fun Account.toGraph() = User(
        id = this.id,
        name = this.name,
        username = this.username,
    )
}
