package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import models.JournalEntry;
import models.JournalViewHolder;

/**
 * Created by hoangcongtuan on 10/9/17.
 */

public class JournalFirebaseAdapter extends FirebaseRecyclerAdapter<JournalEntry, JournalViewHolder> {

    private ArrayList<JournalEntry> journalEntries;
    private RecyclerView recyclerView;
    private Context context;

    public JournalFirebaseAdapter(FirebaseRecyclerOptions<JournalEntry> options) {
        super(options);
    }

//    public JournalFirebaseAdapter(RecyclerView recyclerView, Context context) {
//        super();
//        this.recyclerView = recyclerView;
//        this.context = context;
//    }








    @Override
    protected void onBindViewHolder(JournalViewHolder holder, int position, JournalEntry model) {

    }

    @Override
    public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
