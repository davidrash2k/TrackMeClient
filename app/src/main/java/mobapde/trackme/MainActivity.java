package mobapde.trackme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView register;
    Button login;
    EditText email;
    EditText password;
    Boolean isAuthenticated;
    final static String URL_PART ="http://192.168.1.38:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isAuthenticated  = false;

        register = (TextView) findViewById(R.id.reg);
        login = (Button) findViewById(R.id.btnlogin);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        //for ui testing
        ImageView a = (ImageView) findViewById(R.id.img);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainMenu.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Register.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser(email.getText().toString(), password.getText().toString());
            }
        });

    }


    private void authenticateUser(String email, String password){
        //connect to server
        new UrlHelper().execute(email + " " + password);
    }

    private void getUserDetails(String email, String password){
        //connect to server
        new UrlHelper2().execute(email + " " + password);
    }


    //===============================URL HELPERS===============================

    public class UrlHelper extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            String url = URL_PART + "/TrackMeServerV2/ControllerServlet";
            String result = "None";
            String loginDetails[] = new String[2];
            loginDetails = params[0].split(" ");
            System.out.println("USER DETAILS: " + loginDetails.length);

            //used for sending data to server
            if(params[0] != null) {
                RequestBody requestBody = new FormBody.Builder()
                        .add("servlet", "login")
                        .add("email", loginDetails[0])
                        .add("password", loginDetails[1]).build();


                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();


                try {
                    Response response;
                    response = client.newCall(request).execute();
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String loginAndUserDetails[] = new String[9];
            loginAndUserDetails = s.split(" ");
            isAuthenticated = Boolean.parseBoolean(loginAndUserDetails[0]);
            if(isAuthenticated) {
                //save user details in local database
                Intent i = new Intent(getBaseContext(), MainMenu.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "Login successful", Toast.LENGTH_SHORT).show();

                //load user details in local database

                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString(User.SP_KEY_ID, loginAndUserDetails[1]);
                spEditor.putString(User.SP_KEY_NAME, loginAndUserDetails[2]);
                spEditor.putString(User.SP_KEY_EMAIL, loginAndUserDetails[3]);
                spEditor.putString(User.SP_KEY_PASSWORD, loginAndUserDetails[4]);
                spEditor.putString(User.SP_KEY_CODE, loginAndUserDetails[5]);
                spEditor.putString(User.SP_KEY_LATITUDE, loginAndUserDetails[6]);
                spEditor.putString(User.SP_KEY_LONGTITUDE, loginAndUserDetails[7]);
                spEditor.putString(User.SP_KEY_TRACK_MODE, loginAndUserDetails[8]);
                spEditor.putString(User.SP_KEY_TRACK_INTERVAL, loginAndUserDetails[9]);
                spEditor.clear(); //clear everything
                spEditor.commit();

            }else{
                Toast.makeText(getBaseContext(), "Incorrect email and/or password", Toast.LENGTH_SHORT).show();
            }
        }
    }





    public class UrlHelper2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            String url = URL_PART + "/TrackMeServerV2/ControllerServlet";
            String result = "None";
            String loginDetails[] = new String[2];
            loginDetails = params[0].split(" ");

            //used for sending data to server
            if(params[0] != null) {
                RequestBody requestBody = new FormBody.Builder()
                        .add("servlet", "getUserDetails")
                        .add("email", loginDetails[0])
                        .add("password", loginDetails[1]).build();


                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();


                try {
                    Response response;
                    response = client.newCall(request).execute();
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String loginAndUserDetails[] = new String[7];
            loginAndUserDetails = s.split(" ");

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor spEditor = sp.edit();
            spEditor.putString(User.SP_KEY_ID, loginAndUserDetails[1]);
            spEditor.putString(User.SP_KEY_NAME, loginAndUserDetails[2]);
            spEditor.putString(User.SP_KEY_EMAIL, loginAndUserDetails[3]);
            spEditor.putString(User.SP_KEY_PASSWORD, loginAndUserDetails[4]);
            spEditor.putString(User.SP_KEY_CODE, loginAndUserDetails[5]);
            spEditor.putString(User.SP_KEY_LATITUDE, loginAndUserDetails[6]);
            spEditor.putString(User.SP_KEY_LONGTITUDE, loginAndUserDetails[7]);
            spEditor.putString(User.SP_KEY_TRACK_MODE, loginAndUserDetails[8]);
            spEditor.putString(User.SP_KEY_TRACK_INTERVAL, loginAndUserDetails[9]);
            spEditor.clear(); //clear everything
            spEditor.commit();

        }
    }


}