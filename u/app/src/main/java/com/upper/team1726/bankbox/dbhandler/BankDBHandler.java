package com.upper.team1726.bankbox.dbhandler;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.PointF;
import android.util.Log;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.model.ATM;
import com.upper.team1726.bankbox.model.Account;
import com.upper.team1726.bankbox.model.Bank;
import com.upper.team1726.bankbox.model.Branch;
import com.upper.team1726.bankbox.model.Currency;
import com.upper.team1726.bankbox.model.Exchange;
import com.upper.team1726.bankbox.model.Review;

import java.util.ArrayList;


/**
 * Created by UCSM on 10/18/2017.
 */

public class BankDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 13;
    private static final String DATABASE_NAME = "db_bank.db";
    private static final String TABLE_BANK = "tb_bank";
    private static final String TABLE_BRANCH = "tb_branch";
    private static final String TABLE_ATM = "tb_atm";
    private static final String TABLE_EXCHANGE = "tb_exchange";
    private static final String TABLE_CURRENCY_TYPE = "tb_currency_type";
    private static final String TABLE_CURRENCY_EXCHANGE = "tb_currency_exchange";
    private static final String TABLE_CURRENCY = "tb_currency";
    private static final String TABLE_REVIEW = "tb_review";
    private static final String TABLE_CITY = "tb_city";
    private static final String TABLE_TOWNSHIP = "tb_township";
    private static final String TABLE_ACCOUNT = "tb_account";
    private static final String TABLE_INTEREST = "tb_interest";
    private static final String TABLE_CENTRAL = "tb_central";

    //for tb_bank
    private static final String BANK_ID = "bank_id";
    private static final String BANK_NAME = "bank_name";
    private static final String BANK_LOGO = "bank_logo";
    private static final String BANK_COLOR = "bank_color";
    //for tb_branch
    private static final String BRANCH_ID = "branch_id";
    private static final String BRANCH_NAME = "branch_name";
    private static final String BRANCH_ADDRESS = "address";
    private static final String BRANCH_CONTACT = "contact";
    private static final String BRANCH_LAT = "latitude";
    private static final String BRANCH_LOG = "longitude";
    private static final String BRANCH_TOWNSHIP = "township_id";
    private static final String BRANCH_CITY = "city_id";
    //for tb_atm
    private static final String ATM_ID = "atm_id";
    private static final String ATM_NAME = "atm_name";
    private static final String ATM_ADDRESS = "address";
    private static final String ATM_CONTACT = "contact";
    private static final String ATM_LAT = "latitude";
    private static final String ATM_LOG = "longitude";
    private static final String ATM_TOWNSHIP = "township_id";
    private static final String ATM_CITY = "city_id";
    //for tb_exchange
    private static final String EXCHANGE_ID = "exchange_id";
    private static final String EXCHANGE_NAME = "exchange_name";
    private static final String EXCHANGE_ADDRESS = "address";
    //private static final String EXCHANGE_CONTACT="contact";
    private static final String EXCHANGE_OPENING_TIME = "opening_time";
    private static final String EXCHANGE_LAT = "latitude";
    private static final String EXCHANGE_LOG = "longitude";
    private static final String EXCHANGE_TOWNSHIP = "township_id";
    private static final String EXCHANGE_CITY = "city_id";
    //for tb_currency_exchange
    private static final String CURRENCY_ID = "currency_id";
    private static final String CURRENCY_TYPE = "currency_type";
    private static final String CURRENCY_LOGO = "currency_logo";
    private static final String CURRENCY_EXCHANGE_ID = "currency_exchange_id";
    private static final String CURRENCY_DETAIL_ID = "currency_detail_id";
    private static final String CURRENCY_DATE = "currency_date";
    private static final String CURRENCY_BUY = "buy";
    private static final String CURRENCY_SELL = "sell";
    //for tb_review
    private static final String REVIEW_ID = "review_id";
    private static final String REVIEW_DESCRIPTION = "review_description";
    private static final String REVIEW_DATE = "review_date";
    //for tb_city
    private static final String CITY_ID = "city_id";
    private static final String CITY_NAME = "city_name";
    //for tb_township
    private static final String TOWNSHIP_ID = "township_id";
    private static final String TOWNSHIP_NAME = "township_name";
    //for tb_account
    private static final String ACCOUNT_ID = "account_id";
    private static final String ACCOUNT_NAME = "account_name";
    private static final String ACCOUNT_TYPE = "account_type";
    private static final String MAX = "max";
    private static final String MIN = "min";
    //for tb_interest
    private static final String INTEREST_ID = "interest_id";
    private static final String INTEREST_TYPE = "interest_type_name";
    private static final String FIRST_INTEREST_RATE = "fir_interest_rate";
    private static final String SEC_INTEREST_RATE = "sec_interest_rate";
    private static final String THIRD_INTEREST_RATE = "third_interest_rate";
    private static final String FOUATH_INTEREST_RATE = "fourth_interest_rate";
    private static final String FIFTH_INTEREST_RATE = "fifth_interest_rate";
    // for create Central bank table ezt
    private static final String UNIT_CURRENCY_ID = "unit_currency_id";
    private static final String UNIT_CURRENCY_NAME = "unit_currency_name";
    private static final String CENTRAL_RATE = "central_rate";
    private static final String CENTRAL_DATE = "central_date";

    //for create Loan table
    private static final String TABLE_LOAN = "tb_loan";
    private static final String LOAN_ID = "loan_id";
    private static final String LOAN_NAME = "loan_name";
    private static final String LOAN_TYPE = "loan_type";
    private static final String LOAN_INTEREST = "loan_interest";

    //for tb_account description
    private static final String TABLE_ACCOUNT_DES = "tb_account_description";
    private static final String ACC_ID = "account_id";
    private static final String ACCOUNT_PURPOSE = "acc_purpose";
    private static final String ACC_NAME = "acc_name";
    private static final String ACC_DEPOSIT = "acc_initial_deposit";
    private static final String ACC_TYPE = "acc_type";
    private static final String ACC_DEPOSIT_TYPE = "acc_deposit_type";
    private static final String ACC_WITHDRAW_TYPE = "acc_withdraw_type";
    private static final String ACC_MOBILE_BANKING = "acc_mobile";
    private static final String ACC_CASH_TRANSFER = "acc_transfer";
    private static final String ACC_DESCRIPTION = "acc_description";


    int[] CurrencyLogo = {R.drawable.usd, R.drawable.eur, R.drawable.sgd, R.drawable.thb, R.drawable.mlr};
    int[] BankLogo = {R.drawable.kbz, R.drawable.yoma, R.drawable.agd, R.drawable.meb, R.drawable.cb, R.drawable.aya};


    public BankDBHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // Toast.makeText(context, "Successfully connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//for create bank_table
        String CREATE_TABLE_BANK = "CREATE TABLE " + TABLE_BANK + " ("
                + BANK_ID + " INTEGER, "
                + BANK_NAME + " TEXT, "
                + BANK_LOGO + " INT, "
                + BANK_COLOR + " TEXT,"
                + " PRIMARY KEY( " + BANK_ID + " )"
                + ");";
        db.execSQL(CREATE_TABLE_BANK);


        //for create city_table
        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_CITY + " ("
                + CITY_ID + " INTEGER, "
                + CITY_NAME + " TEXT ,"
                + " PRIMARY KEY( " + CITY_ID + " )"
                + ");";
        db.execSQL(CREATE_CITY_TABLE);

        //for create township table
        String CREATE_TOWNSHIP_TABLE = "CREATE TABLE " + TABLE_TOWNSHIP + " ("
                + TOWNSHIP_ID + " INTEGER, "
                + TOWNSHIP_NAME + " TEXT ,"
                + " PRIMARY KEY( " + TOWNSHIP_ID + " )"
                + ");";
        db.execSQL(CREATE_TOWNSHIP_TABLE);

        //for create branch_tb
        String CREATE_BRANCH_TABLE = "CREATE TABLE " + TABLE_BRANCH + " ("
                + BRANCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BANK_ID + " INTEGER , "
                + BRANCH_NAME + " TEXT, "
                + BRANCH_ADDRESS + " TEXT, "
                + BRANCH_CONTACT + " TEXT, "
                + BRANCH_LAT + " DECIMAL, "
                + BRANCH_LOG + " DECIMAL, "
                + BRANCH_TOWNSHIP + " INTEGER, "
                + BRANCH_CITY + " INTEGER, "
                + " FOREIGN KEY( " + BANK_ID + ") REFERENCES " + TABLE_BANK + "(" + BANK_ID + "), "
                + " FOREIGN KEY( " + BRANCH_TOWNSHIP + ") REFERENCES " + TABLE_TOWNSHIP + "(" + TOWNSHIP_ID + "), "
                + " FOREIGN KEY( " + BRANCH_CITY + ") REFERENCES " + TABLE_CITY + "(" + CITY_ID + ")"
                + ");";
        db.execSQL(CREATE_BRANCH_TABLE);

        //for create atm_table
        String CREATE_ATM_TABLE = "CREATE TABLE " + TABLE_ATM + " ("
                + ATM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BANK_ID + " INTEGER, "
                + ATM_NAME + " TEXT, "
                + ATM_ADDRESS + " TEXT, "
                + ATM_LAT + " DECIMAL, "
                + ATM_LOG + " DECIMAL, "
                + ATM_TOWNSHIP + " INTEGER, "
                + ATM_CITY + " INTEGER, "
                + " FOREIGN KEY( " + BANK_ID + ") REFERENCES " + TABLE_BANK + "(" + BANK_ID + "), "
                + " FOREIGN KEY( " + ATM_TOWNSHIP + ") REFERENCES " + TABLE_TOWNSHIP + "(" + TOWNSHIP_ID + "), "
                + " FOREIGN KEY( " + ATM_CITY + ") REFERENCES " + TABLE_CITY + "(" + CITY_ID + ") "
                + ");";
        db.execSQL(CREATE_ATM_TABLE);

        //for create exchange_table
        String CREATE_EXCHANGE_TABLE = " CREATE TABLE " + TABLE_EXCHANGE + " ("
                + EXCHANGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BANK_ID + " INTEGER, "
                + EXCHANGE_NAME + " TEXT, "
                + EXCHANGE_ADDRESS + " TEXT, "
                + EXCHANGE_OPENING_TIME + " TEXT, "
                + EXCHANGE_LAT + " DECIMAL, "
                + EXCHANGE_LOG + " DECIMAL, "
                + EXCHANGE_TOWNSHIP + " INTEGER, "
                + EXCHANGE_CITY + " INTEGER, "
                + " FOREIGN KEY( " + BANK_ID + ") REFERENCES " + TABLE_BANK + "(" + BANK_ID + "), "
                + " FOREIGN KEY( " + EXCHANGE_TOWNSHIP + ") REFERENCES " + TABLE_TOWNSHIP + " (" + TOWNSHIP_ID + "), "
                + " FOREIGN KEY( " + EXCHANGE_CITY + ") REFERENCES " + TABLE_CITY + " (" + CITY_ID + ")"
                + ");";
        db.execSQL(CREATE_EXCHANGE_TABLE);


        //for create interest table
        String CREATE_ACCOUNT_TABLE = " CREATE TABLE " + TABLE_ACCOUNT + " ("
                + ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BANK_ID + " INTEGER, "
                + ACCOUNT_NAME + " TEXT, "
                + MAX + " DECIMAL(10,5), "
                + MIN + " INTEGER, "
                + " FOREIGN KEY( " + BANK_ID + ") REFERENCES " + TABLE_BANK + "(" + BANK_ID + ")"
                + " );";
        db.execSQL(CREATE_ACCOUNT_TABLE);


        String CREATE_INTEREST_TABLE = " CREATE TABLE " + TABLE_INTEREST + " ("
                + INTEREST_ID + " INTEGER, "
                + INTEREST_TYPE + " TEXT, "
                + FIRST_INTEREST_RATE + " DECIMAL, "
                + SEC_INTEREST_RATE + " DECIMAL, "
                + THIRD_INTEREST_RATE + " DECIMAL, "
                + FOUATH_INTEREST_RATE + " DECIMAL, "
                + FIFTH_INTEREST_RATE + " DECIMAL, "
                + " FOREIGN KEY( " + INTEREST_ID + ") REFERENCES " + TABLE_ACCOUNT + "(" + ACCOUNT_ID + ")"

                + " );";

        db.execSQL(CREATE_INTEREST_TABLE);


