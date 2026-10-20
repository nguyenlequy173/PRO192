
package model;

public class EventRSVP {

    private long eventId;
    private long memberId;
    private String status;

    public EventRSVP() {
    }

    public EventRSVP(long eventId, long memberId, String status) {
        this.eventId = eventId;
        this.memberId = memberId;
        this.status = status;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventRSVP{"
                + "eventId=" + eventId
                + ", memberId=" + memberId
                + ", status='" + status + '\''
                + '}';
    }
}
