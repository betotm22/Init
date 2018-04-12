package com.sye.base.util;

import com.sye.base.BuildConfig;

/**Settings Class and constants*/
public class Set {

    //region URLs

    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String AMAZON_URL = BuildConfig.AMAZON_URL;

    //endregion

    //region CONSTANTS

    public static final String POOL_ID = BuildConfig.POOL_ID;
    public static final String BUCKET = BuildConfig.BUCKET;

    /**
     * Pattern for a regular e-mail
     */
    public static final String RE_E_MAIL = "^[\\w-\\+]+(\\.[\\w-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * Regular expression fir a password that accepts capital letters, lower letters, numbers, and
     * special characters and a length from 8 to 15 characters.
     */
    public static final String RE_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&]*)[A-Za-z\\d$@$!%*?&]{8,15}";

    //endregion

    //region BUNDLE ARGUMENTS

    public static final String ARG_ARGUMENTS = "ARG_ARGUMENTS";

    //endregion

    //region INTENT EXTRAS

    public static final String  EXTRA_INTENT_EXTRA = "EXTRA_INTENT_EXTRA";

    //endregion

    //region PREFERENCES

    public static String PREF_USER_NAME = "PREF_USER_NAME";
    public static String PREF_USER_ID = "PREF_USER_ID";
    public static String PREF_AUTH = "PREF_AUTH";

    //endregion

    //region FONT NAMES

    public static final String FONT_NAME_LIGHT = "font/light.ttf";
    public static final String FONT_NAME_BOLD = "font/bold.ttf";
    public static final String FONT_NAME_ITALIC = "font/italic.ttf";
    public static final String FONT_NAME_REGULAR = "font/regular.ttf";
    public static final String FONT_NAME_DEMI = "font/semi.ttf";

    public static final String TAG_STYLE_BOLD = "bold";
    public static final String TAG_STYLE_ITALIC = "italic";
    public static final String TAG_STYLE_SEMI = "semi";

    //endregion

    //region STATUS CODES

    public static final int CODE_SUCCESS = 200;
    public static final int CODE_CREATED = 201;
    public static final int CODE_BAD_REQUEST = 400;
    public static final int CODE_NOT_FOUND = 404;
    public static final int CODE_SERVER_ERROR = 500;

    //endregion
}