//for create review table
        String CREATE_REVIEW_TABLE = " CREATE TABLE " + TABLE_REVIEW + " ("
                + REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BANK_ID + " INTEGER, "
                + REVIEW_DESCRIPTION + " TEXT, "
                + REVIEW_DATE + " TEXT , "
                + " FOREIGN KEY( " + BANK_ID + ") REFERENCES " + TABLE_BANK + "(" + BANK_ID + ") ); ";
        db.execSQL(CREATE_REVIEW_TABLE);
        Log.e("update successfully", "Review");

        //for create currency_type
        String CREATE_CURRENCY_TYPE = " CREATE TABLE " + TABLE_CURRENCY_TYPE + "( "
                + CURRENCY_ID + " INTEGER, "
                + CURRENCY_TYPE + " TEXT, "
                + CURRENCY_LOGO + " INTEGER, "
                + " PRIMARY KEY( " + CURRENCY_ID + " )"
                + ");";
        db.execSQL(CREATE_CURRENCY_TYPE);


        //for create currency
        String CREATE_CURRENCY = " CREATE TABLE " + TABLE_CURRENCY + " ( "
                + CURRENCY_EXCHANGE_ID + " INTEGER, "
                + CURRENCY_ID + " INTEGER, "
                + BANK_ID + " INTEGER, "
                + " PRIMARY KEY( " + CURRENCY_EXCHANGE_ID + " )"
                + " FOREIGN KEY( " + CURRENCY_ID + ") REFERENCES " + TABLE_CURRENCY_TYPE + "(" + CURRENCY_ID + "), "
                + " FOREIGN KEY( " + BANK_ID + ") REFERENCES " + TABLE_BANK + "(" + BANK_ID + ") ); ";
        db.execSQL(CREATE_CURRENCY);


