package com.example.finalproto.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RelationDTO {
    private long rel_No;

    private String send;
    private String accept;


}
