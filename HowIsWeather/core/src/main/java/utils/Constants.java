package utils;

/**
 * Created by: Sumit Jain on 24/3/16.
 */
public interface Constants {

    String PACKAGE_NAME = "com.ovunque.parkwheels";
    String CURRENT_VERSION = "current_version";
    String IS_FURTHER_OPEN = "is_first_open";
    String APP_OPEN_COUNT = "app_open_count";
    String UNIQUE_ID_UPDATED = "unique_id_updated";
    String UNIQUE_ID = "unique_id";
    String DEVICE_NAME = "device_name";
    String USER_APP_MAP_CREATED = "user_app_map_created";
    String OK = "OK";
    String USER_ID = "user_id";
    String APP_USER = "app_user";
    String PHONE_NUMBER = "phone_number";
    String USER_TYPE = "user_type";
    String CORPORATE_USER = "corporate_user";
    String SOCIETY_USER = "society_user";
    String NORMAL_USER = "normal_user";
    String MALL_USER = "mall_user";
    String HYBRID_USER = "hybrid_user";
    String USER_GENDER = "Gender";
    String WIFI_CONNECTED = "wifiConnected";
    String WIFI_TURNED_ON = "wifi_turned_on";
    String NO_PW_WIFI_FOUND = "no_pw_wifi_found";
    String PW_WIFI_FOUND_COUNT = "pw_wifi_found_count";
    String WIFI_REMEMBERED_AND_CONNECTED = "wifi_remembered_and_connected";
    String PAYTM_LINKED = "paytm_linked";
    String SERIAL_NUMBER = "serial_number";

    String EMAIL_MANDATORY = "email_optional";
    String EMAIL_DOMAINS_EXCLUDED = "emil_domains_excluded";
    String EMAIL_DOMAINS_INCLUDED = "email_domains_included";
    String TAG_ID = "tag_id";
    String AUTH_TOKEN = "auth_token";
    String SESSION_EXPIRED = "session_expired";
    String USER_DETAILS = "user_details";
    String USER_SETTINGS = "user_settings";
    String VERIFY_OTP_TARGET = "verify_otp_target";
    int VERIFY_USER_REGISTRATION = 1;
    int VERIFY_USER_LOGIN = 2;
    String IS_USER_VERIFIED = "is_user_verified";
    String SOCIETY_INFO = "society_info";
    String VISITOR_DETAILS = "visitor_details";
    String VEHICLES_LIST = "vehicles_list";
    String BRANDS_LIST = "brands_list";
    String MODELS_LIST = "models_list";
    String CITY_LIST = "city_list";
    String HOME_SCREEN_ANIMATION = "home_screen_animation";
    String CLICKED_MALL_ID = "clicked_mall_id";
    String CLICKED_MALL_NAME = "clicked_mall_name";
    String REFERRAL_CODE = "referral_code";
    String SELECTED_VEHICLE = "selected_vehicle";
    String CITY_SELECTION_SOURCE = "city_selection_source";
    String BOOKING_LIST_SOURCE = "booking_list_source";
    String IS_INTERNET_CONNECTED = "is_internet_connected";
    int ALL_BOOKINGS_CLICKED = 1;
    int VIEW_DETAILS_CLICKED = 2;

    int SOURCE_MALL_ACTIVITY = 1;
    int SOURCE_SERVICE_ACTIVITY = 2;
    String USER_ANALYTICS_MAPPED = "user_analytics_mapped";

    String SOURCE_PARKING_FRAGMENT = "entry_source_parking_fragment";
    int PARKING_FRAGMENT_NORMAL_ENTRY = 1;
    int PARKING_FRAGMENT_SEARCH_ENTRY = 2;
    String SEARCH_LATITUDE = "search_latitude";
    String SEARCH_LONGITUDE = "search_longitude";
    String SEARCH_PARKING_NAME = "search_parking_name";
    String LAST_EVENT_ID_FROM_PI = "last_event_id_from_pi";
    String USER_PROFILE_UPDATED = "user_profile_updated";

    String IS_WIFI_ON = "is_wifi_on";
    String PW_WIFI_FOUND = "is_pw_wifi_found";
    String IS_WIFI_CONNECTED = "is_wifi_connected";
    String LAST_NOTIFICATION_TIME = "last_notification_time";
    String PW_WIFI_MISSED = "pw_wifi_missed";
    String CONNECTED_NET_ID = "connected_net_id";


