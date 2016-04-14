package mobapde.trackme;

/**
 * Created by Zehcnas on 3/16/2016.
 */
public class User {

    private int id;
    private String name;
    private String mobileNumber;
    private String email;
    private String password;
    private int code;
    private double latitude;
    private double longtitude;
    private String track_mode;
    private String track_interval;

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_LATITUDE = "latitute";
    private static final String COLUMN_LONGTITUDE = "longtitude";
    private static final String COLUMN_TRACK_MODE = "TRACK_mode";
    private static final String COLUMN_TRACK_INTERVAl = "TRACK_interval";

    public static final String SP_KEY_ID = "id";
    public static final String SP_KEY_NAME = "name";
    public static final String SP_KEY_EMAIL = "email";
    public static final String SP_KEY_PASSWORD = "password";
    public static final String SP_KEY_CODE = "code";
    public static final String SP_KEY_LATITUDE = "latitude";
    public static final String SP_KEY_LONGTITUDE = "longtitude";
    public static final String SP_KEY_TRACK_MODE = "track_mode";
    public static final String SP_KEY_TRACK_INTERVAL = "track_interval";


    public User() {

    }

    public User(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public User(String name, String mobileNumber, String email, String password) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String mobileNumber, String email, String password, int code, double latitude, double longtitude, String track_mode, String track_interval) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.code = code;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.track_mode = track_mode;
        this.track_interval = track_interval;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getTrack_mode() {
        return track_mode;
    }

    public void setTrack_mode(String track_mode) {
        this.track_mode = track_mode;
    }

    public String getTrack_interval() {
        return track_interval;
    }

    public void setTrack_interval(String track_interval) {
        this.track_interval = track_interval;
    }
}