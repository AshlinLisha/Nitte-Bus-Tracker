package com.nitte.bustracker;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusService {
    private final List<Bus> buses = new ArrayList<>();

    public BusService() {
        // Initial data transcribed from the timetable PDF supplied with the project.
        // Add/edit rows here as the timetable is verified. Times are shown as printed in the source.
        add("8:00","Vishal","Express","Mangalore","");
        add("8:10","Vishal","Express","Mangalore","");
        add("8:45","Sangam","Local, UC","Padubidri","");
        add("8:46","Vishal","Express","Mangalore","");
        add("9:20","Mercy","Local","Mangalore","");
        add("9:20","Navadurga Prasad","Express","Mangalore","");
        add("9:30","Navadurga Prasad","Express","Mangalore","");
        add("9:45","Vishal","Express","Mangalore","");
        add("9:54","Christa Jyothi","Local","Udupi","");
        add("9:58","Vishal","Express","Mangalore","");
        add("9:59","Decent","Local","Udupi","");
        add("10:05","Laxmi Ganesh","Express","Mangalore","");
        add("10:11","Sangam","Local","Padubidri","");
        add("10:18","Pranam","Local","Kateel","");
        add("10:23","Christa Jyothi","Local","Udupi","");
        add("10:26","Reshma","Express","Mangalore","");
        add("10:36","Sangam","Fast Service - Local","Udupi","");
        add("10:50","Padmambikaa","Express","Mangalore","");
        add("11:08","Pranam","Local","Kateel","");
        add("11:10","Padmambikaa","Express","Mangalore","");
        add("11:20","Christa Kiran","Express","Mangalore","");
        add("11:26","Naveen","Local","Udupi","");
        add("11:28","Naveen","Local","Udupi","");
        add("11:29","Vishal","Express","Mangalore","");
        add("11:33","Naveen","Local","Udupi","");
        add("11:36","Jason","Local","Mulki","");
        add("11:40","Vishal","Express","Mangalore","");
        add("11:44","Rashmi Travels","Local","Udupi","via Kapu");
        add("11:51","Padmambikaa","Express","Mangalore","");
        add("12:00","Navadurga Prasad","Express","Mangalore","");
        add("12:09","Rajarajeshwari","Express","Mangalore","");
        add("12:09","Shree Annapoorneshwari","Express","Mangalore","");
        add("12:11","Srilaxmi","Local","Kateel","");
        add("12:18","Naveen","Local","Udupi","");
        add("12:25","Vishal","Express","Mangalore","");
        add("12:33","Navadurga Prasad","Express","Mangalore","");
        add("12:35","Vishal","Express","Mangalore","");
        add("12:38","Shree Padma","Fast Service - Local","Udupi","");
        add("12:45","Padmambikaa","Express","Mangalore","");
        add("12:52","Shahil","Local","Udupi","via Padubidri");
        add("12:56","Rajarajeshwari","Express","Mangalore","");
        add("1:07","Christa Jyothi","Local","Udupi","");
        add("1:10","Navadurga Prasad","Express","Mangalore","");
        add("1:21","Vishal","Express","Mangalore","");
        add("1:30","Kanthi","Local","Udupi","");
        add("1:32","Vishal","Express","Mangalore","");
        add("1:43","Kanthi","Local","Udupi","");
        add("1:50","Bharathi","Local","Udupi","");
        add("1:59","Laxmi Ganesh","Express","Mangalore","");
        add("2:00","Vishal","Express, UC","Mangalore","");
        add("2:07","Sangam","Local","Mulki","");
        add("2:10","Sangam","Local","Udupi","");
        add("2:15","Laxmi Ganesh","Express","Mangalore","");
        add("2:34","Pranam","Local","Kateel","");
        add("2:37","Padmambikaa","Express","Mangalore","");
        add("2:44","Navadurga Prasad","Express","Mangalore","");
        add("2:55","Pranam","Local","Kateel","");
        add("2:57","Padmambikaa","Express","Mangalore","");
        add("3:05","Reshma","Express","Mangalore","");
        add("3:12","Christa Jyothi","Local","Udupi","");
        add("3:16","Star","Local, UC","Udupi","");
        add("3:20","Vishal","Express","Mangalore","");
        add("3:31","Naveen","Local","Udupi","");
        add("3:33","Vishal","Express","Mangalore","");
        add("3:43","Sangam","Fast Service - Local","Padubidri","");
        add("3:45","Naveen","Local","Udupi","");
        add("3:48","Vishal","Express","Mangalore","");
        add("3:54","Naveen","Local","Udupi","");
        add("3:59","Padmambikaa","Express","Mangalore","");
        add("4:10","Ayra","Express","Mangalore","");
        add("4:19","Vishal","Express","Mangalore","");
        add("4:25","Naveen","Local","Udupi","");
        add("4:29","Vishal","Express","Mangalore","");
        add("4:30","Bharathi","Express","Padubidri","");
        add("4:35","Sai Ram","Fast Service - Local","Padubidri","");
        add("4:41","Vishal","Express","Mangalore","");
        add("4:49","Shree Padma","Local","Udupi","via Kapu");
        add("4:56","Vishal","Express","Mangalore","");
        add("5:02","Mercy","Local","Mangalore","");
        add("5:07","Navadurga Prasad","Express","Mangalore","");
        add("5:21","Shree Padma","Local","Udupi","");
        add("5:22","Navadurga Prasad","Express","Mangalore","");
    }

    private void add(String time, String name, String type, String destination, String route) {
        buses.add(new Bus(time, name, type, destination, route));
    }

    public List<Bus> search(String name, String destination, String type, String from, String to) {
        int fromMin = parseFilterTime(from, 0);
        int toMin = parseFilterTime(to, 23 * 60 + 59);

        final int start = fromMin;
        final int end = toMin;
        final boolean crossesMidnight = start > end;

        String n = safe(name).toLowerCase(Locale.ROOT);
        String d = safe(destination).toLowerCase(Locale.ROOT);
        String t = safe(type).toLowerCase(Locale.ROOT);

        return buses.stream()
            .filter(b -> crossesMidnight
                ? b.getMinutes() >= start || b.getMinutes() <= end
                : b.getMinutes() >= start && b.getMinutes() <= end)
            .filter(b -> b.getName().toLowerCase(Locale.ROOT).contains(n))
            .filter(b -> b.getDestination().toLowerCase(Locale.ROOT).contains(d))
            .filter(b -> b.getType().toLowerCase(Locale.ROOT).contains(t))
            .sorted(Comparator.comparingInt(Bus::getMinutes))
            .collect(Collectors.toList());
    }

    public Map<String, Long> countsByName() {
        return buses.stream().collect(Collectors.groupingBy(Bus::getName, TreeMap::new, Collectors.counting()));
    }

    public List<String> destinations() {
        return buses.stream().map(Bus::getDestination).distinct().sorted().collect(Collectors.toList());
    }

    public List<String> types() {
        return buses.stream().map(Bus::getType).distinct().sorted().collect(Collectors.toList());
    }

    public int totalCount() { return buses.size(); }

    private String safe(String s) { return s == null ? "" : s.trim(); }

    private int parseFilterTime(String value, int fallback) {
        if (value == null || value.isBlank()) return fallback;
        try {
            String[] parts = value.split(":");
            if (parts.length != 2) return fallback;
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            if (hour < 0 || hour > 23 || minute < 0 || minute > 59) return fallback;
            return hour * 60 + minute;
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }
}
