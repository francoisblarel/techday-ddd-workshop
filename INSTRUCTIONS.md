# DDD workshop - TODO NAME

**Duration**: 2H
**Goal**:practice DDD concepts by modeling a simplified version of an Ad website

--- 

## 🔎 Introduction

In this kata, we will progressively build a domain model around **Ads**.  
The goal is not to build a full system, but to practice 4 core DDD concepts:

- **Value Object**
- **Entity**
- **Aggregate**
- **Domain Service**

We will move step by step, enforcing simple business rules at each stage.

---

## 1 – Value Objects

### Business Rules

- An **Ad** must have a **Price**.
- The price must be strictly positive.
- The price includes a currency (at least **EUR** supported).

### Exercise

1. Allow to apply a discount on an Ad

- Implement a `Price` class that encapsulates `amount` + `currency`.
  *ici dans le use case, on va passer par un setter sur l'entity Ad,*
  *mais en vrai on ferait plutot une methode applyDiscount sur l'entity Ad qui creerait un nouveau Price avec le bon*
  *montant*
- Enforce invariants (amount > 0). *Passer par un property test pour expliquer la propriété*

- _Question_: what difference does it make if the Price is not immutable?_

- Two prices with the same values must be equal (value-based equality).


**Wrap-up:**
**- VO encapsulates business rules**
**- VO is defined by its values, not identity.**
**- VO is immutable**


---

## Step 2 – Entity: Ad

### Business Rules

- An **Ad** has a unique identifier.
- It has: `Name`, `Price`, `Description`.
- It has a **status**: `Draft`, `Published`, `SoldOut`
- an Ad always starts with the `Draft` status.
- An Ad can only be published if it has a valid title and a positive price.
- When publishing an Ad, you can associate a quantity

### Exercise

1. Exercise: move the applyDiscount logic from the use case to the Ad entity.
   **l'entity est responsable de l'integrité de son état, pas le use case**

2. Implement a `publish` method that enforces the publishing rules.
   **On ne créé pas d'instance spécifique pour les tests, on passe par les méthodes de l'entity pour arriver à l'état
   voulu et garder une cohérence.**
   **le all-args ne va être utilisé que par le repo**

**Wrap-up: introduce identity and lifecycle.**
**Wrap-up: responsibilities.**

---

## Step 3 – Aggregate (Ad + Photos)

### Business Rules

- An **Ad** contains a collection of **Photos**.
- A photo is a **Value Object** `PhotoUrl`, validated for proper URL format.
- A **Published Ad** must contain at least 1 photo.

### Exercise

1. Implement `PhotoUrl`.
2. Add a list of photos inside `Ad`.
3. Extend `publish()` to prevent publishing if the Ad has no photo.

**Goal: introduce the Aggregate Root and transactional consistency within the boundary.**

---

## Step 4 – Enriched Aggregate (Ad + Questions)

### New Entity

- `Question` (Entity dependent on the Ad) with:
  - unique `id` (generated locally within the Ad)
  - `text` (non-empty, max 500 characters)
  - `author` (e.g. email or userId, could be a VO)
  - `date`

### Business Rules

- An `Ad` can receive multiple `Questions`.
- A `Question` always belongs to exactly one `Ad`.
- **Transactional invariant**:
  - You can add a question only if the Ad is `Published`.
  - You cannot add questions if the Ad is `Archived`.

### Exercise

- Implement the `Question` class.
- Add a collection of questions inside `Ad`.
- Implement `addQuestion(question)` in `Ad`, enforcing the above rules.

**Goal: show that an Aggregate can contain multiple Entities and enforce consistency across them.**

---

## Step 5 – Domain Service: Publishing an Ad

### Business Rules

- A **User** (separate Entity) cannot have more than 3 published ads in the free version.
- This rule involves multiple Ads → it cannot be enforced *inside the Ad Aggregate*.

### Exercise

1. Implement a `User` class with an identifier.
2. Create an `AdPublicationService` that:

- receives a `User` and an `Ad`
- checks the quota of published ads
- calls `Ad.publish()` only if the rule is respected.

---

## 🗣 Final Discussion

- Why do `Photo` and `Question` belong to the `Ad` Aggregate?
- Why is `User` separate and enforced through a Domain Service?
- What defines the **transactional boundary** of an Aggregate?

**Goal: isolate a business rule that spans across multiple Aggregates.**

---