    String CLICKED_SERVICE_CATEGORY = "clicked_service_category";
    int GENERAL_SERVICE_CLICKED = 1;
    int VEHICLE_WASH_CLICKED = 2;
    int VEHICLE_REPAIR_CLICKED = 3;
    int VEHICLE_EMERGENCY_CLICKED = 4;
    String SERVICE_DETAILS = "service_details";
    String BOOKING_ID = "booking_id";
    String REQUEST_TAG_CITY_LIST = "request_tag_city";

    String EDITABLE_VEHICLE = "editable_vehicle";

    String SOCIETY_MENU_CLICK_ACTION = "society_menu_click_action";
    int CLICK_ALL_COMPLAINTS = 1;
    int CLICK_BOOK_COMPLAINT = 2;
    int CLICK_ADD_VISITOR = 3;
    int CLICK_ALL_VISITORS = 4;
    String GALLERY_LIST = "gallery_list";

    String HOME_ADDRESS_ADDED = "home_address_added";
    String ADD_HOME_CARD_COUNT = "add_home_card_count";
    String ADD_HOME_CANCEL_COUNT = "add_home_cancel_count";

    String NAVIGATE_TRAFFIC = "navigate_traffic";
    String TRAFFIC_NOT_USEFUL = "traffic_not_useful";


    String NEW_REGISTRATION = "new_registration";
    String FIRST_VEHICLE_ADDED = "first_vehicle_added";

    /**
     * Google Analytics Actions
     */
    String ACTION_RESUME = "View";
    String ACTION_PROFILE_UPDATE = "Profile Update";
    String ACTION_PASSWORD_CHANGE = "Password Change";
    String ACTION_LOGOUT = "Logout User";

    /**
     * Constants related to GCM
     */

    // Broadcast receiver intent filters
    String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    String PUSHY_REGISTRATION_COMPLETE = "registrationComplete";
    String PUSH_NOTIFICATION = "pushNotification";
    String KEY_NOTIFICATIONS = "notifications";
    String SMS_NOTIFICATION = "smsNotification";
    String SMS_OTP_RECEIVED = "smsOtpReceived";
    Boolean APPEND_NOTIFICATION_MESSAGES = false;
    String WIFI_CONNECTION_INITIATE = "wifiConnectionInitiate";
    int NOTIFICATION_ID_PARKING_UPDATE = 100;
    int NOTIFICATION_ID_ENTRY_EXIT = 101;
    int NOTIFICATION_PARKING_SPACE_STATUS = 102;
    int NOTIFICATION_ID_BIG_IMAGE = 103;
    int NOTIFICATION_VEHICLE_THEFT = 104;
    int NOTIFICATIONS_NEARBY_PARKING = 105;

    /**
     * Types of push messages
     */
    int PUSH_TYPE_ENTRY = 1;
    int PUSH_TYPE_EXIT = 2;
    int PUSH_TYPE_RATE_APP = 4;
    int PUSH_TYPE_VEHICLE_THEFT = 6;
    int PUSH_TYPE_UPDATE_APP = 7;
    int PUSH_TYPE_PARK_REMINDER = 8;
    int PUSH_TYPE_WALLET_GENERIC = 9;
    int PUSH_TYPE_MY_PASSES = 10;
    int PUSH_TYPE_RECHARGE_WALLET_ONLINE = 11;
    int PUSH_TYPE_My_VEHICLES = 12;
    int PUSH_TYPE_MALLS_GENERIC = 13;
    int PUSH_TYPE_VEHICLE_SERVICE = 14;
    int PUSH_TYPE_REFER_EARN = 15;
    int PUSH_TYPE_FEEDBACK = 16;
    int PUSH_TYPE_TRIPS_LIST = 17;
    int PUSH_TYPE_FIND_PARKING = 18;
    int PUSH_TYPE_MY_PROFILE = 19;
    int PUSHY_TYPE_SILENT = 20;
    int PUSH_TYPE_MY_APARTMENT = 21;
    int PUSH_TYPE_PARKING_UPDATE = 22;
    int PUSH_TYPE_SOCIETY_VISITOR_REQUEST = 5;
    int PUSH_TYPE_SOCIETY_CAB_REQUEST = 3;
    int PUSH_TYPE_OFFER = 23;
    int PUSH_TYPE_ADD_ADDRESS = 24;
    int PUSH_TYPE_TIP = 25;
    int PUSH_TYPE_NAVIGATE_TRAFFIC = 26;
    int PUSH_TYPE_BOOKING_LIST = 27;
    int PUSH_TYPE_BATTERY_OPTIMIZATION = 28;
    int PUSH_TYPE_SETTINGS = 29;
    int PUSH_TYPE_GENERIC = 100;

