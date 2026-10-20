
package model;

public class Event {

    private long eventId;
    private long creatorId;
    private String title;
    private String description;
    private String dateTime;

    public Event() {
    }

    public Event(long eventId, long creatorId,
                 String title, String description,
                 String dateTime) {
        this.eventId = eventId;
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Event{"
                + "eventId=" + eventId
                + ", creatorId=" + creatorId
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", dateTime='" + dateTime + '\''
                + '}';
    }
}
