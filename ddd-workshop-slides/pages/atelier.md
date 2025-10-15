---
layout: center
class: text-center
---

# Hands-on

<!--
On va rentrer dans la partie tactique. Et plutôt que de présenter tous les concepts un par un, 
on va partir d'une approche naïve afin de les faire émerger au fur et à mesure.
-->
---
layout: center
class: text-center
---

# Scope and limitations

<!--
On va partir de certains use cases simples de l'application afin de mettre en lumière les différents patterns.

On a un temps limité, on va donc sciemment laisser certains aspects de côté. Notamment la partie infrastructure.
-->

---
layout: center
class: text-center
---

# Architecture

<!--
présentation rapide de l'architecture proposée
-->

---
layout: center
class: text-center
---

# 1. The Ad

<br>
```
A musician can publish an ad to sell an instrument.
He defines a price for his instrument when publishing the ad.
He can pause an ad.
An ad has a title, a description, a price.
```

---
layout: center
class: text-center
---

# 2. Price

<br>
```
A musicien can apply a discount on the price of his ad.
The discount is a percentage of the price.
```

---
layout: center
class: text-center
---

# 🎉 Value object 🎉

<div v-click>defined by its value</div>
<div v-click>immutable</div>
<div v-click>no lifecycle</div>

<!--
Un Value Object est un objet défini par sa valeur. Deux VO avec la même valeur sont égaux.

Ils sont immuables. On ne peut pas modifier un VO, on crée un nouveau VO avec la nouvelle valeur.

Ils n'ont pas de cycle de vie. Ils n'ont pas d'identité propre. Ils existent uniquement dans le contexte d'une autre entité.
-->


---
layout: center
class: text-center
---

# 3. The proposition

<br>
```
A musician can propose a price for an instrument he wants to buy.
A musician can only make one proposal per ad.
The selling musician can accept or refuse the proposal.
```
---
layout: center
class: text-center
---

# 🎉 The Aggregate 🎉

<div v-click>contains VO and/or entities</div>
<div v-click>responsible for its invariants</div>
<div v-click>garantees consistency</div>
<div v-click>defines transactional boundaries</div>


<!--
Un aggregate est un ensemble de VO et/ou d'entités qui forment une unité cohérente.

Il est responsable de ses invariants. C'est lui qui garantit la cohérence de l'ensemble.

Il définit les frontières transactionnelles et structurelles.

Il est le seul point d'entrée pour accéder aux VO et entités qu'il contient.

Plus besoin de code défensif pour vérifier les invariants, c'est l'aggregate qui s'en charge.

Plus facile à tester, plus facile à maintenir.

-->

---
layout: center
class: text-center
---

# 4. Premium user

<br>
```
A musician can become a premium user by paying a subscription.
A premium user can publish more ads than a free user.
A basic user can publish up to 3 ads.
```

---
layout: center
class: text-center
---

# 🎉 The Domain Service 🎉

<div v-click>domain logic across aggregates</div>
<div v-click>no infrastructure</div>
<div v-click>pure function</div>

<!--
Un domain service est un service qui encapsule une logique métier qui ne peut pas être rattachée à une entité ou un VO.

Il opère souvent sur plusieurs agrégats.

Ses méthodes sont des fonctions pures (même signature, même résultat).

Il ne doit pas contenir de logique d'infrastructure (accès aux données, envoi de mails, etc...).

En celà il se distingue d'un application service (ou service d'application) qui lui contient de la logique d'infrastructure 
et qui va orchestrer les appels aux domain services, aux repositories, etc...

-->
