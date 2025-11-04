# Exercices

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

As we don't want the Ad to be in an invalid state, make sure that the properties are set through the constructor and are
immutable.

### 2. Add the sell method

Implement a `sell` method in the `Ad` class that changes the status of the Ad to `SOLD_OUT`.
Ensure that an Ad can only be sold if it is currently `AVAILABLE`.
If an attempt is made to sell an Ad that is already `SOLD_OUT`, throw an appropriate exception.

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
The `Price` object should handle the discount calculation.

#### Exercise 3: Implementing the proposal

### The Proposal entity

Introduce a new entity `Proposal` based on the following requirements:

```
- A proposal has a proposed price.
- A proposal is placed by a musician.
- A proposal can be accepted or rejected.
```

### Ad as an aggregate root

The `Ad` and  `Proposal` entities are related with the following rules:

```
- When a proposal is accepted, the associated Ad is sold at the proposed price.
- If a musician tries to make more than one proposal for the same Ad, the last proposal should replace the previous one.
```

Add a `makeProposal(MusicianId, Price)` method to the `Ad` class to handle the creation and management of proposals.
Add the `acceptProposal(MusicianId)` and `rejectProposal(MusicianId)` methods to the `Ad` class to handle the
acceptance/rejection of a proposal
and update the Ad's status.

The `Ad` entity should be responsible for managing its proposals, ensuring that the business rules are enforced.

#### Exercise 4: Introducing the premium feature

Consider that we want to introduce a premium feature for Ads.

```
- A musician can only post up to 3 ads unless he has a premium reputation.
```

The reputation feature has already been implemented in the `Musician` bounded context.

As the rule involves two aggregates (Ad and Musician), we need to find a way to enforce this rule without violating the
principles of DDD.

Add a domain service `AdPostingService` with a method `canPostAd(MusicianId)` that checks if a musician can post a new
Ad based on their reputation and the number of existing Ads.



