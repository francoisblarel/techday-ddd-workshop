---
# try also 'default' to start simple
theme: default
# random image from a curated Unsplash collection by Anthony
# like them? see https://unsplash.com/collections/94734566/slidev
background: https://cover.sli.dev
# some information about your slides (markdown enabled)
title: Tech days 2025 - DDD Workshop
info: |
  ## Slides from the DDD Workshop

# apply UnoCSS classes to the current slide
class: text-center
# https://sli.dev/features/drawing
drawings:
  persist: false
# slide transition: https://sli.dev/guide/animations.html#slide-transitions
transition: slide-left
# enable MDC Syntax: https://sli.dev/features/mdc
mdc: true

---

# DDD Hands-on

Decathlon Tech days 2025

<div>
<a href="https://urls.fr/4dzer_">https://urls.fr/4dzer_</a>
</div>

<div class="abs-br m-6 text-xl">
  <button @click="$slidev.nav.openInEditor()" title="Open in Editor" class="slidev-icon-btn">
    <carbon:edit />
  </button>

</div>


 <style>
  h2 {
    display: inline-block;
    background-color: #222; 
    color: white;
    padding: 0.8rem 2rem;
    font-size: 2.5rem; 
    font-weight: 900; 
    text-transform: uppercase; 
    letter-spacing: 0.05em;
    clip-path: polygon(
        2% 0%,   /* Point en haut à gauche */
        93% 5%,  /* Point en haut à droite */
        100% 25%, /* Point milieu-droit (haut) */
        100% 79%, /* Point milieu-droit (bas) */
        85% 98%, /* Point en bas à droite */
        5% 100%,  /* Point en bas à gauche */
        0% 75%,  /* Point milieu-gauche (bas) */
        0% 21%   /* Point milieu-gauche (haut) */
    );
  }
  .source {
    position: fixed;
    bottom: 0rem;
    right: 1rem;
    font-size: 0.8rem;
    color: black;
    opacity: 0.6;
  }
  </style>

<!--
We thought about a revolutionary application for musicians:
A platform where each musician can showcase his studio with all his instruments. Show his settings,
his configurations, his effects, his amps, etc.

But that's not all, there is also a marketplace part that allows musicians to sell their used instruments to each other via ads.
-->

---
src: ./pages/application-en.md
hide: false
---

---
src: ./pages/atelier-en.md
hide: false
---

---
src: ./pages/synthese-en.md
hide: false
---

---
layout: center
class: text-center
---

## Thank you

[Resources](https://github.com/francoisblarel/techday-ddd-workshop/blob/main/RESOURCES.md) · [GitHub](https://github.com/francoisblarel/techday-ddd-workshop)
· [Channel Slack DDD](https://decathlondigital.slack.com/archives/C091F769QP2)


<div class="source">image bank: https://pixabay.com/</div>