    //Check verify OTP fragment
    String ACTIVE_VERIFY_FRAGMENT = "activeVerifyFragment";
    int FRAGMENT_VERIFY_TAG_OTP = 1;
    int FRAGMENT_VERIFY_USER_OTP = 2;

    //Auto fill permissions
    String AUTO_FILL_PERMISSIONS = "auto_fill_permissions";
    int PERMISSIONS_GRANTED = 1;
    int PERMISSIONS_DENIED = 0;

    String IS_USER_REGISTERED = "user_registered_check";
    int USER_REGISTERED = 1;
    int USER_NOT_REGISTERED = 0;

    String SOURCE_RECEIPT = "receipt_source";
    int SOURCE_RECEIPT_LIST = 1;
    int SOURCE_PARK_VEHICLE = 0;

    String SOURCE_ADD_MONEY_FRAGMENT = "source_add_money";

    //Request Tag
    String MALL_SEARCH_REQUEST_TAG = "mall_search_request_tag";

    //Local Notification Types
    String TYPE_PARK_ME = "park_me_detection";

    //Source of activity
    String ACTIVITY_SOURCE_WALLET = "activity_source_wallet";
    String ACTIVITY_SOURCE_SERVICE = "activity_source_service";
    String ACTIVITY_SOURCE_VEHICLES = "activity_source_vehicles";
    String ACTIVITY_SOURCE_REFER_EARN = "activity_source_refer_earn";
    String ACTIVITY_SOURCE_FIND_PARKING = "activity_source_find_parking";
    String ACTIVITY_SOURCE_FEEDBACK = "activity_source_feedback";
    String ACTIVITY_SOURCE_MY_RECEIPT = "activity_source_my_receipt";
    String ACTIVITY_SOURCE_MALLS = "activity_source_malls";
    String ACTIVITY_SOURCE_MY_PROFILE = "activity_source_my_profile";
    String ACTIVITY_SOURCE_MY_PASSES = "activity_source_my_passes";
    String ACTIVITY_SOURCE_MY_ORDERS = "activity_source_my_orders";
    String ACTIVITY_SOURCE_MY_APARTMENT = "activity_source_my_apartment";
    String ACTIVITY_SOURCE_NOTIFICATIONS = "activity_source_notification";
    String ACTIVITY_SOURCE_TIP = "activity_source_tip";
    String ACTIVITY_SOURCE_BATTERY_OPTIMIZATION = "activity_source_battery_optimization";
    String ACTIVITY_SOURCE_SETTINGS = "activity_source_settings";

    int MIN_WALLET_BALANCE = 20;

    String IS_SHORTCUT_CREATED = "is_shortcut_available";
    String SOURCE_PARK_ME_ACTIVITy = "SOURCE_PARK_ME_ACTIVITY";

    String WIFI_PASSWORD = "prkwhls9090";
    String PARK_ME_USED_COUNT = "park_me_used";
    String PARK_ME_SHORTCUT_DENIED = "park_me_shortcut_denied";
    String ENTRY_EXIT_DETERMINE_FAIL_COUNT = "entry_exit_determine_fail_count";
    String HELMET_SAVED_VALUE = "helmet_saved_value";
    String HELMET_OPTION_REMEMBERED = "helmet_option_remembered";
    String PW_ORDER_ID = "pw_order_id";
    String SOURCE_RECHARGE_DETAILS = "source_recharge_details";
    String ADD_MONEY_ACTIVITY = "add_money_activity";
    String MY_ORDERS_ACTIVITY = "my_orders_activity";

