package com.mikhalenok.monitor.statistics.presentation.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionListRs {
    private String logref = "structured_error";
    private List<ExceptionStructuredRs> errors;

    public ExceptionListRs(List<ExceptionStructuredRs> errors) {
        this.logref = "structured_error";
        this.errors = errors;
    }
}