//        String CREATE_TABLE_CENTRAL = "CREATE TABLE " + TABLE_CENTRAL+ " ("
//                + UNIT_CURRENCY_ID+ " INTEGER, "
//                + UNIT_CURRENCY_NAME+ " TEXT, "
//                + CENTRAL_BUY + " DECIMAL, "
//                + CENTRAL_SELL + " DECIMAL,"
//                +CENTRAL_DATE+" TEXT,"
//                + " PRIMARY KEY( " + UNIT_CURRENCY_ID + " )"
//                + ");";
//        db.execSQL(CREATE_TABLE_CENTRAL);


        // db.execSQL("DROP TABLE "+ TABLE_CENTRAL);
        String CREATE_TABLE_CENTRAL = "CREATE TABLE " + TABLE_CENTRAL + " ("
                + UNIT_CURRENCY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UNIT_CURRENCY_NAME + " TEXT, "
                + CENTRAL_RATE + " DECIMAL, "
                + CENTRAL_DATE + " TEXT );";

        db.execSQL(CREATE_TABLE_CENTRAL);

        //for create currency_exchange
        String CREATE_CURRENCY_EXCHANGE = " CREATE TABLE " + TABLE_CURRENCY_EXCHANGE + "( "
                + CURRENCY_DETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CURRENCY_EXCHANGE_ID + " INTEGER, "
                + CURRENCY_DATE + " TEXT, "
                + CURRENCY_BUY + " DECIMAL, "
                + CURRENCY_SELL + " DECIMAL, "
                + " FOREIGN KEY( " + CURRENCY_EXCHANGE_ID + ") REFERENCES " + TABLE_CURRENCY + "(" + CURRENCY_EXCHANGE_ID + ") "
                + " ); ";
        db.execSQL(CREATE_CURRENCY_EXCHANGE);


        //for create Loan
        String CREATE_LOAN = " CREATE TABLE " + TABLE_LOAN + " ( "
                + LOAN_ID + " INTEGER , "
                + LOAN_NAME + " TEXT, "
                + LOAN_TYPE + " TEXT, "
                + LOAN_INTEREST + " DECIMAL,"
                + BANK_ID + " INTEGER, "
                + " PRIMARY KEY( " + LOAN_ID + ")"
                + " FOREIGN KEY( " + BANK_ID + ") REFERENCES " + TABLE_BANK + " ( " + BANK_ID + ") "
                + " ); ";
        db.execSQL(CREATE_LOAN);


        //for create account description
        String CREATE_ACCOUNT_DESCRIPTION = " CREATE TABLE " + TABLE_ACCOUNT_DES + " ("
                + ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BANK_ID + " INTEGER, "
                + ACC_NAME + " TEXT, "
                + ACCOUNT_PURPOSE + " TEXT, "
                + ACC_DEPOSIT + " DECIMAL, "
                + ACC_TYPE + " TEXT, "
                + ACC_DEPOSIT_TYPE + " TEXT,"
                + ACC_WITHDRAW_TYPE + " TEXT, "
                + ACC_DESCRIPTION + " TEXT, "
                + " FOREIGN KEY( " + BANK_ID + ") REFERENCES " + TABLE_BANK + "(" + BANK_ID + ") ); ";
        db.execSQL(CREATE_ACCOUNT_DESCRIPTION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            Log.e("update", "true");

        }
    }

    //insert data into tb_bank
    public void insertBank(Bank bank) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BANK_ID, bank.getBankId());
        contentValues.put(BANK_NAME, bank.getName());
        contentValues.put(BANK_LOGO, bank.getImageURL());
        contentValues.put(BANK_COLOR, bank.getColor());

        db.insert(TABLE_BANK, null, contentValues);
        db.close();
        Log.e("DATA status", "successful");
    }

    //get bank name for interest
    public ArrayList<String> getAllBankNameInterest() {
        ArrayList<String> bank_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String getBankNamesql = "select " + BANK_NAME + " from " + TABLE_BANK + ";";
        Cursor c = db.rawQuery(getBankNamesql, null);
        Log.i("Count", String.valueOf(c.getCount()));
        c.moveToFirst();
        do {
            bank_list.add(c.getString(0));
            Log.e("Selected bank name", c.getString(0));
        } while (c.moveToNext());
        c.close();
        db.close();
        return bank_list;

    }

    //get bank name
    public ArrayList<String> getAllBankName() {
        ArrayList<String> bank_list = new ArrayList<>();
        bank_list.add(0, "All Banks");
        SQLiteDatabase db = this.getReadableDatabase();
        String getBankNamesql = "select " + BANK_NAME + " from " + TABLE_BANK + ";";
        Cursor c = db.rawQuery(getBankNamesql, null);
        Log.i("Count", String.valueOf(c.getCount()));
        c.moveToFirst();
        do {
            bank_list.add(c.getString(0));
            Log.e("Selected bank name", c.getString(0));
        } while (c.moveToNext());
        c.close();
        db.close();
        return bank_list;

    }

    //get Bank obj
    public ArrayList<Bank> getAllBank() {
        ArrayList<Bank> bank_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_BANK;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            int bank_id = cursor.getInt(0);
            String bank_name = cursor.getString(1);
            int bank_logo = cursor.getInt(2);
            String bank_color = cursor.getString(3);
            Bank b = new Bank(bank_id, bank_name, bank_logo, bank_color);
            bank_list.add(b);
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return bank_list;
    }

    //for delete data
    public void deleteBankAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_BANK;
        db.execSQL(sql);
        Log.e("Status", "in delete");
        db.close();
    }

    //for insert township data
    public void insertTownshipData(int id, String town_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TOWNSHIP_ID, id);
        contentValues.put(TOWNSHIP_NAME, town_name);
        db.insert(TABLE_TOWNSHIP, null, contentValues);
        Log.e("Status", "Success insert in township");
        db.close();
    }

    public void getTownshipCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from  " + TABLE_TOWNSHIP + ";";
        Cursor c = db.rawQuery(sql, null);
        Log.e("Count", String.valueOf(c.getCount()));
        c.close();
        db.close();
    }

    //insert city data
    public void insertCityData(int c_id, String c_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CITY_ID, c_id);
        contentValues.put(CITY_NAME, c_name);
        db.insert(TABLE_CITY, null, contentValues);
        db.close();
    }

    public void getCityCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from  " + TABLE_CITY + ";";
        Cursor c = db.rawQuery(sql, null);
        Log.e(" CityCount", String.valueOf(c.getCount()));
        c.close();
        db.close();
    }

    public void deletTownshipData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_TOWNSHIP;
        db.execSQL(sql);
        Log.e("Status", "in delete");
        db.close();
    }

    public void deleteCityData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_CITY;
        db.execSQL(sql);
        Log.e("Status", "in delete");
        db.close();
    }

    //delete atm data
    public void deleteAtmData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_ATM;
        db.execSQL(sql);
        Log.e("Status", "in delete");
        db.close();
    }

    //delete branch data
    public void deleteBranchData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_BRANCH;
        db.execSQL(sql);
        Log.e("Status in branch", "in delete");
        db.close();
    }

    //delete exchange data
    public void deleteExchangeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_EXCHANGE;
        db.execSQL(sql);
        Log.e("Status in exchange", "in delete");
        db.close();
    }


    //public void delete Interest data
    public void deleteInterestData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_INTEREST;
        db.execSQL(sql);
        db.close();
    }

    //public void delete Interest data
    public void deleteAccountData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_ACCOUNT;
        db.execSQL(sql);
        db.close();
    }

    //get all data from city
    public ArrayList<String> getAllCity() {
        ArrayList<String> city_name_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT DISTINCT " + CITY_NAME + " FROM " + TABLE_CITY + ";";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            Log.e("Auto count", String.valueOf(cursor.getCount()));
            cursor.moveToFirst();
            do {
                city_name_list.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return city_name_list;
    }

    //get all township data
    public ArrayList<String> getAllTownship() {
        ArrayList<String> township_name_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT DISTINCT " + TOWNSHIP_NAME + " FROM " + TABLE_TOWNSHIP + ";";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            Log.e(" township ", String.valueOf(cursor.getCount()));
            cursor.moveToFirst();
            do {
                township_name_list.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return township_name_list;
    }

    //get All ATM data with specific bank
    public ArrayList<ATM> getSearchATMData(String bank, String location) {
        ArrayList<ATM> atmData = new ArrayList<>();

        int bank_id = findBankIndex(bank);
        int city_id = findCityIndex(location);
        int township_id = 0;
        String sql;
        if (city_id < 0) {
            township_id = findTownshipIndex(location);
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_ATM + " e WHERE " + BANK_ID + " = " + bank_id + " AND " + ATM_TOWNSHIP + " = " + township_id + ";";
        } else {
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_ATM + " e WHERE " + BANK_ID + " = " + bank_id + " AND " + ATM_CITY + " = " + city_id + ";";

        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int atm_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = cursor.getString(8);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                double lat = cursor.getDouble(4);
                double longitude = cursor.getDouble(5);
                int t_id = cursor.getInt(6);
                int c_id = cursor.getInt(7);
                ATM atm = new ATM(atm_id, bank_name, name, address, lat, longitude, t_id, c_id);
                atmData.add(atm);
            } while (cursor.moveToNext());
        }

        Log.e("ATM Search data ", String.valueOf(atmData.size()));
        cursor.close();
        db.close();
        return atmData;
    }

    //get All ATM data for all banks
    public ArrayList<ATM> getSearchATMAllBank(String location) {
        ArrayList<ATM> atmData = new ArrayList<>();

        int city_id = findCityIndex(location);
        //  int township_id = findTownshipIndex(location);
        int township_id = 0;
        String sql;
        if (city_id < 0) {
            township_id = findTownshipIndex(location);
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_ATM + " e WHERE " + ATM_TOWNSHIP + " = " + township_id + ";";
            Log.e("Township ", "Reach");
        } else {

            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_ATM + " e WHERE " + ATM_CITY + " = " + city_id + ";";
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("ATM Search data1 ", String.valueOf(cursor.getCount()));

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int atm_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = cursor.getString(8);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                double lat = cursor.getDouble(4);
                double longitude = cursor.getDouble(5);
                int t_id = cursor.getInt(6);
                int c_id = cursor.getInt(7);
                ATM atm = new ATM(atm_id, bank_name, name, address, lat, longitude, t_id, c_id);
                atmData.add(atm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.e("ATM Search data1 ", String.valueOf(atmData.size()));
        db.close();
        return atmData;
    }

    //get All Branch data with custom search bank
    public ArrayList<Branch> getSearchBranchData(String bank, String location) {
        ArrayList<Branch> branchData = new ArrayList<>();

        int bank_id = findBankIndex(bank);
        int city_id = findCityIndex(location);
        int township_id = 0;
        String sql;
        if (city_id < 0) {
            township_id = findTownshipIndex(location);
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_BRANCH + " e WHERE " + BANK_ID + " = " + bank_id + " AND " + BRANCH_TOWNSHIP + " = " + township_id + ";";
        } else {
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_BRANCH + " e WHERE " + BANK_ID + " = " + bank_id + " AND " + BRANCH_CITY + " = " + city_id + ";";

        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Branch Search cursor ", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                int branch_id = cursor.getInt(0);
                Log.e("get branch id ", String.valueOf(cursor.getInt(0)));
                int b_id = cursor.getInt(1);
                Log.e("get bank id", String.valueOf(cursor.getInt(1)));
                String bank_name = cursor.getString(9);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String contact = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Branch branch = new Branch(branch_id, bank_name, name, address, contact, lat, longitude, t_id, c_id);
                branchData.add(branch);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("Branch Search data ", String.valueOf(branchData.size()));
        return branchData;
    }

    //get All Branch data for all banks
    public ArrayList<Branch> getSearchBranchData(String location) {
        ArrayList<Branch> branchData = new ArrayList<>();
        int city_id = findCityIndex(location);

        int township_id = 0;
        String sql;
        if (city_id < 0) {
            township_id = findTownshipIndex(location);
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_BRANCH + " e WHERE " + BRANCH_TOWNSHIP + " = " + township_id + ";";
        } else {
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_BRANCH + " e WHERE " + BRANCH_CITY + " = " + city_id + ";";

        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Branch Search cursor ", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int branch_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);

                Log.e("Branch Name ", cursor.getString(2));
                Log.e("Bank id is", String.valueOf(cursor.getInt(1)));
                String bank_name = cursor.getString(9);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String contact = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Branch branch = new Branch(branch_id, bank_name, name, address, contact, lat, longitude, t_id, c_id);
                branchData.add(branch);
            } while (cursor.moveToNext());
        }

        db.close();
        Log.e("Branch Search data all ", String.valueOf(branchData.size()));
        return branchData;
    }

    //get All Exchange data with specific bank
    public ArrayList<Exchange> getSearchExchangeData(String bank, String location) {
        ArrayList<Exchange> exchangeData = new ArrayList<>();

        int bank_id = findBankIndex(bank);
        int city_id = findCityIndex(location);
        int township_id = 0;
        String sql;
        if (city_id < 0) {
            township_id = findTownshipIndex(location);
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_EXCHANGE + " e WHERE " + BANK_ID + " = " + bank_id + " AND " + EXCHANGE_TOWNSHIP + " = " + township_id + ";";
        } else {
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_EXCHANGE + " e WHERE " + BANK_ID + " = " + bank_id + " AND " + EXCHANGE_CITY + " = " + city_id + ";";

        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.e(" search data exchange", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int exchange_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = cursor.getString(9);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String opening_time = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Exchange exchange = new Exchange(exchange_id, bank_name, name, address, opening_time, lat, longitude, t_id, c_id);
                exchangeData.add(exchange);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // Log.i(" Exchange data search",String.valueOf(exchangeData.size()));
        return exchangeData;
    }

    //get All Exchange data for all bank
    public ArrayList<Exchange> getSearchExchangeData(String location) {
        ArrayList<Exchange> exchangeData = new ArrayList<>();

        int city_id = findCityIndex(location);
        int township_id = 0;
        String sql;
        if (city_id < 1) {
            township_id = findTownshipIndex(location);
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_EXCHANGE + " e WHERE " + EXCHANGE_TOWNSHIP + " = " + township_id + ";";
        } else {
            sql = "SELECT *," +
                    "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_EXCHANGE + " e WHERE " + EXCHANGE_CITY + " = " + city_id + ";";

        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.e(" search data exchange", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int exchange_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                Log.e("Bank_id in exchange", String.valueOf(b_id));
                String bank_name = cursor.getString(9);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String opening_time = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Exchange exchange = new Exchange(exchange_id, bank_name, name, address, opening_time, lat, longitude, t_id, c_id);
                exchangeData.add(exchange);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.i(" Exchange data search", String.valueOf(exchangeData.size()));
        return exchangeData;
    }


    //get bank_name
    public String getBankName(int bank_id) {
        Log.e("Bank id", String.valueOf(bank_id));
        SQLiteDatabase db = this.getReadableDatabase();

//        String sql1="select * from "+TABLE_BANK +";";
//        Cursor c=db.rawQuery(sql1,null);
//        c.moveToFirst();
//        do{
//            Log.e("id",String.valueOf(c.getInt(0)));
//            Log.e("name",String.valueOf(c.getString(1)));
//        }while(c.moveToNext());

        String sql = "SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " =" + bank_id + " ;";
        Cursor cursor = db.rawQuery(sql, null);
        //cursor.moveToFirst();
        // Log.e("Bank name",cursor.getString(0));
        if (cursor.moveToFirst()) {
            String b_name = cursor.getString(0);
            return b_name;
        }
        cursor.close();
        db.close();
        return null;
    }

    //get bank_id
    public int findBankIndex(String bank_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + BANK_ID + " FROM " + TABLE_BANK + " WHERE " + BANK_NAME + " = \'" + bank_name + "\';";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int bank_id = cursor.getInt(0);
        if (cursor.getCount() > 0) {
            return bank_id;
        }
        cursor.close();
        db.close();
        return -1;
    }


    //to get account ID by bank_id and account_name
    public int getAccountID(String account_name, int bank_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int acc_id = 1;
        String sql = "select " + ACCOUNT_ID + " from " + TABLE_ACCOUNT + " where " + BANK_ID + " = " + bank_id + " and " + ACCOUNT_NAME + "=\'" + account_name + "\'";
//        String sql="select * from "+TABLE_ACCOUNT+" where "+BANK_ID+" = "+bank_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
//        String acc_name=c.getString(2);
//       if(acc_name.equals(account_name)){ acc_id=c.getInt(0);}
        int countId = c.getInt(0);
        c.close();
        db.close();
        return countId;
    }


    //to get bank ID by bank Name
    public int getBankID(String bankName) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_BANK + " where " + BANK_NAME + "=\'" + bankName + "\'";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        int bankId = c.getInt(c.getColumnIndex(BANK_ID));
        c.close();
        db.close();
        return bankId;
    }


    //to check amount user typed
    public String checkAmount(int acc_id, Double amount) {

        SQLiteDatabase db = this.getReadableDatabase();
        Log.e("check", "AMOUNT" + amount);
        String sql = "select * from " + TABLE_ACCOUNT + " where " + ACCOUNT_ID + " = " + acc_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        Double MAX = c.getDouble(3);
        Double MIN = c.getDouble(4);
        Log.e("MAX", "=" + MAX);
        Log.e("MIN", "=" + MIN);

        String check;
        if (amount < MIN) {
            check = "Your amount is less than Minimum amount !\n Try Again";
        } else if (MAX != 0 && amount > MAX) {
            check = "Your amount is greater than Maximum amount !\n Try Again";
        } else if (amount == 0) {
            check = "Please enter your amount ";
        } else {
            check = "true";
        }
        c.close();
        return check;
    }

    //to get account type from interest table
    public String getAccountType(int acc_id) {
        Log.e("id", " aa" + acc_id);
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_INTEREST + " where " + INTEREST_ID + " = " + acc_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        Log.e("acc_type ", " is " + c.getString(1));
        String accName = c.getString(1);
        c.close();
        db.close();
        return accName;

    }

    //to check fixed or saving account and get Interest Rate
    public ArrayList<Double> getInterestRate(int interest_id) {
        Log.e("count", "count " + interest_id);
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Double> interest = new ArrayList<Double>();

        String sqlTest = "select * from " + TABLE_INTEREST;
        Cursor cc = db.rawQuery(sqlTest, null);
        Log.e("interest table", "count " + cc.getCount());

        String sql = "select * from " + TABLE_INTEREST + " where " + INTEREST_ID + " = " + interest_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        Log.e("count", "count " + c.getCount());
        // c.getString(0);
        if (c.getString(1).equals("fixed")) {
            interest.add(c.getDouble(2));
            interest.add(c.getDouble(3));
            interest.add(c.getDouble(4));
            interest.add(c.getDouble(5));
            interest.add(c.getDouble(6));
        } else if (c.getString(1).equals("supwar")) {
            interest.add(c.getDouble(2));
            interest.add(c.getDouble(3));
            interest.add(c.getDouble(4));
        } else {
            interest.add(c.getDouble(2));
        }
        c.close();
        db.close();
        return interest;


    }


    //to get all account names by bank ID
    public ArrayList<String> getAllAccountName(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> allAccountName = new ArrayList<String>();
        String sql = "select " + ACCOUNT_NAME + " from " + TABLE_ACCOUNT + " where " + BANK_ID + " = " + id;
        Cursor c = db.rawQuery(sql, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            String acc_name = c.getString(0);
            allAccountName.add(acc_name);
            c.moveToNext();
        }
        c.close();
        db.close();
        return allAccountName;
    }


    //insert branch data
    public void insertBranchData(Branch branch) {

        ContentValues contentValues = new ContentValues();
        String bank_name = branch.getBankName();
        int bank_id = findBankIndex(bank_name);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("Insert bank name", bank_name);
        Log.e("Insert bank_id", String.valueOf(bank_id));
        contentValues.put(BANK_ID, bank_id);
        contentValues.put(BRANCH_NAME, branch.getBranchName());
        contentValues.put(BRANCH_ADDRESS, branch.getBranchAddress());
        contentValues.put(BRANCH_CONTACT, branch.getBranchPhNo());
        contentValues.put(BRANCH_LAT, branch.getBranchLatitude());
        contentValues.put(BRANCH_LOG, branch.getBranchLongitude());
        contentValues.put(BRANCH_TOWNSHIP, branch.getBranchTownship());
        contentValues.put(BRANCH_CITY, branch.getBranchCity());
        db.insert(TABLE_BRANCH, BRANCH_ID, contentValues);
        db.close();
        Log.e("Status", "branch inserted");
    }

    //insert atm data
    public void insertAtmData(ATM atm) {

        ContentValues contentValues = new ContentValues();
        int bank_id = findBankIndex(atm.getBankName());
        SQLiteDatabase db = this.getReadableDatabase();
        contentValues.put(BANK_ID, bank_id);
        contentValues.put(ATM_NAME, atm.getAtmName());
        contentValues.put(ATM_ADDRESS, atm.getAtmAddress());
        contentValues.put(ATM_LAT, atm.getAtmLatitude());
        contentValues.put(ATM_LOG, atm.getAtmLongitude());
        contentValues.put(ATM_TOWNSHIP, atm.getTownship_id());
        contentValues.put(ATM_CITY, atm.getCity_id());
        db.insert(TABLE_ATM, ATM_ID, contentValues);
        db.close();
        Log.e("status", "atm data inserted");

    }

    //insert exchange data
    public void insertExchangeData(Exchange exchange) {

        ContentValues contentValues = new ContentValues();
        int bank_id = findBankIndex(exchange.getBankName());
        SQLiteDatabase db = this.getReadableDatabase();
        contentValues.put(BANK_ID, bank_id);
        contentValues.put(EXCHANGE_NAME, exchange.getExchangeName());
        contentValues.put(EXCHANGE_ADDRESS, exchange.getExchangeAddress());
        contentValues.put(EXCHANGE_OPENING_TIME, exchange.getExchangeOpenTime());
        contentValues.put(EXCHANGE_LAT, exchange.getExchangeLatitude());
        contentValues.put(EXCHANGE_LOG, exchange.getExchangeLongitude());
        contentValues.put(EXCHANGE_TOWNSHIP, exchange.getTownship_id());
        contentValues.put(EXCHANGE_CITY, exchange.getCity_id());
        db.insert(TABLE_EXCHANGE, EXCHANGE_ID, contentValues);
        db.close();
        Log.e("status", "exchange data inserted");

    }

    //get township index
    public int findTownshipIndex(String townshipName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + TOWNSHIP_ID + " FROM " + TABLE_TOWNSHIP + " WHERE " + TOWNSHIP_NAME + " LIKE \'%" + townshipName + "%\'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int t_id = -1;

        if (cursor.getCount() > 0) {
            t_id = cursor.getInt(0);
            return t_id;
        }
        Log.e("township name", townshipName);
        Log.e("township _id", String.valueOf(t_id));
        cursor.close();
        db.close();
        return t_id;
    }

    //get city index
    public int findCityIndex(String cityName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + CITY_ID + " FROM " + TABLE_CITY + " WHERE " + CITY_NAME + " LIKE \'%" + cityName + "%\'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int c_id = -1;
        if (cursor.getCount() > 0) {
            c_id = cursor.getInt(0);
            return c_id;
        }
        Log.e("city name", cityName);
        Log.e("city _id", String.valueOf(c_id));
        cursor.close();
        db.close();
        return c_id;
    }

    //get branch count
    public void getBranchCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_BRANCH;
        Cursor c = db.rawQuery(sql, null);
        Log.e("Count in branch", String.valueOf(c.getCount()));
        c.close();
        db.close();
    }

    //get branch data of specific bank
    public ArrayList<Branch> getSearchBranchAddress(int bank_id) {
        //Log.e("bank id in db",String.valueOf(bank_id));
        ArrayList<Branch> branchData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_BRANCH + " e WHERE " + BANK_ID + " =" + bank_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Branch Search cursor ", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                int branch_id = cursor.getInt(0);
                Log.e("get branch id ", String.valueOf(cursor.getInt(0)));
                int b_id = cursor.getInt(1);
                Log.e("get bank id", String.valueOf(cursor.getInt(1)));
                String bank_name = cursor.getString(9);//getBankName(b_id);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String contact = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Branch branch = new Branch(branch_id, bank_name, name, address, contact, lat, longitude, t_id, c_id);
                branchData.add(branch);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("Branch Search data ", String.valueOf(branchData.size()));
        return branchData;
    }


    //get branch data of specific bank
    public ArrayList<Branch> getSearchBranchAddressNoBankName(int bank_id) {
        //Log.e("bank id in db",String.valueOf(bank_id));
        ArrayList<Branch> branchData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_BRANCH + " e WHERE " + BANK_ID + " =" + bank_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Branch Search cursor ", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                int branch_id = cursor.getInt(0);
                Log.e("get branch id ", String.valueOf(cursor.getInt(0)));
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String contact = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Branch branch = new Branch(branch_id, "", name, address, contact, lat, longitude, t_id, c_id);
                branchData.add(branch);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("Branch Search data ", String.valueOf(branchData.size()));
        return branchData;
    }

    //get atm data of specific bank
    public ArrayList<ATM> getSearchATMAddress(int bank_id) {
        //Log.e("bank id in db",String.valueOf(bank_id));
        ArrayList<ATM> atmData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_ATM + " e WHERE " + BANK_ID + " =" + bank_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("ATM Search cursor ", String.valueOf(cursor.getCount()));

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int atm_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = cursor.getString(8);//getBankName(b_id);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                double lat = cursor.getDouble(4);
                double longitude = cursor.getDouble(5);
                int t_id = cursor.getInt(6);
                int c_id = cursor.getInt(7);
                ATM atm = new ATM(atm_id, bank_name, name, address, lat, longitude, t_id, c_id);
                atmData.add(atm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("ATM Search data ", String.valueOf(atmData.size()));
        return atmData;
    }

    //get atm data of specific bank
    public ArrayList<ATM> getSearchATMAddressNoBankName(int bank_id) {
        //Log.e("bank id in db",String.valueOf(bank_id));
        ArrayList<ATM> atmData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_ATM + " e WHERE " + BANK_ID + " =" + bank_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("ATM Search cursor ", String.valueOf(cursor.getCount()));

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int atm_id = cursor.getInt(0);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                double lat = cursor.getDouble(4);
                double longitude = cursor.getDouble(5);
                int t_id = cursor.getInt(6);
                int c_id = cursor.getInt(7);
                ATM atm = new ATM(atm_id, "", name, address, lat, longitude, t_id, c_id);
                atmData.add(atm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("ATM Search data ", String.valueOf(atmData.size()));
        return atmData;
    }


    //get exchange data of specific bank
    public ArrayList<Exchange> getSearchExchangeAddress(int bank_id) {
        //Log.e("bank id in db",String.valueOf(bank_id));
        ArrayList<Exchange> exchangeData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_EXCHANGE + " e WHERE " + BANK_ID + " =" + bank_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Exchange Search cursor ", String.valueOf(cursor.getCount()));

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int exchange_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = cursor.getString(9);//getBankName(b_id);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String opening_time = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Exchange exchange = new Exchange(exchange_id, bank_name, name, address, opening_time, lat, longitude, t_id, c_id);
                exchangeData.add(exchange);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // Log.i(" Exchange data search",String.valueOf(exchangeData.size()));
        return exchangeData;
    }

    //get exchange data of specific bank
    public ArrayList<Exchange> getSearchExchangeAddressNoBankName(int bank_id) {
        //Log.e("bank id in db",String.valueOf(bank_id));
        ArrayList<Exchange> exchangeData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_EXCHANGE + " e WHERE " + BANK_ID + " =" + bank_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Exchange Search cursor ", String.valueOf(cursor.getCount()));

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int exchange_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = cursor.getString(9);//getBankName(b_id);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String opening_time = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Exchange exchange = new Exchange(exchange_id, "", name, address, opening_time, lat, longitude, t_id, c_id);
                exchangeData.add(exchange);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // Log.i(" Exchange data search",String.valueOf(exchangeData.size()));
        return exchangeData;
    }

    //get Search Branch Address Specific
    public ArrayList<Branch> getSearchBranchAddressSpecific(int bank_id, String searchData) {
        ArrayList<Branch> branchData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_BRANCH + " WHERE " + BANK_ID + "=" + bank_id + " AND " + BRANCH_ADDRESS + " LIKE \'%" + searchData + "%\' ;";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Branch Search cursor ", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                int branch_id = cursor.getInt(0);
                Log.e("get branch id ", String.valueOf(cursor.getInt(0)));
                int b_id = cursor.getInt(1);
                Log.e("get bank id", String.valueOf(cursor.getInt(1)));
                String bank_name = getBankName(b_id);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String contact = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Branch branch = new Branch(branch_id, bank_name, name, address, contact, lat, longitude, t_id, c_id);
                branchData.add(branch);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("Branch Search data ", String.valueOf(branchData.size()));
        return branchData;
    }


    // get Search ATM Address Specific
    public ArrayList<ATM> getSearchATMAddressSpecific(String searchData) {
        //Log.e("bank id in db",String.valueOf(bank_id));
        ArrayList<ATM> atmData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_ATM + " WHERE " + ATM_ADDRESS + " LIKE % '" + searchData + "'% ;";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("ATM Search cursor ", String.valueOf(cursor.getCount()));

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int atm_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = getBankName(b_id);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                double lat = cursor.getDouble(4);
                double longitude = cursor.getDouble(5);
                int t_id = cursor.getInt(6);
                int c_id = cursor.getInt(7);
                ATM atm = new ATM(atm_id, bank_name, name, address, lat, longitude, t_id, c_id);
                atmData.add(atm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("ATM Search data ", String.valueOf(atmData.size()));
        return atmData;
    }


    //add data to interest table
    public void insertAccount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(1,1,'Call Deposit Saving Account',100000,null)";
        String insert2 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(2,1,'Saving Deposit Account',1000,null)";
        String insert3 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(3,1,'Fixed Deposit Account',1000,null)";
        String insert4 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(4,1,'Children Saving Account',1000,null)";
        String insert5 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(5,1,'SuPwar Deposit Account',1000,null)";
        String insert6 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(6,2,'Call Deposit Account',100000,null)";
        String insert7 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(7,2,'Saving Deposit Account',1000,null)";
        String insert8 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(8,2,'Fixed Deposit Account',1000,null)";
        String insert9 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(9,3,'Saving Account',1000,null)";
        String insert10 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(10,3,'Special Deposit Account',10000000,null)";
        String insert11 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(11,3,'Fixed Deposit Account',10000,null)";
        String insert12 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(12,4,'Saving Deposit Account',1000,null)";
        String insert13 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(13,4,'Fixed Deposit Account',1000,null)";

        String insert14 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(14,5,'Smart Saving Account',1000,null)";
        String insert15 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(15,5,'eSaving Account',1000,null)";
        String insert16 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(16,5,'Minor Deposit Account',1000,null)";
        String insert17 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(17,5,'Call Deposit Account',100000,null)";
        String insert18 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(18,5,'Traditional Saving Account',1000,null)";
        String insert19 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(19,5,'CB Youth Account',2000,null)";
        String insert20 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(20,5,'Fixed Deposit Account',100000,null)";
        String insert21 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(21,5,'Wedding Deposit Account',1000000,null)";
        String insert22 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(22,6,'AYA Saving Account',1000,null)";

        String insert23 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(23,6,'AYA Interest Maximizer Account',1000,null)";

        String insert24 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(24,6,'AYA loyal Account',1000,null)";

        String insert25 = "insert into tb_account(account_id,bank_id,account_name,min,max) " +
                "values(25,6,'Fixed Deposit Account',10000,null)";
        db.execSQL(insert);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);

        db.execSQL(insert6);
        db.execSQL(insert7);
        db.execSQL(insert8);
        db.execSQL(insert9);
        db.execSQL(insert10);

        db.execSQL(insert11);
        db.execSQL(insert12);
        db.execSQL(insert13);
        db.execSQL(insert14);
        db.execSQL(insert15);

        db.execSQL(insert16);
        db.execSQL(insert17);
        db.execSQL(insert18);
        db.execSQL(insert19);
        db.execSQL(insert20);

        db.execSQL(insert21);
        db.execSQL(insert22);
        db.execSQL(insert23);
        db.execSQL(insert24);
        db.execSQL(insert25);
        Log.e("status", "Account Inserted2");
        db.close();
    }


    //insert data to Interest table
    public void insertInterest() {
        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(1,'call deposit',6,null,null,null,null)";
        String insert2 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(2,'saving',8.25,null,null,null,null)";
        String insert3 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(3,'fixed',9,9.25,9.5,9.75,10)";
        String insert4 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(4,'saving',8.25,null,null,null,null)";
        String insert5 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(5,'supwar',9.25,9.5,10,null,null)";
        String insert6 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(6,'call deposit',6,null,null,null,null)";
        String insert7 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(7,'saving',8.25,null,null,null,null)";
        String insert8 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(8,'fixed',9,9.25,9.5,9.75,10)";
        String insert9 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(9,'saving',8.6,null,null,null,null)";
        String insert10 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(10,'call deposit',6,null,null,null,null)";
        String insert11 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(11,'fixed',9,9.25,9.5,9.75,10)";
        String insert12 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(12,'saving',8,null,null,null,null)";
        String insert13 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(13,'fixed',8,8.25,8.5,8.75,9.25)";
        String insert14 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(14,'smart saving',8.25,null,null,null,null)";
        String insert15 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(15,'esaving',8.25,null,null,null,null)";
        String insert16 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(16,'saving',8.25,null,null,null,null)";
        String insert17 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(17,'call deposit',6,null,null,null,null)";
        String insert18 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(18,'saving',8.25,null,null,null,null)";
        String insert19 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(19,'saving',8.5,null,null,null,null)";
        String insert20 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(20,'fixed',9,9.25,9.5,9.75,10)";
        String insert21 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(21,'wedding',10,null,null,null,null)";
        String insert22 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(22,'saving',8.25,null,null,null,null)";
        String insert23 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(23,'saving',8.25,null,null,null,null)";
        String insert24 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(24,'saving',8.25,null,null,null,null)";
        String insert25 = "insert into tb_interest(interest_id,interest_type_name,fir_interest_rate,sec_interest_rate,third_interest_rate,fourth_interest_rate,fifth_interest_rate) " +
                "values(25,'fixed',9,9.25,9.5,9.75,10)";
        db.execSQL(insert);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);

        db.execSQL(insert6);
        db.execSQL(insert7);
        db.execSQL(insert8);
        db.execSQL(insert9);
        db.execSQL(insert10);

        db.execSQL(insert11);
        db.execSQL(insert12);
        db.execSQL(insert13);
        db.execSQL(insert14);
        db.execSQL(insert15);

        db.execSQL(insert16);
        db.execSQL(insert17);
        db.execSQL(insert18);
        db.execSQL(insert19);
        db.execSQL(insert20);

        db.execSQL(insert21);
        db.execSQL(insert22);
        db.execSQL(insert23);
        db.execSQL(insert24);
        db.execSQL(insert25);
        Log.e("status", "interest Inserted2");
        db.close();
    }


    //get data from review9
    public void insertReview(Review review) {

        ContentValues contentValues = new ContentValues();
        int bank_id = findBankIndex(review.getBank_name());

        contentValues.put(BANK_ID, bank_id);
        contentValues.put(REVIEW_DESCRIPTION, review.getReview());
        contentValues.put(REVIEW_DATE, review.getReviewDate());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_REVIEW, REVIEW_ID, contentValues);
        Log.e("status", "review inserted");
    }

    //public void insertManualReview
    public void insertManualReview(){
        SQLiteDatabase db=this.getWritableDatabase();

        String sql3="INSERT INTO "+TABLE_REVIEW+" (bank_id,review_description,review_date ) values (1,'Pyin Oo Lwin ATM is out of service now','18/11/2017-13:00');";
        String sql1="INSERT INTO "+TABLE_REVIEW+" (bank_id,review_description,review_date ) values (1,'So many branches','18/11/2017-13:00');";
        String sql2="INSERT INTO "+TABLE_REVIEW+" (bank_id,review_description,review_date ) values (2,'Cannot contact to the head Office','18/11/2017-13:30');";
        String sql4="INSERT INTO "+TABLE_REVIEW+" (bank_id,review_description,review_date ) values (3,'Excellent Service','18/11/2017-13:30');";
        String sql5="INSERT INTO "+TABLE_REVIEW+" (bank_id,review_description,review_date ) values (4,'Many accounts and services','18/11/2017-13:30');";
        String sql6="INSERT INTO "+TABLE_REVIEW+" (bank_id,review_description,review_date ) values (5,'Good Service!','18/11/2017-13:30');";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
        db.close();
        Log.e("STATUS","Manual Review reach");

    }



    //public void getReview(){
    public ArrayList<Review> getReview(String bank_name) {
        Log.e("In db get review", "reach");
        ArrayList<Review> review_list = new ArrayList<>();
        int bank_id = findBankIndex(bank_name);
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_REVIEW + " WHERE " + BANK_ID + " = " + bank_id;
        Cursor cursor = db.rawQuery(sql, null);

        Log.e("review count is", "" + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            do {
                String review_data = cursor.getString(2);
                String review_date = cursor.getString(3);
                Review review = new Review(bank_name, review_data, review_date);
                review_list.add(review);
            } while (cursor.moveToPrevious());
        }
        cursor.close();
        db.close();
        Log.e("review count is", "" + review_list.size());
        return review_list;

    }


    //for test empty data
    public boolean checkBankTable() {
        boolean isEmpty = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlBank = "select * from " + TABLE_BANK;
        Cursor c = db.rawQuery(sqlBank, null);
        Log.e("Bank count", "" + c.getCount());
        if (c.getCount() == 0) {
            isEmpty = true;
        }
        c.close();
        db.close();
        return isEmpty;
    }

    //test for empty data
    public boolean ischeckEmpty() {
        boolean isEmpty = false;
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlTwn = "select " + TOWNSHIP_ID + " from " + TABLE_TOWNSHIP;
        String sqlCity = "select  " + CITY_ID + " from " + TABLE_CITY;
        String sqlBranch = "select " + BRANCH_ID + " from " + TABLE_BRANCH;
        String sqlATM = "select " + ATM_ID + " from " + TABLE_ATM;
        String sqlExchange = "select " + EXCHANGE_ID + " from " + TABLE_EXCHANGE;
        String sqlAccount = "select " + ACCOUNT_ID + " from " + TABLE_ACCOUNT;
        String sqlInterest = "select " + INTEREST_ID + " from " + TABLE_INTEREST;
        String sqlCurrencyType = "select " + CURRENCY_ID + " from " + TABLE_CURRENCY_TYPE;
        String sqlCurrency = "select " + CURRENCY_ID + " from " + TABLE_CURRENCY;
        String sqlCurrencyExchange = "select " + CURRENCY_DETAIL_ID + " from " + TABLE_CURRENCY_EXCHANGE;
        // String sqlCentral =   "select "+ UNIT_CURRENCY_ID +" from "  + TABLE_CENTRAL;

        Cursor c = db.rawQuery(sqlTwn, null);
        Cursor c1 = db.rawQuery(sqlCity, null);
        Cursor cbranch = db.rawQuery(sqlBranch, null);
        Cursor cAtm = db.rawQuery(sqlATM, null);
        Cursor cExchange = db.rawQuery(sqlExchange, null);
        Cursor cAccount = db.rawQuery(sqlAccount, null);
        Cursor cInterest = db.rawQuery(sqlInterest, null);
        Cursor cCurrencyType = db.rawQuery(sqlCurrencyType, null);
        Cursor cCurrency = db.rawQuery(sqlCurrency, null);
        Cursor cCurrencyEx = db.rawQuery(sqlCurrencyExchange, null);
        //Cursor cCentral = db.rawQuery(sqlCentral, null);


        Log.e("Township count", "" + c.getCount());
        Log.e("city count", "" + c1.getCount());
        Log.e("Branch count", "" + cbranch.getCount());
        Log.e("AtM count", "" + cAtm.getCount());
        Log.e("Exchange count", "" + cExchange.getCount());
        Log.e("Acount count", "" + cAccount.getCount());
        Log.e("Interest count", "" + cInterest.getCount());
        Log.e("Currency type count", "" + cCurrencyType.getCount());
        Log.e("Currency count", "" + cCurrency.getCount());
        Log.e("Currency exchange count", "" + cExchange.getCount());
        //Log.e("Central count", "" + cCentral.getCount());


        if (c.getCount() == 0 || c1.getCount() == 0 || cbranch.getCount() == 0 || cAtm.getCount() == 0 ||
                cExchange.getCount() == 0 || cAccount.getCount() == 0 || cInterest.getCount() == 0 ||
                cCurrencyType.getCount() == 0 || cCurrency.getCount() == 0 || cCurrencyEx.getCount() == 0
                ) {
            isEmpty = true;
        }
        c.close();
        c1.close();
        cbranch.close();
        cAtm.close();
        cAccount.close();
        cInterest.close();
        cCurrency.close();
        cExchange.close();
        // cCentral.close();
        db.close();
        return isEmpty;
    }

    //to get all Currency Type of specified Bank
    public ArrayList<String> getCurrencyByBank(int bank_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> AllCurrencyName = new ArrayList<>();

        String sql = "select * from " + TABLE_CURRENCY + " where " + BANK_ID + " = " + bank_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        do {
            int currencyType_id = c.getInt(c.getColumnIndex(CURRENCY_ID));
            String currencyName = getCurrencyType(currencyType_id);
            AllCurrencyName.add(currencyName);
            c.moveToNext();
        }
        while (!c.isAfterLast());
        c.close();
        db.close();
        return AllCurrencyName;
    }


    //to get Currency Type Name of currency Id
    public String getCurrencyType(int currency_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CURRENCY_TYPE + " where " + CURRENCY_ID + " = " + currency_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        Log.e("c_ type", "" + c.getCount());
        String currency_type = c.getString(c.getColumnIndex(CURRENCY_TYPE));
        c.close();
        db.close();
        return currency_type;
    }


    //to get all currrency Type from Central Bank
    public ArrayList<String> getCurrencyTypeByCentral() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> centralCurrencyArrayList = new ArrayList<>();
        String sql = "select distinct unit_currency_name from " + TABLE_CENTRAL;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        do {
            String currencyName = c.getString(c.getColumnIndex(UNIT_CURRENCY_NAME));
            centralCurrencyArrayList.add(currencyName);
            c.moveToNext();
        }
        while (!c.isAfterLast());
        c.close();
        db.close();
        return centralCurrencyArrayList;
    }
//


    //to get buy && sell value of central bank by date
    public double getCentralRate(String currency) {
        SQLiteDatabase db = this.getReadableDatabase();
        double rate = 0.0;


        String sql = "select * from " + TABLE_CENTRAL + " where " + UNIT_CURRENCY_NAME + " =\'" + currency + "\'";


        Cursor c = db.rawQuery(sql, null);
        c.moveToPosition(c.getCount() - 1);
        do {
            double dbRate = c.getDouble(c.getColumnIndex(CENTRAL_RATE));
            rate = dbRate;
            c.moveToNext();
        }
        while (!c.isAfterLast());
        c.close();
        db.close();
        return rate;
    }


    //to get buy && sell value of central bank by date
    public double[] getBuySell(int bank_id, String trimdate, int currency_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        double[] buySell = new double[2];

        String sql = "select * from " + TABLE_CURRENCY + " where " + CURRENCY_ID + " = " + currency_id + " and " + BANK_ID + " = "
                + bank_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        int currency_exchange_id = c.getInt(c.getColumnIndex(CURRENCY_EXCHANGE_ID));
        Log.e("TESTDBBBexchange_id", " " + currency_exchange_id);

        String sql2 = "select * from " + TABLE_CURRENCY_EXCHANGE + " where " + CURRENCY_EXCHANGE_ID + " = " + currency_exchange_id + " AND " + CURRENCY_DATE + " =\'" +
                trimdate + "\'";
        Cursor c2 = db.rawQuery(sql2, null);
        c2.moveToFirst();
        do {
            double buy = c2.getDouble(c2.getColumnIndex(CURRENCY_BUY));
            double sell = c2.getDouble(c2.getColumnIndex(CURRENCY_SELL));
            buySell[0] = buy;
            buySell[1] = sell;
            c2.moveToNext();
        }
        while (!c2.isAfterLast());
        c.close();
        c2.close();
        db.close();
        return buySell;
    }


    //to get currency id
    public int getCurrencyID(String currencyName) {

        SQLiteDatabase db = this.getReadableDatabase();
        int currency_id;
        String sql = "select * from " + TABLE_CURRENCY_TYPE + " where " + CURRENCY_TYPE + " =\'" + currencyName + "\'";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        do {
            currency_id = c.getInt(c.getColumnIndex(CURRENCY_ID));
            Log.e("TESTDBCurrency_id", " " + currency_id);
            c.moveToNext();
        }
        while (!c.isAfterLast());
        c.close();
        db.close();
        return currency_id;
    }


    public void getCentralDate() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CENTRAL;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Log.e(" central shi date", "a" + c.getString(c.getColumnIndex(CENTRAL_DATE)));
            c.moveToNext();
        }


        Log.e("Central Count", "" + c.getCount());
        c.close();
        db.close();
    }

    //to insert Central bank table
    public void insertCentral(double usd, double eur, double sgd, double thb, double myr, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "insert into tb_central(unit_currency_name,central_rate,central_date)values"
                + "('USD'," + usd + ",'" + date + "')";
        String insert2 = "insert into tb_central(unit_currency_name,central_rate,central_date) values"
                + "('EURO'," + eur + ",'" + date + "')";
        String insert3 = "insert into tb_central(unit_currency_name,central_rate,central_date) values"
                + "('SGD'," + sgd + ",'" + date + "')";
        String insert4 = "insert into tb_central(unit_currency_name,central_rate,central_date) values"
                + "('THB'," + thb + ",'" + date + "')";
        String insert5 = "insert into tb_central(unit_currency_name,central_rate,central_date) values"
                + "('MYR'," + myr + ",'" + date + "')";


        Log.e("IN the central", "inserted");
        db.execSQL(insert);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);

        Log.e("IN the central", "inserted1");


    }

    public String getCentralLastDate() {
        String date = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + CENTRAL_DATE + " from " + TABLE_CENTRAL;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            date = cursor.getString(cursor.getColumnIndex(CENTRAL_DATE));
        }
        Log.e("Latest date ", date);
        cursor.close();
        db.close();
        return date;
    }


    public ArrayList<Currency> getCentralData() {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Currency> currencyArrayList = new ArrayList<>();

        String sql = "select * from " + TABLE_CENTRAL;


        Cursor c = db.rawQuery(sql, null);
        //c.moveToFirst();
        c.moveToPosition(c.getCount() - 5);

        Log.e("Centrall data count", " " + c.getCount());
        while (!c.isAfterLast()) {


            //update ezt
            Currency currency = new Currency();
            int currency_id = c.getInt(c.getColumnIndex(UNIT_CURRENCY_ID));
            currency.setCurrency_type(c.getString(c.getColumnIndex(UNIT_CURRENCY_NAME)));
            currency.setCurrency_id(currency_id);
            Log.e("id", "" + currency_id);
            if (currency_id > 5) {
                currency.setCurrencyLogo(CurrencyLogo[(currency_id - 1) % 5]);
            } else {
                currency.setCurrencyLogo(CurrencyLogo[currency_id - 1]);
            }
            currency.setRate(c.getDouble(c.getColumnIndex(CENTRAL_RATE)));

            currencyArrayList.add(currency);
            c.moveToNext();
        }
        c.close();
        db.close();
        return currencyArrayList;


    }

    //to get all data from central bank
    public ArrayList<Currency> getCentralData(String date) {
        Log.e("Given date", " " + date);
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Currency> currencyArrayList = new ArrayList<>();

        String sql = "select * from " + TABLE_CENTRAL + " where " + CENTRAL_DATE + " =\'" + date + "\'";

        Log.e("Central Date", " " + date);

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        Log.e("Centrall data count", " " + c.getCount());
        while (!c.isAfterLast()) {
            Currency currency = new Currency();
            int currency_id = c.getInt(c.getColumnIndex(UNIT_CURRENCY_ID));
            currency.setCurrency_type(c.getString(c.getColumnIndex(UNIT_CURRENCY_NAME)));
            currency.setCurrency_id(currency_id);
            Log.e("id", "" + currency_id);
            if (currency_id > 5) {
                currency.setCurrencyLogo(CurrencyLogo[(currency_id - 1) % 5]);
            } else {
                currency.setCurrencyLogo(CurrencyLogo[currency_id - 1]);
            }
            currency.setRate(c.getDouble(c.getColumnIndex(CENTRAL_RATE)));

            currencyArrayList.add(currency);
            c.moveToNext();
        }
        c.close();
        db.close();
        return currencyArrayList;
    }

    //delete currency table
    public void deletecurrency_exchange_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_CURRENCY_EXCHANGE;
        db.execSQL(sql);
        db.close();
    }

    //to get currency detail for specified currency type,date

    public ArrayList<Currency> getCurrencyDetail(String data, int currency_id) {
//         int []CurrencyLogo={R.drawable.USD,R.drawable.EUR,R.drawable.SGD,R.drawable.THB,R.drawable.MLR};
//         int []BankLogo={R.drawable.KBZ, R.drawable.YOMA,R.drawable.AGD,R.drawable.MEB,R.drawable.CB,R.drawable.AYA};
        SQLiteDatabase db1;

        SQLiteDatabase db = this.getReadableDatabase();


        ArrayList<Currency> currencyArrayList = new ArrayList<>();

        String sql2 = "select * from " + TABLE_CURRENCY + " where " + CURRENCY_ID + " = " + currency_id;
        Cursor c2 = db.rawQuery(sql2, null);
        Cursor c3;
        c2.moveToFirst();
        do {
            Currency currency = new Currency();
            int bank_id = c2.getInt(c2.getColumnIndex(BANK_ID));
            int currency_Exchange_Id = c2.getInt(c2.getColumnIndex(CURRENCY_EXCHANGE_ID));
            //Log.e("c exchange id", " " + c2.getInt(c2.getColumnIndex(CURRENCY_EXCHANGE_ID)));
            String bank_name = getBankName(bank_id);

            currency.setCurrency_id(currency_id);
            currency.setBank_id(bank_id);

            int currency_type_id = c2.getInt(c2.getColumnIndex(CURRENCY_ID));
            //Log.e("TeST CURRENCY TYPE", " " + getCurrencyType(currency_type_id));
            currency.setCurrency_type(getCurrencyType(currency_type_id));

            currency.setCurrencyLogo(CurrencyLogo[currency_type_id - 1]);
            currency.setBank_name(bank_name);
            currency.setLogo(BankLogo[bank_id - 1]);
            String sqlt = "select * from " + TABLE_CURRENCY_EXCHANGE + " where " + CURRENCY_EXCHANGE_ID + " = " + currency_Exchange_Id;
            db = this.getReadableDatabase();
            Cursor ct = db.rawQuery(sqlt, null);
            Log.e("Count T", " " + ct.getCount());
            ct.close();
            String sql3 = "select * from " + TABLE_CURRENCY_EXCHANGE + " where " + CURRENCY_DATE + " = \'" + data + "\' and " + CURRENCY_EXCHANGE_ID + " = " + currency_Exchange_Id;

            db1 = this.getReadableDatabase();
            c3 = db1.rawQuery(sql3, null);

            Log.e("Count ", " " + c3.getCount());
            if (c3.getCount() > 0) {
                c3.moveToFirst();
                do {

                    Log.e("by Date", " " + c3.getDouble(c3.getColumnIndex(CURRENCY_BUY)));

                    currency.setBuy(c3.getDouble(c3.getColumnIndex(CURRENCY_BUY)));
                    currency.setSell(c3.getDouble(c3.getColumnIndex(CURRENCY_SELL)));


                    c3.moveToNext();
                }

                while (!c3.isAfterLast());


            }
            currencyArrayList.add(currency);

            c2.moveToNext();


        }
        while (!c2.isAfterLast());
        c3.close();
        c2.close();
        db1.close();
        db.close();

        return currencyArrayList;

    }

    public ArrayList<Integer> getAllCurrency() {
        ArrayList<Integer> currencyArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_CURRENCY_TYPE;
        Cursor c = db.rawQuery(sql, null);
        //Log.e("GetAllCurrency Size", " " + c.getCount());
        c.moveToFirst();
        do {

            int currencyId = c.getInt(c.getColumnIndex(CURRENCY_ID));
            //  Log.e("Testtt Currency Id", " " + currencyId);

            currencyArrayList.add(currencyId);
            c.moveToNext();
        }
        while (!c.isAfterLast());
        // Log.e(" size", " :" + currencyArrayList.size());
//        String sqld= "select * from " + TABLE_CURRENCY_EXCHANGE;
//          Cursor cd= db.rawQuery(sqld, null);
//            cd.moveToFirst();
//            while(!cd.isAfterLast()) {
//               Log.e("ALLDate", " " + cd.getString(cd.getColumnIndex(CURRENCY_DATE)));
//            }
        c.close();
        db.close();
        return currencyArrayList;
    }


    public void deleteCurrencyType() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_CURRENCY_TYPE;
        db.execSQL(sql);
        db.close();
    }

    public void deleteCurrency() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_CURRENCY;
        db.execSQL(sql);
        db.close();
    }

    //delete central bank
    public void deleteCentralData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "delete from " + TABLE_CENTRAL;
        db.execSQL(delete);
        db.close();
    }

    // insert CurrencyType table
    public void insertCurrencyType() {
        Log.e("status", "reach in currency type");
        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "insert into " + TABLE_CURRENCY_TYPE + " (currency_id,currency_type,currency_logo)values" +
                "(1,'USD',1)";
        String insert2 = "insert into " + TABLE_CURRENCY_TYPE + " (currency_id,currency_type,currency_logo)values" +
                "(2,'EUR',2)";
        String insert3 = "insert into " + TABLE_CURRENCY_TYPE + " (currency_id,currency_type,currency_logo)values" +
                "(3,'SGD',3)";
        String insert4 = "insert into " + TABLE_CURRENCY_TYPE + " (currency_id,currency_type,currency_logo)values" +
                "(4,'THB',4)";
        String insert5 = "insert into " + TABLE_CURRENCY_TYPE + " (currency_id,currency_type,currency_logo)values" +
                "(5,'MYR',5)";
        db.execSQL(insert);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);
        db.close();

    }

    //to get Currency Logo
    public int getCurrencyLogo(int currency_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from the " + TABLE_CURRENCY_TYPE + " where " + CURRENCY_ID + " = " + currency_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        int logo = c.getInt(c.getColumnIndex(CURRENCY_ID));
        c.close();
        db.close();
        return logo;
    }
    // to insert currency Table

    public void insertCurrency() {
        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(1,1,1)";
        String insert2 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(2,2,1)";
        String insert3 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(3,3,1)";
        String insert4 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(4,4,1)";
        String insert5 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(5,1,2)";
        String insert6 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(6,2,2)";
        String insert7 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(7,3,2)";
        String insert8 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(8,4,2)";
        String insert9 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(9,5,2)";
        String insert10 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(10,1,3)";
        String insert11 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(11,2,3)";
        String insert12 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(12,3,3)";
        String insert13 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(13,4,3)";
        String insert14 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(14,1,5)";
        String insert15 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(15,2,5)";
        String insert16 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(16,3,5)";
        String insert17 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(17,4,5)";
        String insert18 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(18,5,5)";
        String insert19 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(19,1,6)";
        String insert20 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(20,2,6)";
        String insert21 = "insert into " + TABLE_CURRENCY + " (currency_exchange_id,currency_id,bank_id)values"
                + "(21,3,6)";
        db.execSQL(insert);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);
        db.execSQL(insert6);
        db.execSQL(insert7);
        db.execSQL(insert8);
        db.execSQL(insert9);
        db.execSQL(insert10);
        db.execSQL(insert11);
        db.execSQL(insert12);
        db.execSQL(insert13);
        db.execSQL(insert14);
        db.execSQL(insert15);
        db.execSQL(insert16);
        db.execSQL(insert17);
        db.execSQL(insert18);
        db.execSQL(insert19);
        db.execSQL(insert20);
        db.execSQL(insert21);
        db.close();

    }

    //get currency count
    public int getCurrencyCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CURRENCY;
        Cursor c = db.rawQuery(sql, null);
        Log.e("curency ", "" + c.getCount());
        int count = c.getCount();
        c.close();
        db.close();
        return count;
    }

    //get currencytype count
    public int getCurrencyTypeCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CURRENCY_TYPE;
        Cursor c = db.rawQuery(sql, null);
        Log.e("c_ type", "" + c.getCount());
        c.close();
        db.close();
        return c.getCount();
    }

    //to insert currencyExchange Rate by Date
    public void insertCurrencyExchange() {

        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(1,'October 28, 2017',1324,1456)";
        String insert2 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(2,'October 28, 2017',1325,1456)";
        String insert3 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(3,'October 28, 2017',1326,1457)";
        String insert4 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(4,'October 28, 2017',1324,1456)";
        String insert5 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(1,'October 28, 2017',1324,1456)";
        String insert6 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(2,'October 28, 2017',1324,1456)";
        String insert7 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(3,'October 28, 2017',1324,1456)";
        String insert8 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(8,'October 28, 2017',1324,1456)";
        String insert9 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(9,'October 28, 2017',1324,1456)";
        String insert10 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(10,'October 28, 2017',1324,1456)";
        String insert11 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(11,'October 26, 2017',1324,1456)";
        String insert12 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(12,'October 28, 2017',1324,1456)";
        String insert13 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(13,'October 28, 2017',1324,1456)";
        String insert14 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(14,'October 28, 2017',1324,1456)";
        String insert15 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(15,'October 28, 2017',1324,1456)";
        String insert16 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(16,'October 28, 2017',1324,1456)";
        String insert17 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(17,'October 28, 2017',1324,1456)";
        String insert18 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(18,'October 28, 2017',1324,1456)";
        String insert19 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(19,'October 28, 2017',1324,1456)";
        String insert20 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(20,'October 28, 2017',1324,1456)";
        String insert21 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(21,'October 28, 2017',2324,2456)";
        String insert22 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(1,'October 28, 2017',1324,1456)";
        String insert23 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(2,'October 28, 2017',1324,1456)";
        String insert25 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(3,'October 28, 2017',1324,1456)";
        String insert26 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(4,'October 28, 2017',1326,1456)";
        String insert27 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(5,'October 28, 2017',1324,1456)";
        String insert28 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(6,'October 28, 2017',1324,1456)";
        String insert29 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(7,'October 28, 2017',1324,1456)";
        String insert30 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(8,'October 28, 2017',1324,1456)";
        String insert31 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(9,'October 28, 2017',1324,1456)";
        String insert32 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(10,'October 28, 2017',1324,1456)";
        String insert33 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(11,'October 28, 2017',1324,1456)";
        String insert34 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(12,'October 28, 2017',1324,1456)";
        String insert35 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(13,'October 28, 2017',1324,1456)";
        String insert36 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(14,'October 28, 2017',1324,1456)";
        String insert37 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(15,'October 27, 2017',1324,1456)";
        String insert38 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(16,'October 27, 2017',1334,1456)";
        String insert39 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(17,'October 27, 2017',1314,1456)";
        String insert40 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(18,'October 27, 2017',1324,1456)";
        String insert41 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(19,'October 27, 2017',1324,1456)";
        String insert42 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(20,'October 27, 2017',1324,1456)";
        String insert24 = "insert into " + TABLE_CURRENCY_EXCHANGE + " (currency_exchange_id,currency_date,buy,sell)values"
                + "(21,'October 27, 2017',1324,1455)";


        db.execSQL(insert);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);
        db.execSQL(insert6);
        db.execSQL(insert7);
        db.execSQL(insert8);
        db.execSQL(insert9);
        db.execSQL(insert10);
        db.execSQL(insert11);
        db.execSQL(insert12);
        db.execSQL(insert13);
        db.execSQL(insert14);
        db.execSQL(insert15);
        db.execSQL(insert16);
        db.execSQL(insert17);
        db.execSQL(insert18);
        db.execSQL(insert19);
        db.execSQL(insert20);
        db.execSQL(insert21);
        db.execSQL(insert22);
        db.execSQL(insert23);
        db.execSQL(insert24);
        db.execSQL(insert25);
        db.execSQL(insert26);
        db.execSQL(insert27);
        db.execSQL(insert28);
        db.execSQL(insert29);
        db.execSQL(insert30);
        db.execSQL(insert31);
        db.execSQL(insert32);
        db.execSQL(insert33);
        db.execSQL(insert34);
        db.execSQL(insert35);
        db.execSQL(insert36);
        db.execSQL(insert37);
        db.execSQL(insert38);
        db.execSQL(insert39);
        db.execSQL(insert40);
        db.execSQL(insert41);
        db.execSQL(insert42);
        db.close();
    }


    //get currency count
    public int getCurrencyExchange() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CURRENCY_EXCHANGE;
        Cursor c = db.rawQuery(sql, null);
        Log.e("c_Exchange type", "" + c.getCount());
        int count = c.getCount();
        c.close();
        db.close();
        return count;

    }

    public ArrayList<Integer> getArroundLocation(String tableName, PointF p1, PointF p2, PointF p3, PointF p4) {
        float latitude = 1, longitude = 1;
        PointF around;
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Integer> arroundList = new ArrayList<>();

        Log.e("TAble", "" + tableName);
        String sql = "select * from " + tableName;
        Cursor c = db.rawQuery(sql, null);
        Log.e("TESSS", "" + c.getCount());

        if (c.moveToFirst()) {
            do {
                latitude = c.getFloat(c.getColumnIndex("latitude"));
                longitude = c.getFloat(c.getColumnIndex("longitude"));
                around = new PointF(latitude, longitude);
                if (around.x > p3.x && around.x < p1.x && around.y < p2.y && around.y > p4.y) {
                    arroundList.add(c.getInt(0));
                    //}
                }
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        Log.e("arroundListSize", " " + arroundList.size());
        return arroundList;
    }

    //get all atm data
    public ArrayList<ATM> getAllATMAddress() {
        ArrayList<ATM> atmData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_ATM + " e ; ";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("ATM Search cursor ", String.valueOf(cursor.getCount()));

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int atm_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = cursor.getString(8);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                double lat = cursor.getDouble(4);
                double longitude = cursor.getDouble(5);
                int t_id = cursor.getInt(6);
                int c_id = cursor.getInt(7);
                ATM atm = new ATM(atm_id, bank_name, name, address, lat, longitude, t_id, c_id);
                atmData.add(atm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("ATM Search data ", String.valueOf(atmData.size()));
        return atmData;
    }


    //to get nearest Branch  Information
    public Branch getBranchInfo(int branch_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Branch b = new Branch();

        String sql = "select * from " + TABLE_BRANCH + " where " + BRANCH_ID + " = " + branch_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            b.setBranchName(c.getString(c.getColumnIndex(BRANCH_NAME)));
            b.setBankName(getBankName(c.getInt(c.getColumnIndex(BANK_ID))));
            b.setBranchAddress(c.getString(c.getColumnIndex(BRANCH_ADDRESS)));
            b.setBranchPhNo(c.getString(c.getColumnIndex(BRANCH_CONTACT)));
            b.setBranchLatitude(c.getDouble(c.getColumnIndex(BRANCH_LAT)));
            b.setBranchLongitude(c.getDouble(c.getColumnIndex(BRANCH_LOG)));
            c.moveToNext();
        }
        c.close();
        db.close();
        return b;

    }


    //to get nearest ATM  Information
    public ATM getATMInfo(int atm_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        ATM atm = new ATM();

        String sql = "select * from " + TABLE_ATM + " where " + ATM_ID + " = " + atm_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            atm.setAtmName(c.getString(c.getColumnIndex(ATM_NAME)));
            atm.setBankName(getBankName(c.getInt(c.getColumnIndex(BANK_ID))));
            atm.setAtmAddress(c.getString(c.getColumnIndex(ATM_ADDRESS)));
            atm.setAtmLatitude(c.getDouble(c.getColumnIndex(ATM_LAT)));
            atm.setAtmLongitude(c.getDouble(c.getColumnIndex(ATM_LOG)));
            c.moveToNext();
        }
        c.close();
        db.close();
        return atm;
    }


    //to get Exchange Info
    public Exchange getExchangeInfo(int exchange_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Exchange exchange = new Exchange();

        String sql = "select * from " + TABLE_EXCHANGE + " where " + EXCHANGE_ID + " = " + exchange_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            exchange.setExchangeName(c.getString(c.getColumnIndex(EXCHANGE_NAME)));
            exchange.setBankName(getBankName(c.getInt(c.getColumnIndex(BANK_ID))));
            exchange.setExchangeAddress(c.getString(c.getColumnIndex(EXCHANGE_ADDRESS)));
            exchange.setExchangeOpenTime(c.getString(c.getColumnIndex(EXCHANGE_OPENING_TIME)));
            exchange.setExchangeLatitude(c.getDouble(c.getColumnIndex(EXCHANGE_LAT)));
            exchange.setExchangeLongitude(c.getDouble(c.getColumnIndex(EXCHANGE_LOG)));
            c.moveToNext();
        }
        c.close();
        db.close();
        return exchange;

    }

    public void insertCurrencyExchange(int currency_id, String date, double buy, double sell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CURRENCY_EXCHANGE_ID, currency_id);
        values.put(CURRENCY_DATE, date);
        values.put(CURRENCY_BUY, buy);
        values.put(CURRENCY_SELL, sell);
        db.insert(TABLE_CURRENCY_EXCHANGE, CURRENCY_DETAIL_ID, values);
        Log.e(" Currency exchange ", "Inserted");
        db.close();
    }

    //get All Branch data for all banks
    public ArrayList<Branch> getAllBranchAddress() {
        ArrayList<Branch> branchData = new ArrayList<>();
        Log.e("For all branch", "true");
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_BRANCH + " e ; ";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Branch Search cursor ", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int branch_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);

                Log.e("Branch Name ", cursor.getString(2));
                Log.e("Bank id is", String.valueOf(cursor.getInt(1)));
                String bank_name = cursor.getString(9);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String contact = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Branch branch = new Branch(branch_id, bank_name, name, address, contact, lat, longitude, t_id, c_id);
                branchData.add(branch);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("Branch Search data all ", String.valueOf(branchData.size()));
        return branchData;
    }

    //get exchange data of specific bank
    public ArrayList<Exchange> getAllExchangeAddress() {
        //Log.e("bank id in db",String.valueOf(bank_id));
        ArrayList<Exchange> exchangeData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *," +
                "(SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = e." + BANK_ID + ") as bankname FROM " + TABLE_EXCHANGE + " e ; ";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("Exchange Search cursor ", String.valueOf(cursor.getCount()));

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int exchange_id = cursor.getInt(0);
                int b_id = cursor.getInt(1);
                String bank_name = cursor.getString(9);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String opening_time = cursor.getString(4);
                double lat = cursor.getDouble(5);
                double longitude = cursor.getDouble(6);
                int t_id = cursor.getInt(7);
                int c_id = cursor.getInt(8);
                Exchange exchange = new Exchange(exchange_id, bank_name, name, address, opening_time, lat, longitude, t_id, c_id);
                exchangeData.add(exchange);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // Log.i(" Exchange data search",String.valueOf(exchangeData.size()));
        return exchangeData;
    }

    //check central data
    public boolean isCentralTableEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + UNIT_CURRENCY_ID + " FROM " + TABLE_CENTRAL;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() == 0) {
            return true;
        }
        return false;
    }


    //to get all loan by bank
    public ArrayList<String> getAllLoanByBank(int bank_id) {
        ArrayList<String> allLoan = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select * from " + TABLE_LOAN + " where " + BANK_ID + " = " + bank_id;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                allLoan.add(cursor.getString(cursor.getColumnIndex(LOAN_NAME)));

            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return allLoan;
    }

    //to get loan_id of the loan
    public int getLoanID(String loanName, int bank_id) {
        int loan_id = 1;
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select * from " + TABLE_LOAN + " where " + BANK_ID + " = " + bank_id + " and " + LOAN_NAME + " = " + " \'"
                + loanName + "\'";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                loan_id = cursor.getInt(0);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return loan_id;
    }

    //to get loan Type of the loan_id
    public String getLoanType(int loan_id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_LOAN + " where " + LOAN_ID + " = " + loan_id;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        Log.e("loan_type ", " is " + c.getString(c.getColumnIndex(LOAN_TYPE)));
        String loanName = c.getString(c.getColumnIndex(LOAN_TYPE));
        c.close();
        db.close();
        return loanName;

    }

