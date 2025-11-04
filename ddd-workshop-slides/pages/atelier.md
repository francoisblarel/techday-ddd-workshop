---
layout: center
class: text-center
---

## Hands-on

<!--
On va rentrer dans la partie tactique. Et plutôt que de présenter tous les concepts un par un, 
on va partir d'une approche naïve afin de les faire émerger au fur et à mesure.
-->
---
layout: center
class: text-center
---

## Scope and limitations

<!--
On va partir de certains use cases simples de l'application afin de mettre en lumière les différents patterns.

On a un temps limité, on va donc sciemment laisser certains aspects de côté. Notamment la partie infrastructure.
-->

---
layout: center
class: text-center
---

## Architecture

<!--
présentation rapide de l'architecture proposée

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
Une entité est un objet qui a une identité propre et un cycle de vie.

Elle est mutable, on peut modifier ses propriétés au cours de son cycle de vie.
Par contre, elle n'est pas mutable via des setters publics. 
Les modifications doivent se faire via des méthodes métier.

Deux entités sont égales si elles ont la même identité.

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
Un Value Object est un objet défini par sa valeur. Deux VO avec la même valeur sont égaux.

Ils sont immuables. On ne peut pas modifier un VO, on crée un nouveau VO avec la nouvelle valeur.

Ils n'ont pas de cycle de vie. Ils n'ont pas d'identité propre. Ils existent uniquement dans le contexte d'une autre entité.

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

Poser la question: proposal est-il une entité ou un VO?

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
Un aggregate est un ensemble de VO et/ou d'entités qui forment une unité cohérente.

Il est responsable de ses invariants. C'est lui qui garantit la cohérence de l'ensemble.

Il définit les frontières transactionnelles et structurelles.

Il est le seul point d'entrée pour accéder aux VO et entités qu'il contient.

Plus besoin de code défensif pour vérifier les invariants, c'est l'aggregate qui s'en charge.

Plus facile à tester, plus facile à maintenir.

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

Question: cette règle métier peut-elle être implémentée dans un aggregat existant? Lequel?
Qu'est-ce que ça implique?

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
Un domain service est un service qui encapsule une logique métier qui ne peut pas être rattachée à une entité ou un VO.

Il opère souvent sur plusieurs agrégats.

Ses méthodes sont des fonctions pures (même signature, même résultat).

Il ne doit pas contenir de logique d'infrastructure (accès aux données, envoi de mails, etc...).

En celà il se distingue d'un application service (ou service d'application) qui lui contient de la logique d'infrastructure 
et qui va orchestrer les appels aux domain services, aux repositories, etc...

TIMING: 1h40 min
-->
