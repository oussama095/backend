package com.doctor.backend.dto;

import com.doctor.backend.model.Address;
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
public class AddressDto {

    private String street1;

    private String street2;

    private String city;

    private Integer postalCode;

    private String country;

    public static Address toEntity(AddressDto addressDto) {
        if (null == addressDto)
            return null;
        return new ModelMapper().map(addressDto, Address.class);
    }

    public static AddressDto fromEntity(Address address) {
        if (address == null)
            return null;
        return new ModelMapper().map(address, AddressDto.class);
    }
}
