package com.niladri.Online.food.ordering.dto.response.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Error response",description = "Error response details")
public class ErrorResponseDto {
	@Schema(description = "API path", example = "/api/v1/accounts")
	private String apiPath;
	@Schema(description = "Error message", example = "Internal server error")
	private String errorMessage;
	@Schema(description = "Error code", example = "500")
	private HttpStatus errorCode;
	@Schema(description = "Error time", example = "2021-09-01T10:20:30")
	private LocalDateTime errorTime;
}
