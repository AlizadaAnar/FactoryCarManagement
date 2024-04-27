package com.company.CarFactoryManagement.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactoryRequestDto {


    private String name;
    private String location;

}
