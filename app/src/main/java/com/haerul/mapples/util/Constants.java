package com.haerul.mapples.util;

import android.os.Environment;

public final class Constants {
    public static final String MASTER_DB = "mapples.db";
    public static final String LOG_DB = "log_mapples.db";
    public static final String MASTER_DB_ZIP = "mapples.zip";
    public static final String TEMP_DB = "mapples_temp.db";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APP_JSON = "application/json";
    public static final String MASTER_DB_PATH = String.format("//data//data//%s//databases//", "com.haerul.mapples");
    public static final String PATH = Environment.getExternalStorageDirectory() + "/MAPPLES/";
    public static final String PATH_DOWNLOAD = PATH + ".file-downloaded/";
    public static final String PATH_IMG = PATH + "img/";
    public static final String PATH_IMG_HIDDEN = PATH + ".temp_img/";
    public static final String PATH_EXPORT_DATA = PATH + ".exported/";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_ONLY_FORMAT = "yyyy-MM-dd";
    public static final String TIME_ONLY_FORMAT = "HH:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SECURITY_KEY = "Authorization";
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String TOKEN_EXPIRED = "Unauthorized Access!";
    public static final String DB_NAME = "db_name";
    public static final String DB_VERSION = "db_version";
    public static final String DB_PATH = "db_path";
    public static final String DB_URL = "db_url";
    public static final String DATA = "data";
    public static final String UID = "uid";
    public static final String IS_LOGIN = "is_login";
    public static final String USER_SID = "user_sid";
    public static final String USER_UID = "user_uid";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_UNIT = "user_unit";
    public static final String USER_LOGIN_NAME = "user_login_name";
    public static final String USER_PASSWORD = "user_password";
    public static final String IS_ACTIVE = "is_active";
    public static final String USER_ROLE_SID = "user_role_sid";
    public static final String DATE_CREATED = "date_created";
    public static final String DATE_MODIFIED = "date_modified";
    public static final String TOKEN_AUTH = "token_auth";
}
