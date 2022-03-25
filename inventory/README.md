# Inventory Service

## Inventory subgraph

```graphql
type Product @key(fields: "upc") @extends {
    upc: String! @external
    weight: Int @external
    price: Int @external
    inStock: Boolean
    shippingEstimate: Int @requires(fields: "price weight")
}
```

## Useful links
- [H2 console](http://localhost:8084/h2-console)