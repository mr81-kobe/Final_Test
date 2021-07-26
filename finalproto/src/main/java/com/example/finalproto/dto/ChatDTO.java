package com.example.finalproto.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatDTO {

    private Long No;
    private String message;
    private String to;
    private String from;
    private String time;


}
