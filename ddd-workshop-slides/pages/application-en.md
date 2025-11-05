---
layout: center
class: text-center
---

![backStage.png](../assets/backStage.png)

<!--

As we already have a great name, we've done 90% of the work.
What we propose to you today is to do the remaining 10% together, using Domain-Driven Design methodologies.

-->

---
layout: image-right
image: ../assets/mixer.jpg
---

## Business concepts ?

<br>
<br>

### <v-click> Musician</v-click>

### <v-click> Instrument</v-click>

### <v-click> Studio</v-click>

### <v-click> Marketplace</v-click>

### <v-click> Ad</v-click>

### <v-click> to apply a discount</v-click>

### <v-click> make a price proposition</v-click>

### <v-click> place an alert</v-click>

### <v-click>pay</v-click>

<!--
What are the important business concepts we retain?

Musician
Studio
Instrument
Ad
Price
make a price proposal
Alert
apply a discount
-->
--- 
class: text-center
layout: center
---

## UBIQUITOUS LANGUAGE

<!--
What we just did is start to build a common language between us, developers, and business experts.

The ubiquitous language.
-->

--- 
layout: image
image: ../assets/projet-informatique.jpg
backgroundSize: contain
---

<!--
What we want is to remove all ambiguities about business vocabulary.
To have precise terms, shared, and understood by all.

The trap is to have terms that mean several things, depending on the people we talk to.

And as developers, we have a big responsibility on this vocabulary,
because we tend to introduce technical terms (dev bias) that don't make sense to business experts.
-->

--- 
class: text-center
layout: center
---

![ubiquitous-language.png](../assets/ubiquitous-language.png)

<!--
We want to build a shared mental model with everyone and in the code.

But what is a model?
-->


---
layout: image
image: ../assets/paris.jpg
backgroundSize: contain
---

---
layout: image
image: ../assets/paris-map.jpg
backgroundSize: contain
---

---
layout: image
image: ../assets/paris-metro.png
backgroundSize: contain
---

---
layout: quote
---

# "All the models are wrong, but some are useful"

George E. P. Box

<!--
What we want is a simple model, useful in a given context.

Because if we want to make a model that covers the entire business domain,
we will end up with a God Class.
-->

---
class: text-center
layout: center
---

![bounded-context.jpg](../assets/bounded-context.jpg)

<!--
We want to avoid this...

-->


---
class: text-center
layout: center
---

## Bounded Context

<!--
And it's perfect, because DDD gives us the notion of Bounded Context for that.

A bounded context is an explicit boundary around a ubiquitous language via a specific model.

It will allow us to define boundaries within which a specific model applies.

But how to find these boundaries?
-->


---
class: text-center
layout: center
---

## Sub-domains

<!--
The sub-domains will help us structure our business domain.

A sub-domain is a part of the business domain that has a specific responsibility.
A set of coherent business capabilities.

What are the sub-domains in our application?

Studio
Marketplace
Musician accounts
notifications
Alerting
Payments
-->

---
class: text-center
layout: image-left
image: ../assets/guitar.jpg
---

## Sub-domains typologies

<br><br>
<h3 v-click>Core Domain</h3>
<h3 v-click>Supporting Subdomain</h3>
<h3 v-click>Generic Subdomain</h3>

<!--
We can cut the business domain into sub-domains.
[click] The Core Domain is the main sub-domain, the one that brings differentiating value to the company.

[click] The supporting Subdomain is a sub-domain that brings value, but is not differentiating. (ex: a product catalog)

[click] The generic Subdomain is a sub-domain that does not bring differentiating value, and can be outsourced. (ex: notification management, payment management, etc...)
-->

---
layout: image
image: ../assets/core-domain-chart.jpg
backgroundSize: 65% 90%
---
<div v-click style="position: fixed;right:300px;top:100px">MARKETPLACE</div>
<div v-click style="position: fixed;right:300px;bottom:100px">STUDIO</div>
<div v-click style="position: fixed;left:420px;top:200px">ALERTING</div>
<div v-click style="position: fixed;left:315px;top:120px">PAYMENT</div>

<div class="source">source: https://ddd-crew.github.io/ddd-starter-modelling-process/</div>

<!--
Here what is our core-domain?
What are the supporting subdomains?
What are the generic subdomains?

Thanks to this breakdown, we will be able to align the bounded contexts.
The alignment between sub-domains and bounded contexts is not necessarily 1:1, but it is often the case.

It's an important architectural choice to make upstream of the project.
-->


---
layout: image-right
image: ../assets/mic.jpg
---

## Strategic patterns

<!--

All that we have talked about so far (ubiquitous language, bounded contexts, sub-domains), is part of strategic patterns.
It's one of the pillars, if not the most important pillar of DDD.
There are many things to explore in this area, and there are many possible workshops, such as for example Event Storming.

-->

---
layout: image
image: ../assets/event-storming.png
backgroundSize: contain
---


---
layout: image-left
image: ../assets/amp.jpg
---

## Tactical patterns

<br>
<br>
<h3 v-click>Entities</h3>
<h3 v-click>Value objects</h3>
<h3 v-click>Aggregates</h3>
<h3 v-click>Domain service</h3>
<h3 v-click>Domain events</h3>

<!--

But we have chosen today to focus much more extensively on tactical patterns.
Because it is often the entry point for developers into DDD, and the conference is code-oriented.
Now it is important to understand that DDD is not limited to tactical patterns, and that on the contrary
DDD begins with strategic patterns (no tactics without strategy).

-->
