package com.zemoga.challenge.profileApi.application.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TwitterDTO {
    long id;
    String text;
}
