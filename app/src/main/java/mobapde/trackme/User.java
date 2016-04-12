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
    private String code;
    private String location;

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_LOCATION = "location";

    public static final String SP_KEY_ID = "id";
    public static final String SP_KEY_NAME = "name";
    public static final String SP_KEY_EMAIL = "email";
    public static final String SP_KEY_PASSWORD = "password";
    public static final String SP_KEY_CODE = "code";
    public static final String SP_KEY_LOCATION = "location";






    public User(){

    }

    public User(String name, String email){
        this.email = email;
        this.name = name;
    }

    public User(String name, String mobileNumber, String email, String password) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String mobileNumber, String email, String password, String code, String location) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.code = code;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

