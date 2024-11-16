package com.ssilvadevevent.api.domain.event;

import org.springframework.web.multipart.MultipartFile;

public record EventRequestDTO(
        String title,
        String description,
        Long date,
        String city,
        String state,
        Boolean remote,
        String eventUrl,
        String imageUrl) {

}
