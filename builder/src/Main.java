//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.stream.Collectors;

import java.util.Comparator;


public class Main {

    public static void main(String[] args) {

        List<Event> EventList ;//=  new ArrayList<Event>();

        EventList = Arrays.asList(

                CreateEvent("Lollapalooza São Paulo","Brazil", "São Paulo"),
                CreateEvent("Lollapalooza Stockholm","Sweden", "Stockholm"),
                CreateEvent("Lollapalooza Paris","France", "Paris"),
                CreateEvent("Lollapalooza Chicago","United States", "Chicago"),
                CreateEvent("Lollapalooza Berlin","Germany", "Berlin"),
                CreateEvent("Lollapalooza Santiago","Chile", "Santiago"),
                CreateEvent("Lollapalooza Buenos Aires","Argentina", "Buenos Aires"),
                CreateEvent("Lollapalooza Colombia","Colombia"),
                CreateEvent("Lollapalooza Undefined")
        );






        System.out.println("\nTop 5 ordered by Id: ");
        EventList.stream()
                .sorted(Comparator.comparing(Event::getId))
                .limit(5)
                .forEach(e -> System.out.println(e));


        System.out.println("\nTop 5 ordered by Id: ");
        EventList.stream()
                .sorted(Comparator.comparing((Event e) -> e.getId()))
                .limit(5)
                .forEach(e -> System.out.println(e));



        System.out.println("\nTop 5 ordered by name: ");
        EventList.stream()
                .sorted(Comparator.comparing((Event e) -> e.getName()))
                .limit(5)
                .forEach(e -> System.out.println(e));


        System.out.println("\nAll reversed ordered by name: ");
        EventList.stream()
                .sorted(Comparator.comparing((Event e) -> e.getName()).reversed())
                .forEach(e -> System.out.println(e));


    }



    private static int EventId;
    private static int LocationId;
    private static int CityId;

    private static Event CreateEvent(String event, String location, String city)
    {
        return new Event.Builder().id(++EventId).name(event).location(
                new Location.Builder().id(++LocationId).name(location).city(
                        new City.Builder().id(++CityId).name(city).build()
                ).build()
        ).build();

    }

    private static Event CreateEvent(String event, String location)
    {
        return new Event.Builder().id(++EventId).name(event).location(
                new Location.Builder().id(++LocationId).name(location).build()
        ).build();
    }

    private static Event CreateEvent(String event)
    {
        return new Event.Builder().id(++EventId).name(event).build();
    }

}
