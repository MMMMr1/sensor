package com.mikhalenok.monitor.sensors.presentation.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionStructuredRs {
    private String field;
    private String message;

    public ExceptionStructuredRs(String message) {
        this.field = "structured_error";
        this.message = message;
    }
}
