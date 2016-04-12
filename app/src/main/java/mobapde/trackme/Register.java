package mobapde.trackme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {
    Button cancel, submit;
    EditText etName, etMobileNumber, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cancel = (Button) findViewById(R.id.btncancel);
        submit = (Button) findViewById(R.id.btnsubmit);
        etName = (EditText) findViewById(R.id.et_regName);
        etMobileNumber = (EditText) findViewById(R.id.et_regMobileNumber);
        etEmail = (EditText) findViewById(R.id.et_regEmail);
        etPassword = (EditText) findViewById(R.id.et_regPassword);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                registerUser(etName.getText().toString(), etMobileNumber.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());


            }
        });
    }

    private void registerUser(String name, String mobileNumber, String email, String password){
        new UrlHelper().execute(name + " " + mobileNumber + " " + email + " " + password);
    }


    public class UrlHelper extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            String url = MainActivity.URL_PART + "/TrackMeServerV2/ControllerServlet";
            String userDetails[] = new String[4];
            userDetails = params[0].split(" ");


            //used for sending data to server
            RequestBody requestBody = new FormBody.Builder()
                    .add("servlet", "register")
                    .add("name", userDetails[0])
                    .add("mobileNumber", userDetails[1])
                    .add("email", userDetails[2])
                    .add("password", userDetails[3]).build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            String result = "-1";

            try {
                Response response;
                response = client.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //return true if registration is successful
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            finish();

            System.out.println("STRING S: "  +  s);

            Integer isRegistered = Integer.parseInt(s);
            if(isRegistered > -1) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "Registration successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_SHORT).show();
            }
        }

    }
}







