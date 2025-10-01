package com.decathlon.techday.dddworkshop.musician.infrastructure.rest;

import java.util.Date;

public record ErrorResponse(Date timestamp,
                            String path,
                            int status,
                            String error,
                            String error_description) {

}