    String SELECTED_BOOKING_ID = "selected_booking_id";
    String LAST_BOOKING_ID = "last_booking_id";

    String LOCAL_NOTIFICATION_ID = "local_notification_id";
    String SOURCE_PARK_ME = "source_park_me";
    String SOURCE_NEW_BOOKING = "source_new_booking";
    String SOURCE_BOOKING_DETAILS = "source_booking_details";
    String SOURCE_OVERFLOW_DETAIL = "source_overflow_detail";

    String TERMS_LINK = "terms_link";
    String PRIVACY_POLICY_LINK = "privacy_policy";
    String TERMS_AGREED = "terms_agreed";
    String SHOW_TERMS_DIALOG = "show_terms_dialog";
    String PREFERRED_VEHICLE_TYPE = "preferred_vehicle_type";

    /**
     * Policy Activity
     */
    String POLICY_SOURCE = "policy_source";
    int POLICY_LIST = 2;
    int POLICY_DETAILS = 1;


    /**
     * Constants Related to Answer Events
     */
    String USER_PROFILE_OPEN = "User Profile Open";
    String USER_PROFILE_UPDATE = "User Profile Update";
    String UER_SETTINGS_OPEN = "User Settings Open";
    String USER_SETTINGS_UPDATE = "User Settings Update";
    String APP_OPEN = "App Open";
    String INITIATE_LOGIN = "Initiate Login";
    String INITIATE_REGISTER = "Initiate Register";
    String REGISTER_COMPLETE = "Register Complete";
    String LOGIN_VERIFIED = "Login Verified";
    String REGISTER_VERIFIED = "Register Verified";
    String RESEND_OTP = "Resend OTP";
    String REQUEST_TAG_VERIFIED = "Request Tag Verified";
    String REQUEST_TAG = "Request Tag";
    String HOME_OPEN = "Home Open";
    String CHECKIN_ENTRY_DONE = "Ck Entry Done";
    String CHECKIN_ATTEMPT_ENTRY = "Ck Attempt Entry";
    String CHECKIN_ATTEMPT_EXIT = "Ck Attempt Exit";
    String CHECKIN_EXIT_DONE = "Ck Exit Done";
    String MALLS_LIST_OPENED = "Malls List Open";
    String FOOD_OUTLETS_OPENED = "Food Outlets Open";
    String BRANDS_OPENED = "Brands Open";
    String VEHICLE_LIST_OPENED = "Vehicle List Open";
    String TAG_CONTROL_SETTINGS_UPDATE = "Tag Control Settings Update";
    String VEHICLE_DETAILS_OPENED = "Vehicle Details";
    String VEHICLE_EDIT = "Vehicle Edit";
    String SERVICE_SECTION_OPEN = "Service Section Open";
    String VEHICLE_SERVICE = "Vehicle Service";
    String SERVICE_BOOKING_DETAILS = "Service Booking Details";
    String SERVICE_BOOKING_CANCELLED = "Service Booking Cancelled";
    String EMERGENCY_SERVICE = "Emergency Service";
    String REPAIR_SERVICE = "Repair Service";
    String WALLET_OPEN = "Wallet Open";
    String LOGOUT = "Logout";
    String REFER_OPEN = "Refer And Earn Open";
    String FEEDBACK_OPEN = "Feedback Open";
    String FAQ_OPENED = "FAQS Opened";
    String FAQ_CATEGORY_OPEN = "FAQ Category Open";
    String DETECT_PARKWHEELS_ZONE = "Detect Zone";
    String PW_WIFI_REMEMBER = "Ck PW Wifi Remember";
    String WIFI_ADAPTER_STATUS = "Ck Wifi Adapter Status";
    String MOBILE_DATA_STATUS = "Ck Mobile Data Status";
    String CONNECT_PARKWHEELS_ZONE = "Connect Zone";
    String BOOK_PARKING = "Book Parking";
    String BOOKING_DETAILS = "Park Booking Details";
    String BOOKING_CANCELLED = "Park Booking Cancel";
    String POLICY_LIST_EVENT = "Policy List";
    String POLICY_DETAILS_EVENT = "Policy Details";
    String CREATE_CHECKIN_SHORTCUT = "Create Ck Shortcut";
    String CHECKIN_SHORTCUT_USED = "Ck Shortcut Used";
    String BOOK_PARKING_OPEN = "Book Parking Open";
    String POLICY_LIST_FETCH = "Fetch Policy List";
    String PARK_NEAR = "Near Parking";
    String FIND_PARKING = "Find Parking";

