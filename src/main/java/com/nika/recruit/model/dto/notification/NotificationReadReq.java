package com.nika.recruit.model.dto.notification;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class NotificationReadReq {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

}
