package com.example.cmi_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAccountCreationRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
