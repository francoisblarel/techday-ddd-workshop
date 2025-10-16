package com.decathlon.techday.dddworkshop.configuration.rest;

import java.util.Date;

public record ErrorResponse(Date timestamp,
                            String path,
                            int status,
                            String error,
                            String error_description) {

}
