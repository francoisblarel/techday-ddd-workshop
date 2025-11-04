## Exercise 2: Implementing the discount

### Add the discount method

Implement a `discount` method in the `Ad` class based on the following requirements:

```
- A musician can apply a discount on the price of his ad.
- The discount is a percentage of the price (between 0% and 100%).
- A price cannot be negative.
```

### Introducing the Price value object

Implement a `Price` object (using a java record) to encapsulate the price logic.
Replace the `price` property in the `Ad` class with the `Price` object.
The `Price` object should handle the discount calculation.
