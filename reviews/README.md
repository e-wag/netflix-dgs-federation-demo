# Review Service

## Reviews subgraph

```graphql
type Review @key(fields: "id") {
    id: ID!
    body: String
    author: User @provides(fields: "username")
    product: Product
}

type User @key(fields: "id") @extends {
    id: ID! @external
    username: String @external
    reviews: [Review]
}

type Product @key(fields: "upc") @extends {
    upc: String! @external
    reviews: [Review]
}
```

## Useful links
- [Netflix Dgs](https://netflix.github.io/dgs/)
- [Graphql schema](https://www.apollographql.com/docs/apollo-server/schema/schema/)
