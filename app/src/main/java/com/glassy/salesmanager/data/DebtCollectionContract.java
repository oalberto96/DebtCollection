package com.glassy.salesmanager.data;

import android.provider.BaseColumns;

/**
 * Created by glassy on 12/17/17.
 */

public class DebtCollectionContract {

    public static final class Client implements BaseColumns{
        public static final String TABLE_NAME = "client";
        public static final String COLUMN_FIRST_NAME = "firstName";
        public static final String COLUMN_LAST_NAME = "lastName";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE_NUMBER = "phoneNumber";
        public static final String COLUMN_NOTES = "notes";
        public static final String COLUMN_TIN = "tin";
    }
}
