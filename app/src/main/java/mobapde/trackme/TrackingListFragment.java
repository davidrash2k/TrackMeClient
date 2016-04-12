package mobapde.trackme;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TrackingListFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView recList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trackinglist, container, false);
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
        List aa = new ArrayList();
        aa.add(new User("Janey", "janeyjane@gmail.com"));
        aa.add(new User("Johnny", "johnny@gmail.com"));
        aa.add(new User("Miguel", "miguelito@gmail.com"));
        aa.add(new User("Jacob", "jacob@gmail.com"));
        aa.add(new User("Ashley", "ash@gmail.com"));
        aa.add(new User("Alan", "alan@gmail.com"));
        aa.add(new User("Caitlin", "catie@gmail.com"));
        aa.add(new User("Jenna", "jenna@gmail.com"));
        aa.add(new User("Lena", "lena324@gmail.com"));
        UserAdapter adapter = new UserAdapter(aa);
        recList.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        // Inflate the layout for this fragment
        return v;
    }

}