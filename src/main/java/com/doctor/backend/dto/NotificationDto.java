package com.doctor.backend.dto;

import com.doctor.backend.model.Notification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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

    private String createdAt;

    public static Notification toEntity(NotificationDto notificationDto) {
        if (null == notificationDto)
            return null;
        return new Notification(notificationDto.getId(), notificationDto.getTitle(), notificationDto.getMessage(), notificationDto.isRead, notificationDto.getCreatedAt());
    }

    public static NotificationDto fromEntity(Notification notification) {
        if (notification == null)
            return null;
        return new NotificationDto(notification.getId(), notification.getTitle(), notification.getMessage(), notification.getIsRead(), notification.getCreatedAt());
    }
}
