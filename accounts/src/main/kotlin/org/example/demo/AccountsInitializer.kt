package org.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class AccountsInitializer(
    private val accountRepository: AccountRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val accounts = listOf(
            Account("b79dccc1-a0a4-410b-9c83-3c3cec7dc3d5", "John Doe", "john"),
            Account("b3e21fe8-7d2b-4ce6-95e5-7a39fa4ad448", "Jane Doe", "jane"),
            Account("c084ec68-6294-4ff2-a84b-a7cfd0b7b2b4", "James Doe", "james")
        )

        accountRepository.saveAll(accounts)
    }
}