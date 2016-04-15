package mobapde.trackme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jasonsioco on 3/16/2016.
 */
public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder>{


    private List<User> userList;
    private View v2;


    public RequestAdapter(List<User> userList) {
        this.userList = userList;
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        public View viewThis;
        protected TextView vName;
        protected Button accept;
        protected Button decline;
        public RequestViewHolder(View v) {
            super(v);
            viewThis = v;
            vName =  (TextView) v.findViewById(R.id.reqname);
            accept = (Button) v.findViewById(R.id.accept);
            decline = (Button) v.findViewById(R.id.decline);

        }
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestitem, parent, false);

        return new RequestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, final int position) {
        holder.vName.setText(userList.get(position).getName().toString());
        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("POSITION" + position);
                //delete


            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("POSITION" + position);
                //set status active


            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }








}
