## Exercise 4: Introducing the premium feature

Consider that we want to introduce a premium feature for Ads.

```
- A musician can only post up to 3 ads unless he has a premium reputation.
```

The reputation feature has already been implemented in the `Musician` bounded context.

As the rule involves two aggregates (Ad and Musician), we need to find a way to enforce this rule without violating the
principles of DDD.

Add a domain service `AdPostingService` with a method `canPostAd(MusicianId)` that checks if a musician can post a new
Ad based on their reputation and the number of existing Ads.

