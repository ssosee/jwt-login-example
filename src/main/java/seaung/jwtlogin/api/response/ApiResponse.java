package seaung.jwtlogin.api.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse<T> {
    private String message;
    private HttpStatus status;
    private T data;

    public ApiResponse(String message, HttpStatus status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(String message, HttpStatus status, T data) {
        return new ApiResponse<>(message, status, data);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.of(HttpStatus.OK.name(), HttpStatus.OK, data);
    }
}
