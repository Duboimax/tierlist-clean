package fr.duboimax.cleanarchi.application.dtos.responses;


public record ApiErrorResponse(
        int status,
        ErrorDetails error
) {
    public record ErrorDetails(String message) {}

    public static ApiErrorResponse error(int status, String message) {
        return new ApiErrorResponse(status, new ErrorDetails(message));
    }
}
