package com.sawtooth.models.exceptions;

import java.util.Date;

public record AppError(int status, String message, Date timestamp) {
}
