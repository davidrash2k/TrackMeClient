package mobapde.trackme;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


/**
 * Created by jasonsioco on 3/13/2016.
 */
public class ProfileFragment extends Fragment{

    private FloatingActionButton mode,destination,arrived;
    private FloatingActionMenu menu;

    private TextView tv_name, tv_email, tv_trackMode, tv_code, tv_location, tv_status;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.profile, container, false);


       //tv_ = (TextView) v.findViewById(R.id.tv_);
        tv_name = (TextView) v.findViewById(R.id.tv_name);
        tv_email = (TextView) v.findViewById(R.id.tv_email);
        tv_trackMode = (TextView) v.findViewById(R.id.tv_trackMode);
        tv_code = (TextView) v.findViewById(R.id.tv_code);
        tv_location = (TextView) v.findViewById(R.id.tv_location);
        tv_status = (TextView) v.findViewById(R.id.tv_status);

        //DISPLAY USER DATA IN PROFILE
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());
        tv_name.setText(sp.getString(User.SP_KEY_NAME, null));
        tv_email.setText(sp.getString(User.SP_KEY_EMAIL, null));

        tv_trackMode.setText(  "Track Mode: " + sp.getString(User.SP_KEY_TRACK_MODE, null));
        tv_code.setText(sp.getString(User.SP_KEY_CODE, null));

        //process latitude and longtitude to get location name
        //tv_location.setText();

        tv_status.setText(sp.getString(User.SP_KEY_STATUS, null));


        mode = (FloatingActionButton) v.findViewById(R.id.fabmode);
        destination = (FloatingActionButton) v.findViewById(R.id.fabdestination);
        arrived = (FloatingActionButton) v.findViewById(R.id.fabarrived);
        menu = (FloatingActionMenu) v.findViewById(R.id.menu);
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SetDestinationActivity.class);
                startActivity(intent);
                menu.close(true);
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new ModeDialog();
                dialog.show(getFragmentManager(), "mode");
                menu.close(true);
            }
        });

        arrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.close(true);
            }
        });

        return v;
    }











}
