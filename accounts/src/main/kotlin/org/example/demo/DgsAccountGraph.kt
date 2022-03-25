package org.example.demo

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsEntityFetcher
import com.netflix.graphql.dgs.DgsQuery
import org.example.demo.dgs.generated.DgsConstants
import org.example.demo.dgs.generated.types.User

@DgsComponent
class DgsAccountGraph(
    private val accountGraphQlService: AccountGraphQlService
) {

    @DgsEntityFetcher(name = DgsConstants.USER.TYPE_NAME)
    fun resolveUserReference(values: Map<String, Any?>): User =
        accountGraphQlService.findById(values[DgsConstants.USER.Id] as String)

    /**
     * Just hard coded, but here could probably be some SecurityContext magic involved
     */
    @DgsQuery(field = DgsConstants.QUERY.Me)
    fun getCurrentUser() = accountGraphQlService.findByUsername("john")
}
