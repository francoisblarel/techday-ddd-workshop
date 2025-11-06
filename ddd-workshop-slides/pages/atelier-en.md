---
layout: center
class: text-center
---

## Hands-on

<!--
We are going to dive into the tactical part. Rather than presenting all the concepts one by one,
we will start from a naive approach in order to highlight them as we go along.
-->
---
layout: center
class: text-center
---

## Scope and limitations

<!--
We will start from some simple use cases of the application in order to highlight the different patterns.
We have limited time, so we will deliberately leave out certain aspects. Notably the infrastructure part.
-->

---
layout: center
class: text-center
---

## Architecture

<!--
Rapid presentation of the proposed architecture

TIMING: 20 min
-->

---
layout: center
class: text-center
---

## 1. The Ad

<br>
<br>
<br>
```
- A musician can publish an Ad to sell an instrument.
- An Ad should have a title, an instrument, and a price.
- An Ad can be sold
- An Ad is available to sell until it is sold.
```

---
layout: image-right
image: ../assets/bass.jpg
---

## Entity

<br>
<br>
<br>

<h3 v-click>identity</h3>
<h3 v-click>lifecycle</h3>
<h3 v-click>mutable</h3>

<!--
An entity is an object that has its own identity and a lifecycle.
It is mutable, we can modify its properties during its lifecycle.
However, it is not mutable via public setters.
Modifications must be done via business methods. 
Two entities are equal if they have the same identity.

TIMING: 35 min
-->


---
layout: center
class: text-center
---

## 2. Price

<br>
<br>
<br>
```
- A musician can apply a discount on the price of his ad.
- The discount is a percentage of the price (between 0% and 100%).
- A price cannot be negative.
```

---
layout: image-right
image: ../assets/drums.jpg
---

## Value object

<br>
<br>
<br>

<h3 v-click>defined by its value</h3>
<h3 v-click>immutable</h3>
<h3 v-click>no lifecycle</h3>

<!--

A Value Object is an object defined by its value. Two VOs with the same value are equal.
They are immutable. We cannot modify a VO, we create a new VO with the new value.
They have no lifecycle. They have no identity of their own. They exist only in the context of another entity.

TIMING: 1h
-->


---
layout: center
class: text-center
---

## 3. The proposal

<br>
<br>
<br>
```
- A proposal has a proposed price.
- A proposal is placed by a musician.
- A placed proposal is waiting for a decision.
- A proposal can be accepted or rejected (if in waiting state).
```

<!--

Is the proposal an entity or a VO?

-->

---
layout: image-right
image: ../assets/guitar.jpg
---

## The Aggregate

<br>
<br>
<br>

<h3 v-click>contains VO and/or entities</h3>
<h3 v-click>responsible for its invariants</h3>
<h3 v-click>garantees consistency</h3>
<h3 v-click>defines transactional boundaries</h3>


<!--

An aggregate is a set of VOs and/or entities that form a coherent unit.
The aggregate root responsible for its invariants. It guarantees the consistency of the whole.
It defines transactional and structural boundaries.
It is the only entry point to access the VOs and entities it contains.
No more defensive code to check invariants, the aggregate takes care of it.
Easier to test, easier to maintain.

TIMING: 1h30 min
-->

---
layout: center
class: text-center
---

## 4. Premium user

<br>
<br>
<br>

```
A musician can become a premium user by paying a subscription.
A premium user can publish more ads than a free user.
A basic user can publish up to 3 ads.
```

<!--

Question: can this business rule be implemented in an existing aggregate? Which one? What does it imply?

-->

---
layout: image-left
image: ../assets/mixer.jpg
---

## The Domain Service

<br>
<br>
<br>

<h3 v-click>domain logic across aggregates</h3>
<h3 v-click>no infrastructure</h3>
<h3 v-click>pure function</h3>

<!--
THe domain service is a service that encapsulates business logic that cannot be attached to an entity or a VO.
It often operates on multiple aggregates.
Its methods are pure functions (same signature, same result).
It must not contain infrastructure logic (data access, sending emails, etc...).
In this way it is distinguished from an application service which contains infrastructure logic
and orchestrates calls to domain services, repositories, etc...

TIMING: 1h40 min
-->
