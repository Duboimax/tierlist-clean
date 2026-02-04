package fr.duboimax.cleanarchi.application.dtos.responses;

public record ApiResponse<T>(
        int status,
        T data
) {
    public static <T> ApiResponse<T> success(int status, T data) {
        return new ApiResponse<>(status, data);
    }
}
