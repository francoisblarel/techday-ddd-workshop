package com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands;

import java.util.UUID;

public record ApplyAdDiscountCommand(UUID adId, float discount) {

}
