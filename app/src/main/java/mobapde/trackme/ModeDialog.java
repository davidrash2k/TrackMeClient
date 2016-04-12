package mobapde.trackme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

/**
 * Created by jasonsioco on 3/16/2016.
 */
public class ModeDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setTitle("Select Mode").setMessage("Select the transmission mode of your location.")
                .setNegativeButton("Pulse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ModeDialog.this.getDialog().cancel();
                    }
                }).setPositiveButton("Stream", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ModeDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

}
