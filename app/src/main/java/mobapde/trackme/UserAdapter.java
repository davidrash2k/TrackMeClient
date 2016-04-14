package mobapde.trackme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasonsioco on 3/16/2016.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vEmail;
        private final Context context;

        public UserViewHolder(View v) {
            super(v);
            context = v.getContext();
            vName =  (TextView) v.findViewById(R.id.name);
            vEmail = (TextView)  v.findViewById(R.id.email);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("POSITION"+getAdapterPosition());
                    //getadapterposition and display proper profile
                    Intent i = new Intent(v.getContext(),TrackeeDetails.class);
                    context.startActivity(i);
                }
            });

        }

    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater. from(parent.getContext()).inflate(R.layout.useritem, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.vName.setText(userList.get(position).getName().toString());
        holder.vEmail.setText(userList.get(position).getEmail().toString());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
