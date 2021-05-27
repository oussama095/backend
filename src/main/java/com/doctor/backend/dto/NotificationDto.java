package com.doctor.backend.dto;

import com.doctor.backend.model.Notification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationDto {

    private Long id;

    private String title;

    private String message;

    private Boolean isRead;

    public static Notification toEntity(NotificationDto notificationDto) {
        if (null == notificationDto)
            return null;
        return new ModelMapper().map(notificationDto, Notification.class);
    }

    public static NotificationDto fromEntity(Notification notification) {
        if (notification == null)
            return null;
        return new ModelMapper().map(notification, NotificationDto.class);
    }

    public static List<NotificationDto> fromEntities(List<Notification> notifications) {
        if (notifications == null)
            return new ArrayList<>();
        return notifications.stream().map(NotificationDto::fromEntity).collect(Collectors.toList());
    }

    public static List<Notification> toEntities(List<NotificationDto> notifications) {
        if (notifications == null)
            return  new ArrayList<>();
        return notifications.stream().map(NotificationDto::toEntity).collect(Collectors.toList());
    }
}
