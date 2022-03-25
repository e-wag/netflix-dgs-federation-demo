# Product Service

## Product subgraph
```graphql
type Query {
    topProducts(first: Int = 5): [Product]
}

type Product @key(fields: "upc") {
    upc: String!
    name: String
    price: Int
    weight: Int
}
```

## Useful links
- [Netflix Dgs](https://netflix.github.io/dgs/)
- [Graphql schema](https://www.apollographql.com/docs/apollo-server/schema/schema/)
