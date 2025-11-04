## Exercise 1: Implementing the Ad

Implement a simple domain model for the Ad entity based on the following requirements:

```
- A musician can publish an Ad to sell an instrument.
- An Ad should have a title, an instrument, and a price.
- An Ad can be sold
- An Ad is available to sell until it is sold.
```

### 1. Create the Ad class

- Create an `Ad` class with the following properties:
  - `title`: string
  - `instrument`: string (for simplicity, we use a string to represent the instrument)
  - `price`: BigDecimal
  - currency: Currency
  - `status`: AVAILABLE | SOLD_OUT (enum)

As we don't want the Ad to be in an invalid state, make sure that the properties are set through the constructor
and the class does not expose any public setters.

### 2. Add the sell method

Implement a `sell` method in the `Ad` class that changes the status of the Ad to `SOLD_OUT`.
Ensure that an Ad can only be sold if it is currently `AVAILABLE`.
If an attempt is made to sell an Ad that is already `SOLD_OUT`, throw an appropriate exception.
