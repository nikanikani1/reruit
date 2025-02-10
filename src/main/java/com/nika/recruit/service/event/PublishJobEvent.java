package com.nika.recruit.service.event;


import lombok.Value;

/**
 * @author ht
 */
@Value
public class PublishJobEvent {
    Long userId;
    Long jobId;
}
