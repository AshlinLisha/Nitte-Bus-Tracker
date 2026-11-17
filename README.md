# Nitte Bus Tracker (Java Spring Boot)

## Live Website

[Click here to open Nitte Bus Tracker](https://YOUR-RENDER-LINK.onrender.com)

A starter website for searching bus timetable entries from Nitte by bus name, time range, service type and destination. It also counts departures by bus name.

## Requirements
- Java 17+
- Maven 3.8+

## Run it
1. Extract this ZIP.
2. Open a terminal in the `nitte-bus-tracker` folder.
3. Run: `mvn spring-boot:run`
4. Open `http://localhost:8080` in a browser.

## Included features
- Search by bus name (partial match)
- Filter by destination and service type
- Filter by start/end time
- Show repeated departures for each bus name
- Responsive mobile-friendly layout

## Important data note
The initial sample data is transcribed from the timetable PDF supplied for the project. It is not live tracking, and it may not be the latest schedule. The source includes many buses whose destination is Mangalore or Udupi; these are shown as the source labels state. Do not assume every service stops at Padubidri unless the route is explicitly confirmed. Verify times and route details with the bus operator or local timetable before relying on them.

## Update the timetable
Open `src/main/java/com/nitte/bustracker/BusService.java` and add or edit lines in the constructor using:
`add("8:45", "Sangam", "Local", "Padubidri", "");`

Time range filters use 24-hour input controls (for example, 1:00 PM is 13:00).
Source timetable times from 1:00 to 5:59 are interpreted as afternoon departures.
The timetable entries are sample transcriptions from the supplied project data and
should be verified locally; this application does not provide live bus tracking.
