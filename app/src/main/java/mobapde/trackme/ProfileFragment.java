package mobapde.trackme;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


/**
 * Created by jasonsioco on 3/13/2016.
 */
public class ProfileFragment extends Fragment{

    private FloatingActionButton mode,destination,arrived;
    private FloatingActionMenu menu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.profile, container, false);
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
