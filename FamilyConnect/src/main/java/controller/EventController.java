
package controller;

import java.util.List;
import model.Event;
import model.EventRSVP;
import service.EventService;

public class EventController {

    private final EventService eventService;

    public EventController() {
        eventService = new EventService();
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Find an event by ID
    public Event findEventById(long eventId) {
        return eventService.findEventById(eventId);
    }

    // Create a new event
    public boolean createEvent(Event event) {
        return eventService.createEvent(event);
    }

    // Get all event responses
    public List<EventRSVP> getAllRSVPs() {
        return eventService.getAllRSVPs();
    }

    // Respond to an event
    public boolean respondToEvent(
            long eventId, long memberId, String status) {

        return eventService.respondToEvent(
                eventId, memberId, status
        );
    }
}
