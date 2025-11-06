package com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.UUID;

public record RejectAdProposalCommand(UUID adId, MusicianId musicianId) {

}
