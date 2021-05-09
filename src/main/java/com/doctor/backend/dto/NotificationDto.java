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

    public NotificationDto(String title, String message) {
        this.title = title;
        this.message = message;
        this.isRead = false;
    }

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
}
