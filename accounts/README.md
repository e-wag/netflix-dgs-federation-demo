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
- [H2 console](http://localhost:8081/h2-console)