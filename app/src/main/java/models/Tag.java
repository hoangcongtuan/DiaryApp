package models;

/**
 * Created by hoangcongtuan on 10/8/17.
 */

public class Tag {
    private String tagId;
    private String tagName;
    private int journalCount;

    public Tag () {

    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getJournalCount() {
        return journalCount;
    }

    public void setJournalCount(int journalCount) {
        this.journalCount = journalCount;
    }
}
