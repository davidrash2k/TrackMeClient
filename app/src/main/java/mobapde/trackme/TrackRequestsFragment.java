package mobapde.trackme;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class TrackRequestsFragment extends Fragment {
    RecyclerView recycler;
    RequestAdapter requestadapter;

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
        View v = inflater.inflate(R.layout.trackrequests, container, false);
        recycler = (RecyclerView) v.findViewById(R.id.requests);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        recycler.setLayoutManager(llm);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setAdapter(requestadapter);
        // Inflate the layout for this fragment
        return v;
    }
}