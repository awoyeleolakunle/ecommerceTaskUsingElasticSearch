package com.semicolonLabs.ecommercetask.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreCreationRequest {
    private String sellerEmailAddress;
    private String storeName;

}