    String BATTERY_OPTIMIZATION_ASKED = "battery_optimization_asked";

    /**
     * Raise ticket
     */
    String ORDER_NUMBER = "order_number";
    String ORDER_TITLE = "order_title";

    String RECHARGE_DETAILS_REQUEST = "recharge_details_request";
    String RECHARGE_DETAILS_ACTIVITY = "recharge_details_activity";
    String IS_CORPORATE_VERIFICATION_DONE = "is_corporate_verified";

    /**
     * Society related
     */
    String CONTACT_PICKED = "contact_picked";
    int REQUEST_CODE_PICK_CONTACT = 999;
    String VISITOR_ACCEPTED = "accepted";
    String VISITOR_REJECTED = "rejected";
    int ACCEPT_ACTION_ID = 1;
    int REJECT_ACTION_ID = 2;

    String NOTIFICATION_ACTION = "notification_action";
    String VISITOR_ACTION_HANDLED = "visitor_action_handled";
    String CAB_ACTION_HANDLED = "cab_action_handled";

    String FCM_TOKEN_REFRESH = "fcm_token_refresh";
    String CURRENT_FCM_TOKEN = "current_fcm_token";
    String FCM_TOKEN_SENT = "fcm_token_sent";

    String IS_NOTIFICATION_PERMISSION_ASKED = "is_notification_permission_asked";
    String BIND_NOTIFICATION_PERMISSION = "bind_notification_permission";
    String IS_HELPER_DELETED = "is_maid_deleted";
    String UPDATE_PARKING_COUNT = "update_parking_status";

    /**
     *  Deep link
     * */

    String CAB_LINK = " http://parkwheels.co.in/api/apartment/cabs";
    String VISITOR_LINK = " http://parkwheels.co.in/api/apartment/visitors";




    String HASH_IT = "kY4AgI6JLK";
    String SECURE_KEY = "NdeTRHVNdg4PkV7bey";

    /**
     *  Paytm related constants
     * */

    // Development
    String DEV_PAYTM_MERCHANT_ID    = "Ovunqu49012274185272";
    String DEV_PAYTM_CHANNEL_ID     = "WAP";
    String DEV_PAYTM_WEBSITE_APP    = "APPSTAGING";
    String DEV_PAYTM_INDUSTRY_TYPE  = "Retail";
    String DEV_PAYTM_CALLBACK_URL   = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=__ORDERID__";

    // Production
    String PROD_PAYTM_MERCHANT_ID    = "Ovunqu36589353563413";
    String PROD_PAYTM_CHANNEL_ID     = "WAP";
    String PROD_PAYTM_WEBSITE_APP    = "APPPROD";
    String PROD_PAYTM_INDUSTRY_TYPE  = "Retail109";
    String PROD_PAYTM_CALLBACK_URL   = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=__ORDERID__";

    String PAYTM_REQUEST_ADD_MONEY = "ADD_MONEY";

    String PAYTM_WALLET_ADDED_SUCCESS = "paytm_wallet_added";
    String TEXT1 = "text1";
    String TEXT2 = "text2";
    String SETTING_USER_TYPE = "setting_user_type";

    public static final int HOME_ADDRESS = 1;
    public static final int OFFICE_ADDRESS = 0;
    public static final String SOURCE_HOME = "home";
    public static final String SOURCE_PROFILE = "profile";

    String UPDATE_SOCIETY_HELPER_LIST = "update_society_helper_list";
    int ALWAYS_AUTHORIZED = 0;
    int ONCE_AUTHORIZED = 1;

    // Type of installations
    int TYPE_MALL = 1;
    int TYPE_CORPORATE = 2;
    int TYPE_SOCIETY = 3;
    int TYPE_OPEN_SURFACE = 4;

    // Tag lock location object
    String TAG_LOCK_LOCATION = "tag_lock_location";

    // Refer and Earn
    String TYPE_FORUM = "form";
    String TYPE_IMAGE = "image";
}