package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by hoangcongtuan on 10/9/17.
 */

public class SampleData {

    public static List<JournalEntry> getSampleJournalEntries() {

        List<JournalEntry> journalEnrties = new ArrayList<>();
        //create the dummy journal
        JournalEntry journalEntry1 = new JournalEntry();
        journalEntry1.setTitle("DisneyLand Trip");
        journalEntry1.setContent
                ("We went to Disneyland today and the kids had lots of fun!");
        Calendar calendar1 = GregorianCalendar.getInstance();
        journalEntry1.setDateModified(calendar1.getTimeInMillis());
        journalEnrties.add(journalEntry1);

        JournalEntry journalEntry2 = new JournalEntry();
        journalEntry2.setTitle("Gym Work Out");
        journalEntry2.setContent
                ("We went to Disneyland today and the kids had lots of fun!");
        journalEntry2.setDateModified(calendar1.getTimeInMillis());
        journalEnrties.add(journalEntry2);

        JournalEntry journalEntry3 = new JournalEntry();
        journalEntry3.setTitle("Blog Post Idea");
        journalEntry3.setContent
                ("We went to Disneyland today and the kids had lots of fun!");
        journalEntry3.setDateModified(calendar1.getTimeInMillis());
        journalEnrties.add(journalEntry3);

        JournalEntry journalEntry4 = new JournalEntry();
        journalEntry4.setTitle("Cupcake Recipe");
        journalEntry4.setContent
                ("We went to Disneyland today and the kids had lots of fun!");
        journalEntry4.setDateModified(calendar1.getTimeInMillis());
        journalEnrties.add(journalEntry4);

        JournalEntry journalEntry5 = new JournalEntry();
        journalEntry5.setTitle("Notes From Networking Event");
        journalEntry5.setContent
                ("We went to Disneyland today and the kids had lots of fun!");
        journalEntry5.setDateModified(calendar1.getTimeInMillis());
        journalEnrties.add(journalEntry5);


        return journalEnrties;
    }

    public static List<String> getSampleTags() {

        List<String> tags = new ArrayList<>();
        //create the dummy journal
        tags.add("Tag1");
        tags.add("Tag2");
        tags.add("Tag3");
        tags.add("Tag4");
        tags.add("Tag5");
        tags.add("Tag6");
        tags.add("Tag7");
        tags.add("Tag8");

        return tags;
    }
}
