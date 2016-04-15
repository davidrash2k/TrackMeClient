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
import android.widget.TextView;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jasonsioco on 3/16/2016.
 */
public class ModeDialog extends DialogFragment{
    View v;
    private TextView tv_trackMode;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.activity_main, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setTitle("Select Mode").setMessage("Select the transmission mode of your location.")
                .setNegativeButton("Pulse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        updateTrackingMode("Pulse");
                        ModeDialog.this.getDialog().cancel();
                    }
                }).setPositiveButton("Stream", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                        updateTrackingMode("Stream");
                        ModeDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    private void updateTrackingMode(String sTrackMode){
        new UrlHelper().execute(sTrackMode);
    }


    public class UrlHelper extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            String url = MainActivity.URL_PART + "/TrackMeServerV2/ControllerServlet";
            String result = "None";
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());

            //used for sending data to server
            if(params[0] != null) {
                RequestBody requestBody = new FormBody.Builder()
                        .add("servlet", "updateTrackMode")
                        .add("id", sp.getString(User.SP_KEY_ID,null))
                        .add("track_mode", params[0]).build();


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
            /*
            System.out.println("Server Response: " + s);
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());
            SharedPreferences.Editor spEditor = sp.edit();
            spEditor.putString(User.SP_KEY_TRACK_MODE, s);
            tv_trackMode = (TextView) v.findViewById(R.id.tv_trackMode);
            tv_trackMode.setText(  "Track Mode: " + sp.getString(User.SP_KEY_TRACK_MODE, null));
            */
        }
    }





}
