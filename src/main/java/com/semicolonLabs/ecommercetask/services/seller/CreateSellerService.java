package com.semicolonLabs.ecommercetask.services.seller;


import com.semicolonLabs.ecommercetask.data.models.Seller;
import com.semicolonLabs.ecommercetask.dtos.request.SellerAccountCreationRequest;
import com.semicolonLabs.ecommercetask.exceptions.RegistrationException;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import com.semicolonLabs.ecommercetask.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateSellerService {


    private final SellerService sellerService;


    private final ModelMapper modelMapper;

    public ApiResponse createSellerAccount(SellerAccountCreationRequest sellerAccountCreationRequest) throws RegistrationException {

        System.out.println("I entered here ");
        var isRegistered = sellerService.findByEmail(sellerAccountCreationRequest.getEmailAddress()).isPresent();
        if(isRegistered) throw new RegistrationException(GenerateApiResponse.ACCOUNT_ALREADY_EXIST);
        buildSeller(sellerAccountCreationRequest);
        return GenerateApiResponse.created(GenerateApiResponse.ACCOUNT_CREATED_SUCCESSFULLY);
    }


    private void buildSeller(SellerAccountCreationRequest sellerAccountCreationRequest){
        Seller seller = modelMapper.map(sellerAccountCreationRequest, Seller.class);
        var savedSeller =  sellerService.saveSeller(seller);
        System.out.println("I'm the saved seller email address : " + savedSeller.getEmailAddress());
        System.out.println("I'm the saved seller : " + savedSeller);
    }

}
