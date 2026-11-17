package com.nitte.bustracker;

public class Bus {
    private final String time;
    private final int minutes;
    private final String name;
    private final String type;
    private final String destination;
    private final String route;

    public Bus(String time, String name, String type, String destination, String route) {
        this.time = time == null ? "" : time.trim();
        this.minutes = parseMinutes(this.time);
        this.name = name == null ? "" : name.trim();
        this.type = type == null ? "" : type.trim();
        this.destination = destination == null ? "" : destination.trim();
        this.route = route == null ? "" : route.trim();
    }

    private int parseMinutes(String value) {
        String[] parts = value.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid bus time: " + value);
        }

        int hour;
        int minute;
        try {
            hour = Integer.parseInt(parts[0]);
            minute = Integer.parseInt(parts[1]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid bus time: " + value, ex);
        }

        if (hour < 1 || hour > 12 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Bus time must be in 1-12 hour format: " + value);
        }

        // In the supplied timetable, 1:00-5:59 represent afternoon times.
        if (hour >= 1 && hour <= 5) {
            hour += 12;
        }
        return hour * 60 + minute;
    }

    public String getTime() { return time; }
    public int getMinutes() { return minutes; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getDestination() { return destination; }
    public String getRoute() { return route; }
}