//to get interest Rate of

    public double getLoanInterest(String loanName) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + LOAN_INTEREST + " from " + TABLE_LOAN + " where " + LOAN_TYPE + " =  \'" + loanName + "\'";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        Log.e("loan_type ", " is " + c.getDouble(c.getColumnIndex(LOAN_INTEREST)));
        double loanInterest = c.getDouble(c.getColumnIndex(LOAN_INTEREST));
        c.close();
        db.close();
        return loanInterest;

    }

    //to insert Loan
    public void insertLoan() {
        Log.e("START lOAN", "JGGKF");

        SQLiteDatabase db = this.getWritableDatabase();
        String insert1 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(1,'Hire Purchase','kbz_hire',9,1)";
        String insert2 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(2,'OverDraft','kbz_overdraft',13,1)";
        String insert3 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(3,'Term Loan','yoma_term_loan',12,2)";
        String insert4 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(4,'OverDraft','yoma_overdraft',12,2)";
        String insert5 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(5,'Temporary OverDraft','yoma_temporary_overdraft',12,2)";
        String insert6 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(6,'Lien Loan','yoma_lien_loan',13,2)";
        String insert7 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(7,'Share Loan','yoma_share_loan',13,2)";
        String insert8 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(8,'Gold loan','yoma_gold_loan',13,2)";
        String insert9 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(9,'Pledge Loan','yoma_pledge_loan',13,2)";
        String insert10 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(10,'Home Loan','yoma_home_loan',13,2)";
        String insert11 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(11,'Hire Purchase','yoma_hire',7,2)";
        String insert12 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(12,'Personal Loan','agd_personal_loan',12,3)";
        String insert13 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(13,'Overdraft','agd_overdraft',12,3)";
        String insert14 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(14,'Hire Purchase','agd_hire',9,3)";
        String insert15 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(15,'Gold Financing','agd_gold_loan',12,3)";
        String insert16 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(16,'Purchase of Central Bank of Myanmar Treasury Bond','agd_treasure',11.5,3)";
        String insert17 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(17,'Gold Financing','cb_gold_loan',13,5)";
        String insert18 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(18,'Easi Credit Loan','cb_easi_credit_loan',13,5)";
        String insert19 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(19,'Economy Financing','cb_economy_loan',10,5)";
        String insert20 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(20,'Trade Facility','cb_trade_loan',12,5)";
        String insert21 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(21,'Hire Purchase','cb_hire',13,5)";
        String insert22 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(22,'Education Loan','cb_education_loan',10,5)";
        String insert23 = "insert into " + TABLE_LOAN + " (loan_id,loan_name,loan_type,loan_interest,bank_id)values"
                + "(23,'Education Loan','aya_education_loan',13,6)";

        db.execSQL(insert1);
        Log.e("START lOAN", "111111");
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);
        db.execSQL(insert6);
        db.execSQL(insert7);
        db.execSQL(insert8);
        db.execSQL(insert9);
        db.execSQL(insert10);
        db.execSQL(insert11);
        db.execSQL(insert12);
        db.execSQL(insert13);
        db.execSQL(insert14);
        db.execSQL(insert15);
        db.execSQL(insert16);
        db.execSQL(insert17);
        db.execSQL(insert18);
        db.execSQL(insert19);
        db.execSQL(insert20);
        db.execSQL(insert21);
        db.execSQL(insert22);
        db.execSQL(insert23);
        Log.e("START lOAN", "JGGKF");
        //db.close();
    }


    //for insert account suggestion data
    public void insertAccountSuggestionData(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACC_ID, account.getAccId());
        int bank_id = findBankIndex(account.getBankName());
        contentValues.put(BANK_ID, bank_id);
        contentValues.put(ACC_NAME, account.getName());
        contentValues.put(ACCOUNT_PURPOSE, account.getAccountPurpose());
        contentValues.put(ACC_DEPOSIT, account.getInitialDeposit());
        contentValues.put(ACC_TYPE, account.getType());
        contentValues.put(ACC_DEPOSIT_TYPE, account.getDepositTime());
        contentValues.put(ACC_WITHDRAW_TYPE, account.getWithdrawTime());
        contentValues.put(ACC_DESCRIPTION, account.getDescription());
        db.insert(TABLE_ACCOUNT_DES, null, contentValues);
        Log.e("Status", "acc description inserted");
    }


    //get purpose account suggestion
    public ArrayList<Integer> getPurposeSuggestionId(String selectedPurpose) {
        ArrayList<Integer> suggest_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + ACC_ID + " FROM " + TABLE_ACCOUNT_DES + " WHERE " + ACCOUNT_PURPOSE + " LIKE \'%" + selectedPurpose + "%\'";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                suggest_list.add(cursor.getInt(cursor.getColumnIndex(ACC_ID)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return suggest_list;
    }

    //get amount account suggestion
    public ArrayList<Integer> getAmountSuggestionId(int startAmount, int endAmount) {
        ArrayList<Integer> suggest_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + ACC_ID + " FROM " + TABLE_ACCOUNT_DES + " WHERE " + ACC_DEPOSIT + " <= " + endAmount;
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("STATUS", "Reach");
        Log.e("Amount suggest count", "" + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                suggest_list.add(cursor.getInt(cursor.getColumnIndex(ACC_ID)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        Log.e("Suggest Amount", "" + suggest_list.size());
        return suggest_list;
    }

    //get type account suggestion
    public ArrayList<Integer> getTypeSuggestion(String type) {
        ArrayList<Integer> suggest_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + ACC_ID + " FROM " + TABLE_ACCOUNT_DES + " WHERE " + ACC_TYPE + " LIKE \'%" + type + "%\'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            suggest_list.add(cursor.getInt(cursor.getColumnIndex(ACC_ID)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return suggest_list;
    }

    //get deposit frequency
    public ArrayList<Integer> getDepositFrequency(String time) {
        ArrayList<Integer> suggest_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + ACC_ID + " FROM " + TABLE_ACCOUNT_DES + " WHERE " + ACC_DEPOSIT_TYPE + " LIKE \'%" + time + "%\'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                suggest_list.add(cursor.getInt(cursor.getColumnIndex(ACC_ID)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return suggest_list;
    }


    //get withdraw frequency
    public ArrayList<Integer> getWithdrawFrequency(String time) {
        ArrayList<Integer> suggest_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + ACC_ID + " FROM " + TABLE_ACCOUNT_DES + " WHERE " + ACC_WITHDRAW_TYPE + " LIKE \'%" + time + "%\'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                suggest_list.add(cursor.getInt(cursor.getColumnIndex(ACC_ID)));
                Log.e("Withdraw account id", "" + cursor.getInt(cursor.getColumnIndex(ACC_ID)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return suggest_list;
    }

    //get account Name and account id
    public void showAccount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + ACC_ID + " , " + ACC_NAME + " FROM " + TABLE_ACCOUNT_DES;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int acc_id = cursor.getInt(0);
                String acc_name = cursor.getString(1);
                Log.e("Acc_id", "" + acc_id);
                Log.e("Acc_name", "" + acc_name);
            } while (cursor.moveToNext());
        }

    }

    //get account Name and bank Name
    public Account getAccountName(int id) {
        Account account = new Account();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " IN (SELECT " + BANK_ID + " FROM " + TABLE_ACCOUNT_DES + " WHERE " + ACC_ID + "=" + id + " );";
        String sqlAccount = "SELECT " + ACC_NAME + ", " + ACC_DESCRIPTION + " FROM " + TABLE_ACCOUNT_DES + " WHERE " + ACC_ID + "=" + id;
        Cursor cursor = db.rawQuery(sql, null);
        Cursor cursorAccount = db.rawQuery(sqlAccount, null);
        String bName = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            bName = cursor.getString(cursor.getColumnIndex(BANK_NAME));
            account.setBankName(bName);

            Log.e("Bank Name is", "" + cursor.getString(cursor.getColumnIndex(BANK_NAME)));
        }
        if (cursorAccount.getCount() > 0) {
            cursorAccount.moveToFirst();
            String name = cursorAccount.getString(0);
            String description = cursorAccount.getString(1);
            Log.e("Account Name is", name);
            account.setName(bName + "   " + name);
            account.setDescription(description);
        }


        cursor.close();
        db.close();
        return account;
    }

    //delete account suggestion
    public void deleteAccountSuggestion() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = " DELETE FROM " + TABLE_ACCOUNT_DES;
        db.execSQL(sql);
        db.close();

    }

    //get account suggestion description
    public ArrayList<Account> getAccountDescription(int bank_id) {
        ArrayList<Account> acc_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = " SELECT " + ACC_NAME + "," + ACC_DESCRIPTION + " FROM " + TABLE_ACCOUNT_DES + " WHERE " + BANK_ID + "=" + bank_id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Account account = new Account(cursor.getString(cursor.getColumnIndex(ACC_NAME)), cursor.getString(cursor.getColumnIndex(ACC_DESCRIPTION)));
                acc_list.add(account);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return acc_list;
    }
}
