# Account Service

## Account subgraph

```graphql
type Query {
    me: User
}

type User @key(fields: "id") {
    id: ID!
    name: String
    username: String
}
```

## Useful links
- [Netflix Dgs](https://netflix.github.io/dgs/)
- [Graphql schema](https://www.apollographql.com/docs/apollo-server/schema/schema/)
