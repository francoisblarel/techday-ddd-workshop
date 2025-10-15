---
layout: center
class: text-center
---

# Wrap-up

---
layout: center
class: text-center
---

<div v-click>Value Object</div>
<div v-click>Aggregate</div>
<div v-click>Domain Service</div>
<br>
<div v-click>Domain Event</div>

---
layout: center
class: text-center
---

### Domain Driven Design

<br>
<div v-click>Not a framework...</div>
<div v-click>more like a toolbox</div>
<div v-click>not only code</div>

<!--
TODO: montrer le resultat d'un event storming sur ce domaine

On remontre l'event storming et le fait que tout était déjà là.
L'importance des patterns stratégiques pour bien comprendre le domaine avant de se lancer dans la solution.

On a rendu explicite tout ce qui était implicite dans nos têtes.

-->
---
layout: center
class: text-center
---

### Warnings

<br>
<div v-click>Not a silver bullet</div>
<div v-click>somethimes CRUD is more relevant</div>
<div v-click>easy to understand hard to master</div>
<div v-click>iterative</div>
<div v-click>be careful of the "by the book"</div>

<!--

-->
---
layout: center
class: text-center
---

# What about legacy code?

<br>
<div v-click>use value object</div>
<div v-click>move invariants</div>
<div v-click>identify and isolate bounded contexts</div>

<!--
Comment migrer un legacy vers du DDD ?

On n'est pas obligé d'utiliser tous les patterns DDD.
- Commencer par les plus simples: les value objects.

- Est-ce possible de déplacer les invariants métier dans les entités?

- Bien identifier les Bounded Contexts
- Isoler les Bounded Contexts
- strangler pattern
-->

---
layout: center
class: text-center
---


![strangler-pattern.jpg](../assets/strangler-pattern.jpg)


---
layout: center
class: text-center
---

# Strangler pattern

```mermaid {theme: 'neutral', scale: 0.8}
graph TD
    A(Outside world) --> B[Strangler proxy]
    B <--> D[Legacy System]
    B <--> E[new System]
```

---
layout: center
class: text-center
---

# Last words

<br>

<div v-click><i>Take care of your model if you don't want to eat your cat's sh*t</i></div>
