package com.upper.team1726.bankbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.upper.team1726.bankbox.activity.BankSplash;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.ATM;
import com.upper.team1726.bankbox.model.Account;
import com.upper.team1726.bankbox.model.Bank;
import com.upper.team1726.bankbox.model.Branch;
import com.upper.team1726.bankbox.model.Exchange;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class LoadDataTask extends AsyncTask<Void, Void, Void> {


    BankDBHandler db;
    int[] image = {10000, R.drawable.kbz, R.drawable.agd, R.drawable.aya, R.drawable.cb, R.drawable.yoma, R.drawable.meb};
    String[] nameArr = {"", "KBZ", "AGD", "AYA", "CB", "YOMA", "MEB"};
    Context mContext;


    ProgressDialog progressDialog;

    public LoadDataTask(Context context, BankDBHandler db) {
        this.db = db;
        this.mContext = context;
        progressDialog = new ProgressDialog(mContext);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Please wait while fetching data...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected Void doInBackground(Void... voids) {


        db.deleteBankAllData();
        prepareBankData("bankdata.json");

        Log.e("Empty check", "After");
        db.getTownshipCount();
        db.getCityCount();

        db.deletTownshipData();
        prepareTownshipData("township.json");

        db.deleteCityData();
        prepareCityData("city.json");

        db.deleteBranchData();
        prepareBranchData("branch.json");
        db.getBranchCount();

        db.deleteAtmData();
        prepareAtmData("atm.json");

        db.deleteExchangeData();
        prepareExchangeData("exchange.json");


        //for interest data
        db.deleteAccountData();
        db.insertAccount();

        db.deleteInterestData();
        db.insertInterest();

        db.deleteCurrency();
        db.deleteCurrencyType();
        db.deletecurrency_exchange_data();
        db.deleteCentralData();

        db.insertCurrencyType();
        db.insertCurrency();
        // db.insertCurrencyExchange();
        prepareCurrencyExchangeData("currencyexchange.json");
        db.insertLoan();

        prepareAccountData("account_suggestion.json");
        db.insertManualReview();
        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        Intent mainIntent = new Intent(mContext, BankSplash.class);
        mContext.startActivity(mainIntent);
        ((Activity) mContext).finish();


    }


    private void prepareBankData(String fileName) {
        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset(fileName));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);
                int b_id = objJSON.getInt("bank_id");
                String bank_name = objJSON.getString("bank_name").trim().toString();
                String bank_color = objJSON.getString("bank_color").trim().toString();
                BankDBHandler mydb = new BankDBHandler(mContext);
                int index = search(bank_name);
                if (index > 0) {

                    Bank bank = new Bank(b_id, bank_name, index, bank_color);
//
//                    Log.e("bank id222",String.valueOf(b_id));
//                    Log.e("bank name",bank.getName());
//                    Log.e("bank logo",String.valueOf(index));
//                    Log.e("bank color",bank_color);
                    mydb.insertBank(bank);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void prepareTownshipData(String fileName) {
        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset(fileName));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);
                int township_id = objJSON.getInt("FIELD1");
                String township_name = objJSON.getString("FIELD2").trim().toString();
                BankDBHandler mydb = new BankDBHandler(mContext);
                mydb.insertTownshipData(township_id, township_name);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void prepareCityData(String fileName) {
        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset(fileName));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);
                int city_id = objJSON.getInt("FIELD1");
                String city_name = objJSON.getString("FIELD2").trim().toString();
                BankDBHandler mydb = new BankDBHandler(mContext);
                mydb.insertCityData(city_id, city_name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void prepareBranchData(String fileName) {
        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset(fileName));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                String bank_name = objJSON.getString("FIELD1").trim();
                String branch_name = objJSON.getString("FIELD2").trim();
                String address = objJSON.getString("FIELD3").trim();
                String location = objJSON.getString("FIELD4").trim();
                String township = objJSON.getString("FIELD5").trim();
                String city = objJSON.getString("FIELD6").trim();
                String contact = objJSON.getString("FIELD7").trim();
                Log.e("location", location);
                //for finding latitude and longitude
                String[] directory = location.split(",");
                Log.e("Latitude", directory[0]);
                Log.e("longitude", directory[1]);
                Double latitude = Double.parseDouble(directory[0].trim());
                Double longitude = Double.parseDouble(directory[1].trim());
                BankDBHandler db = new BankDBHandler(mContext);
                int city_id = db.findCityIndex(city);
                int township_id = db.findTownshipIndex(township);
                if (city_id > 0 && township_id > 0) {
                    Branch branch = new Branch(bank_name, branch_name, address, contact, latitude, longitude, township_id, city_id);
                    db.insertBranchData(branch);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //prepare Exchange data
    private void prepareExchangeData(String fileName) {
        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset(fileName));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                String bank_name = objJSON.getString("FIELD1").trim();
                String exchange_name = objJSON.getString("FIELD2").trim();
                String address = objJSON.getString("FIELD3").trim();
                String opening_time = objJSON.getString("FIELD4").trim();
                String location = objJSON.getString("FIELD5").trim();
                String township = objJSON.getString("FIELD6").trim();
                String city = objJSON.getString("FIELD7").trim();

                Log.e("location", location);
                //for finding latitude and longitude
                String[] directory = location.split(",");
                Log.e("Latitude", directory[0]);
                Log.e("longitude", directory[1]);
                Double latitude = Double.parseDouble(directory[0].trim());
                Double longitude = Double.parseDouble(directory[1].trim());

                BankDBHandler db = new BankDBHandler(mContext);

                int city_id = db.findCityIndex(city);
                int township_id = db.findTownshipIndex(township);
                if (city_id > 0 && township_id > 0) {
                    Exchange exchange = new Exchange(bank_name, exchange_name, address, opening_time, latitude, longitude, township_id, city_id);
                    db.insertExchangeData(exchange);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //prepare AtmData
    private void prepareAtmData(String fileName) {
        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset(fileName));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                String bank_name = objJSON.getString("FIELD1").trim();
                String atm_name = objJSON.getString("FIELD2").trim();
                String address = objJSON.getString("FIELD3").trim();
                String location = objJSON.getString("FIELD4").trim();
                String township = objJSON.getString("FIELD5").trim();
                String city = objJSON.getString("FIELD6").trim();

                Log.e("location", location);
                //for finding latitude and longitude
                String[] directory = location.split(",");
                Log.e("Latitude", directory[0]);
                Log.e("longitude", directory[1]);
                Double latitude = Double.parseDouble(directory[0].trim());
                Double longitude = Double.parseDouble(directory[1].trim());

                BankDBHandler db = new BankDBHandler(mContext);

                int city_id = db.findCityIndex(city);
                int township_id = db.findTownshipIndex(township);
                if (city_id > 0 && township_id > 0) {
                    ATM atm = new ATM(bank_name, atm_name, address, latitude, longitude, township_id, city_id);
                    db.insertAtmData(atm);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //prepare currency exchange
    private void prepareCurrencyExchangeData(String fileName) {
        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset(fileName));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                int currency_id = objJSON.getInt("FIELD1");
                String date = objJSON.getString("FIELD2").trim();
                double buy = objJSON.getDouble("FIELD3");
                double sell = objJSON.getDouble("FIELD4");

                BankDBHandler db = new BankDBHandler(mContext);
                db.insertCurrencyExchange(currency_id, date, buy, sell);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //prepare Account data
    private void prepareAccountData(String fileName) {
        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset(fileName));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                int acc_id = objJSON.getInt("FIELD1");
                String bank_name = objJSON.getString("FIELD2").trim();
                String acc_name = objJSON.getString("FIELD3").trim();
                String purpose = objJSON.getString("FIELD4").trim();
                String initial_deposit = objJSON.getString("FIELD5").trim();
                String type = objJSON.getString("FIELD6").trim();
                String deposit_time = objJSON.getString("FIELD8").trim();
                String withdraw_time = objJSON.getString("FIELD9").trim();
                String description = objJSON.getString("FIELD10").trim();

                BankDBHandler db = new BankDBHandler(mContext);
                Account account = new Account(acc_id, bank_name, acc_name, purpose, initial_deposit, type, deposit_time, withdraw_time, description);
                db.insertAccountSuggestionData(account);
                Log.e("STATUS", "ACcount Suggestion inserted");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream input = mContext.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            Log.d("Bank data", stringBuilder.toString());
            return stringBuilder.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    //for search image index
    public int search(String name) {
        for (int i = 0; i <= nameArr.length; i++) {
            if (nameArr[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }


}




