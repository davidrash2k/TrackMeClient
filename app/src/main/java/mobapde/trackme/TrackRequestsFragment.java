package mobapde.trackme;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TrackRequestsFragment extends Fragment {
    RecyclerView recycler;
    RequestAdapter requestadapter;
    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List aa = new ArrayList();
        aa.add(new User("Johnny", "johnny@gmail.com"));
        aa.add(new User("Alan", "alan@gmail.com"));
        aa.add(new User("Janey", "janeyjane@gmail.com"));
        aa.add(new User("Manny", "manny2354@gmail.com"));
        aa.add(new User("Allie", "allie134134@gmail.com"));
        requestadapter = new RequestAdapter(aa);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.trackrequests, container, false);
        recycler = (RecyclerView) v.findViewById(R.id.requests);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        recycler.setLayoutManager(llm);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setAdapter(requestadapter);
        // Inflate the layout for this fragment
        return v;
    }


    private void getUserTrackRequests(){
        new UrlHelper().execute();
    }


    public class UrlHelper extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            String url = MainActivity.URL_PART + "/TrackMeServerV2/ControllerServlet";
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());

            //used for sending data to server
            if(params[0] != null) {
                RequestBody requestBody = new FormBody.Builder()
                        .add("servlet", "getUserTrackRequests")
                        .add("id", sp.getString(User.SP_KEY_ID, null)).build();


                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();


                try {
                    Response response;
                    response = client.newCall(request).execute();
                   // result = response.body().string();
                   // System.out.println("result:" + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            //return result;

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }


}