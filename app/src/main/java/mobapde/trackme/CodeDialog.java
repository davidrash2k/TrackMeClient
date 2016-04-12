package mobapde.trackme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CodeDialog extends DialogFragment {


EditText et_code;
String requestingUser_id;
View v;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialog_code, null);
        et_code = (EditText) v.findViewById(R.id.code);


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v).setTitle("Enter Tracking Code").setMessage("Enter the tracking code of the user you would like to track")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                        requestingUser_id = sp.getString(User.SP_KEY_ID, null);
                        trackUser(requestingUser_id ,et_code.getText().toString());
                        CodeDialog.this.getDialog().cancel();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CodeDialog.this.getDialog().cancel();
            }
        });
        return builder.create();


    }

    private void trackUser(String id,String code){

        new UrlHelper().execute(id + " " + code);
    }


public class UrlHelper extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... param) {
        OkHttpClient client = new OkHttpClient();
        String url = MainActivity.URL_PART + "/TrackMeServerV2/ControllerServlet";
        String result = null;
        String input[] = new String[2];
        input = param[0].split(" ");
        String id = input[0];
        String code = input[1];
        RequestBody requestBody = new FormBody.Builder()
                .add("servlet", "trackUser")
                .add("trackerID", id)
                .add("code", code).build();


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

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(Integer.parseInt(s) == 1 ) {
            Toast.makeText(v.getContext(), "Track request has been sent to user.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(v.getContext(), "Incorrect email and/or password", Toast.LENGTH_SHORT).show();
        }

    }

}


}

