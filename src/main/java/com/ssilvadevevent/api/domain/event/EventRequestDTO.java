package com.ssilvadevevent.api.domain.event;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventRequestDTO(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Long date,
        String city,
        String state,
        @NotNull Boolean remote,
        @NotBlank String eventUrl,
        @NotBlank String imageUrl) {
}
