package com.mikhalenok.monitor.sensors.presentation.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionErrorRs {
    private String logref = "error";
    private String message;

    public ExceptionErrorRs(String message) {
        this.logref = "error";
        this.message = message;
    }
}