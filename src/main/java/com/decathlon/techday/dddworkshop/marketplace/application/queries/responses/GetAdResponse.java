package com.decathlon.techday.dddworkshop.marketplace.application.queries.responses;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import java.util.Optional;

public record GetAdResponse(Optional<Ad> maybeAd) {

}
