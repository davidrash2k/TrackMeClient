package mobapde.trackme;

import android.app.DialogFragment;
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

import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TrackingListFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView recList;
    UserAdapter adapter;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      v = inflater.inflate(R.layout.trackinglist, container, false);
        fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new CodeDialog();
                newFragment.show(getFragmentManager(), "add");
            }
        });
        recList = (RecyclerView) v.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);


        List<User> aa = new ArrayList();
        aa.add(new User("1337", "test", "test@gmail.com"));
        adapter = new UserAdapter(aa);
        getUserTrackee();

        recList.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        // Inflate the layout for this fragment
        return v;
    }


    public void getUserTrackee(){
        new UrlHelper().execute();
    }

    public class UrlHelper extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            String result = "";
            String url = MainActivity.URL_PART + "/TrackMeServerV2/ControllerServlet";
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());

            //used for sending data to server

            RequestBody requestBody = new FormBody.Builder()
                    .add("servlet", "getTrackeeList")
                    .add("id", sp.getString(User.SP_KEY_ID, null)).build();


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


            List trackeeList = new ArrayList();

            try {


                JSONArray jsonArray = new JSONArray(s);
                for(int i = 0; i < jsonArray.length(); i++) {
                    int id = jsonArray.getJSONObject(i).getInt(User.COLUMN_ID);
                    String name = jsonArray.getJSONObject(i).getString(User.COLUMN_NAME);
                    String email = jsonArray.getJSONObject(i).getString(User.COLUMN_EMAIL);
                    trackeeList.add(new User(id,name,email));
                    System.out.println();
                }

            }catch (JSONException e){
                e.printStackTrace();
            }

            adapter.setUserList(trackeeList);
            adapter.notifyDataSetChanged();
        }
    }


}