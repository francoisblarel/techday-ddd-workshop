## Exercise 3: Implementing the proposal

### The Proposal entity

Introduce a new entity `Proposal` based on the following requirements:

```
- A proposal has a proposed price.
- A proposal is placed by a musician.
- A placed proposal is waiting for a decision.
- A proposal can be accepted or rejected (if in waiting state).
```

- Create an `Proposal` class with the following properties:
  - `musicianId`: MusicianId
  - `proposedPrice`: Price
  - `status`: WAITING | ACCEPTED | REJECTED (enum)
- Add methods in the `Proposal` class to `accept` and `reject` the proposal, changing its status accordingly.

### Ad as an aggregate root

The `Ad` and `Proposal` entities are related with the following rules:

```
- An Ad can have multiple proposals from different musicians.
- When a proposal is accepted, the associated Ad is sold at the proposed price.
- If a musician tries to make more than one proposal for the same Ad, the last proposal should replace the previous one.
```

Add a `makeProposal(MusicianId, Price)` method to the `Ad` class to handle the creation and management of proposals.
Add the `acceptProposal(MusicianId)` and `rejectProposal(MusicianId)` methods to the `Ad` class to handle the
acceptance/rejection of a proposal

The `Ad` entity should be responsible for managing its proposals, ensuring that the business rules are enforced.
