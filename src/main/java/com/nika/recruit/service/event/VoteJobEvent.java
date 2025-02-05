package com.nika.recruit.service.event;


import lombok.Value;

@Value
public class VoteJobEvent {
    Long userId;
    Long jobId;
}
