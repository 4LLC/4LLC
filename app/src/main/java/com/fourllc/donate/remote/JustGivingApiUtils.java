package com.fourllc.donate.remote;

/**
 * Utility Class for the JustGiving API will instantiate the Retrofit service as well
 */

public class JustGivingApiUtils {

    /*
     *  Statics for the Simple Integration Link
     */
    private static final String SDI_BASE_URL = "https://link.justgiving.com/v1/fundraisingpage/donate/pageId/10830205?";;
    private static final String SDI_DONATION_AMOUNT = "amount=2.00";
    private static final String SDI_DONATION_CURRENCY = "&currency=USD";
    private static final String SDI_DONATION_REFERENCE = "4llcapp";
    private static final String SDI_EXIT_URL = "&exitUrl=fourllc://donationreceived%3FjgDonationId%3DJUSTGIVING-DONATION-ID";
    public static final String SDI_URL = SDI_BASE_URL + SDI_DONATION_AMOUNT + SDI_DONATION_CURRENCY + "&reference=" + SDI_DONATION_REFERENCE + SDI_EXIT_URL;
    public static final String DONATION_ID_PARAMETER = "jgDonationId";

    /*
     * Statics for the API
     */
    public static final String API_SECRET_KEY = "6719d292";

    private static final String API_BASE_URL = "https://api.justgiving.com";
    public static final String API_DONATION_BY_ID = "/v1/donation";

    public static JustGivingService getJustGivingService(){
        return RetrofitClient.getClient(API_BASE_URL).create(JustGivingService.class);
    }
}
