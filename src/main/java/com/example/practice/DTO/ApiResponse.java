package com.example.practice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ApiResponse<T> implements Serializable {
	private static final long serialVersionUID = -2438014012950414283L;

	private String code;
    private String message;
    private String additionalMessage;
    private Date responseTime;
    private List<FieldError> errors;

    private T body;

    public static ApiResponse<Void> create() {
        return create(null);
    }

    public static <T> ApiResponse<T> create(T body) {
        return ApiResponse.<T>builder()
                .body(body)
                .build();
    }

    /**
     * @author Tornado 21030113 track remote invoke message
     */
    @Override
    public String toString() {
        return "ApiResponse [code=" + code + ", message=" + message + ", additionalMessage=" + additionalMessage + "]";
    }

    /**
     * @param title
     * @return
     * @author Tornado 21030113 track remote invoke message
     */
    public String toString(String title) {
        return title + ":[code=" + code + ", message=" + message + ", additionalMessage=" + additionalMessage + "]";
    }

    @Getter
    @Builder
    public static class FieldError {
        private String field;
        private String value;
        private String message;

        private FieldError(String field, String value, String message) {
            this.field = field;
            this.value = value;
            this.message = message;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());

        }
        public static List<FieldError> of(MissingServletRequestParameterException e) {
            List<FieldError> fieldErrorList = new ArrayList<>();
            fieldErrorList.add(FieldError.builder()
                    .field(e.getParameterName())
                    .value("")
                    .message(e.getMessage())
                    .build());
            return fieldErrorList;
        }

        public static List<FieldError> of(MissingRequestHeaderException e) {
            List<FieldError> fieldErrorList = new ArrayList<>();
            fieldErrorList.add(FieldError.builder()
                    .field(e.getHeaderName())
                    .value("")
                    .message(e.getMessage())
                    .build());
            return fieldErrorList;
        }
    }
}
