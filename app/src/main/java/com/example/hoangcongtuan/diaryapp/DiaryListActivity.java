package com.example.hoangcongtuan.diaryapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.JournalEntry;
import models.JournalViewHolder;
import models.SampleData;
import models.Tag;

import static models.SampleData.getSampleJournalEntries;

public class DiaryListActivity extends AppCompatActivity {

    private final static String TAG = DiaryListActivity.class.getName();

    public final static String FIRST_RUN = "FIRST_RUN";

    private DatabaseReference mDatabase;
    private DatabaseReference journalCloudEndPoint;
    private DatabaseReference tagCloudEndPoint;

    private RecyclerView recyclerView;

    private TextView tvEmpty;

    FirebaseRecyclerAdapter<JournalEntry, JournalViewHolder> mJournalistFireBaseAdapter;

    private FirebaseRecyclerAdapter adapter;

    ArrayList<JournalEntry> journalEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        journalCloudEndPoint = mDatabase.child("journalentris");
        tagCloudEndPoint = mDatabase.child("tags");

        tvEmpty = (TextView)findViewById(R.id.tvEmpty);
        tvEmpty.setVisibility(View.INVISIBLE);
        tvEmpty.setEnabled(false);

        //getDataFromFireBase();
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        journalEntries = new ArrayList<>();
        //create query for adapter
        Query query = journalCloudEndPoint;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                journalEntries.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    JournalEntry note = snapshot.getValue(JournalEntry.class);
                    journalEntries.add(note);
                    Log.d(TAG, "onDataChange: " + note.getTitle());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: " + databaseError.getMessage());
            }
        });


        FirebaseRecyclerOptions<JournalEntry> options
                = new FirebaseRecyclerOptions.Builder<JournalEntry>()
                .setQuery(query, JournalEntry.class)
                .build();
        mJournalistFireBaseAdapter = new FirebaseRecyclerAdapter<JournalEntry, JournalViewHolder>(options) {
            @Override
            protected void onBindViewHolder(JournalViewHolder holder, int position, final JournalEntry model) {
                //model = journalEntries.get(position);
                holder.tvTitle.setText(model.getTitle());
                SimpleDateFormat sf = new SimpleDateFormat("dd:MM:yyyy");
                Date date = new Date(model.getDateModified());
                holder.tvDate.setText(sf.format(date));


                //tao icon
                String firstLetter = model.getTitle().substring(0, 1);
                ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
                int color = colorGenerator.getRandomColor();
                TextDrawable drawable = TextDrawable.builder()
                        .buildRound(firstLetter, color);
                holder.journalIcon.setImageDrawable(drawable);

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            journalCloudEndPoint.child(model.getJournalId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    if(mJournalistFireBaseAdapter.getItemCount() < 1) {
                                        tvEmpty.setVisibility(View.VISIBLE);

                                    }
                                }
                            });
                        }
                });
                Log.d(TAG, "onBindViewHolder: " + position);

            }

            @Override
            public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_custom_row, parent, false);
                return new JournalViewHolder(view);
            }

            @Override
            public int getItemCount() {
                return journalEntries.size();
            }

        };

        mJournalistFireBaseAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(mJournalistFireBaseAdapter);

        mJournalistFireBaseAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mJournalistFireBaseAdapter.startListening();

        //neu nhu chay lan dau, tao du lieu tren firebase
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        if (preferences.getBoolean(FIRST_RUN, true)) {
            addInitialDataToFirebase();
            editor.putBoolean(FIRST_RUN, false);
            editor.commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mJournalistFireBaseAdapter.stopListening();
    }

    private void addInitialDataToFirebase() {

        List<JournalEntry> sampleJournalEntries = getSampleJournalEntries();
        for (JournalEntry journalEntry: sampleJournalEntries){
            String key = journalCloudEndPoint.push().getKey();
            journalEntry.setJournalId(key);
            journalCloudEndPoint.child(key).setValue(journalEntry);
        }

        List<String> tagNames = SampleData.getSampleTags();
        for (String name: tagNames){
            String tagKey = tagCloudEndPoint.push().getKey();
            Tag tag = new Tag();
            tag.setTagName(name);
            tag.setTagId(tagKey);
            tagCloudEndPoint.child(tag.getTagId()).setValue(tag);
        }

    }

//    private void getDataFromFireBase() {
//        journalEntries = new ArrayList<>();
//        journalCloudEndPoint.addValueEventListener(
//
//    }
}
