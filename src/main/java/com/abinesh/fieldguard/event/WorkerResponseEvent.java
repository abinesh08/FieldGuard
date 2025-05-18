package com.abinesh.fieldguard.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerResponseEvent {
    private Long id;
    private String name;
    private String place;
    private String subTaluk;
    private String taluk;
    private String district;
    private String state;
    private String address;
    private Long phoneNumber;
}
