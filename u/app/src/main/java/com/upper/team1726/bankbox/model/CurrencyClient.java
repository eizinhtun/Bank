package com.upper.team1726.bankbox.model;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by MICRO on 11/13/2017.
 */

public interface CurrencyClient {

    @GET("latest")
    Call<CentralCurrency> loadCurrencies();
}
