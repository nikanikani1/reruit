package com.nika.recruit.service.event;

import lombok.Value;

@Value
public class SaveResumeEvent {
    Long resumeId;
    Long userId;
}
