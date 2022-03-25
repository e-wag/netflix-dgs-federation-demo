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
- [Netflix Dgs](https://netflix.github.io/dgs/)
