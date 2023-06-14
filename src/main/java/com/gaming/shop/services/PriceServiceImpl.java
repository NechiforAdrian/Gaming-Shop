package com.gaming.shop.services;

import com.gaming.shop.utils.constants.Constants;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService{

    @Override
    public double setPriceProductsTier1() {
        //TO DO SOME IMPL

        return Constants.FINAL_PRICE_TIER1;
    }
}