package com.fourllc.donate.NetworkingUtils;

/**
 * Created by aaronbrecher on 4/9/18.
 */

public class JustGiving {
    public static final String DONATION_BASE = "https://link.justgiving.com/v1/fundraisingpage/donate/pageId/10830205?";
    public static final String DONATION_AMOUNT = "amount=2.00";
    public static final String DONATION_CURRENCY = "&currency=USD";
    public static final String DONATION_REFERENCE = "4llcapp";
    public static final String DONATION_EXIT_URL = "&exitUrl=fourllc://donationreceived%3FjgDonationId%3DJUSTGIVING-DONATION-ID";
    public static final String DONATION_URL = DONATION_BASE+DONATION_AMOUNT+DONATION_CURRENCY+"&reference="+DONATION_REFERENCE+DONATION_EXIT_URL;

    public static final String DONATION_ID_PARAMETER = "jgDonationId";

    public static final String API_USER_NAME = "";
    public static final String API_SECRET_KEY = "";

}
