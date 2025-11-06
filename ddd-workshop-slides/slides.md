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
On a pensé à une application révolutionnaire pour les musiciens : 
Une plateforme ou chaque musicien peut exposer son studio avec tous ses instruments. Montrer ses réglages, 
ses configurations, ses effets, ses amplis, etc.

Mais ce n'est pas tout, il y a aussi une partie marketplace qui permet aux musiciens de vendre leurs instruments d'occasion entre eux via des annonces.
Et cette appli, on l'a appelé...
-->

---
src: ./pages/application.md
hide: false
---

---
src: ./pages/atelier.md
hide: false
---

---
src: ./pages/synthese.md
hide: false
---

---
layout: center
class: text-center
---

## Thank you

[Resources](https://github.com/francoisblarel/techday-ddd-workshop/blob/main/RESOURCES.md) · [GitHub](https://github.com/francoisblarel/techday-ddd-workshop)
· [Channel Slack DDD](https://decathlondigital.slack.com/archives/C091F769QP2)

<br><br>
<div>Bastien Terrier / François Blarel</div>


<div class="source">image bank: https://pixabay.com/</div>
