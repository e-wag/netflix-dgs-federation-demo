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
- [H2 console](http://localhost:8082/h2-console)