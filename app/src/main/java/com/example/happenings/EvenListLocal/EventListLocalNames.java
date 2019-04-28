package com.example.happenings.EvenListLocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class EventListLocalNames
{

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Event> EVENTS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Event> EVENT_MAP = new HashMap<String, Event>();

    private static final int COUNT = 4;

    static
    {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++)
        {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Event item)
    {
        EVENTS.add(item);
        EVENT_MAP.put(item.id, item);
    }

    private static Event createDummyItem(int position)
    {
        String apple=null;
        switch(position)
    {
        case 1:apple="IEEE";
        break;
        case 2:apple="ISTE";
        break;
        case 3:apple="IET";
        break;
        case 4:apple="ROTARACT";
        break;
    }
        return new Event(String.valueOf(position), apple , makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Club: ");
            builder.append("\nMore details information here.");

        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Event {
        public final String id;
        public final String content;
        public final String details;

        public Event(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
