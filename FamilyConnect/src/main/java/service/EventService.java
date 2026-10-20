
package service;

import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.EventRSVP;
import util.FileService;

public class EventService {

    private final FileService fileService;

    private final String EVENT_FILE = "data/events.txt";
    private final String RSVP_FILE = "data/event_rsvps.txt";

    public EventService() {
        fileService = new FileService();
    }

    // Get all events
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        List<String> lines = fileService.readFile(EVENT_FILE);

        for (String line : lines) {
            try {
                String[] parts = line.split("\\|", -1);

                if (parts.length != 5) {
                    continue;
                }

                long eventId = Long.parseLong(parts[0]);
                long creatorId = Long.parseLong(parts[1]);
                String title = parts[2];
                String description = parts[3];
                String dateTime = parts[4];

                Event event = new Event(
                        eventId,
                        creatorId,
                        title,
                        description,
                        dateTime
                );

                events.add(event);

            } catch (NumberFormatException e) {
                System.out.println("Invalid event data: " + line);
            }
        }

        return events;
    }

    // Find an event by ID
    public Event findEventById(long eventId) {
        for (Event event : getAllEvents()) {
            if (event.getEventId() == eventId) {
                return event;
            }
        }

        return null;
    }

    // Create a new event
    public boolean createEvent(Event event) {
        if (event == null) {
            return false;
        }

        if (event.getTitle() == null
                || event.getTitle().trim().isEmpty()) {
            System.out.println("Event title cannot be empty.");
            return false;
        }

        if (event.getDateTime() == null
                || event.getDateTime().trim().isEmpty()) {
            System.out.println("Event date and time cannot be empty.");
            return false;
        }

        long newId = 1;

        for (Event existingEvent : getAllEvents()) {
            if (existingEvent.getEventId() >= newId) {
                newId = existingEvent.getEventId() + 1;
            }
        }

        event.setEventId(newId);

        String line = event.getEventId() + "|"
                + event.getCreatorId() + "|"
                + safe(event.getTitle()) + "|"
                + safe(event.getDescription()) + "|"
                + safe(event.getDateTime());

        List<String> lines = fileService.readFile(EVENT_FILE);
        lines.add(line);

        return fileService.writeFile(EVENT_FILE, lines);
    }

    // Get all RSVPs
    public List<EventRSVP> getAllRSVPs() {
        List<EventRSVP> rsvps = new ArrayList<>();
        List<String> lines = fileService.readFile(RSVP_FILE);

        for (String line : lines) {
            try {
                String[] parts = line.split("\\|", -1);

                if (parts.length != 3) {
                    continue;
                }

                long eventId = Long.parseLong(parts[0]);
                long memberId = Long.parseLong(parts[1]);
                String status = parts[2];

                EventRSVP rsvp = new EventRSVP(
                        eventId,
                        memberId,
                        status
                );

                rsvps.add(rsvp);

            } catch (NumberFormatException e) {
                System.out.println("Invalid RSVP data: " + line);
            }
        }

        return rsvps;
    }

    // Register or update an RSVP
    public boolean respondToEvent(long eventId,
                                  long memberId,
                                  String status) {

        if (findEventById(eventId) == null) {
            System.out.println("Event does not exist.");
            return false;
        }

        if (!(status.equalsIgnoreCase("GOING")
                || status.equalsIgnoreCase("DECLINED")
                || status.equalsIgnoreCase("PENDING"))) {
            System.out.println(
                    "Status must be GOING, DECLINED, or PENDING."
            );
            return false;
        }

        List<EventRSVP> rsvps = getAllRSVPs();
        boolean found = false;

        for (EventRSVP rsvp : rsvps) {
            if (rsvp.getEventId() == eventId
                    && rsvp.getMemberId() == memberId) {

                rsvp.setStatus(status.toUpperCase());
                found = true;
                break;
            }
        }

        if (!found) {
            rsvps.add(new EventRSVP(
                    eventId,
                    memberId,
                    status.toUpperCase()
            ));
        }

        List<String> lines = new ArrayList<>();

        for (EventRSVP rsvp : rsvps) {
            String line = rsvp.getEventId() + "|"
                    + rsvp.getMemberId() + "|"
                    + rsvp.getStatus();

            lines.add(line);
        }

        return fileService.writeFile(RSVP_FILE, lines);
    }

    // Prevent "|" from breaking the file format
    private String safe(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("|", "/");
    }
}
