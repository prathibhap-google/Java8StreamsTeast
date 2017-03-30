package com.example.pprabhakaran.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    List persons = new ArrayList<>();
        persons.add(new Person("Albert", 80, Person.Gender.MALE));
        persons.add(new Person("Ben", 15, Person.Gender.MALE));
        persons.add(new Person("Amy", 20, Person.Gender.FEMALE));
        persons.add(new Person("Dean", 6, Person.Gender.MALE));
        persons.add(new Person("Arnold", 17, Person.Gender.FEMALE));

    List<Person> filtered = (List<Person>) persons
            .stream()
            .filter(p -> ((Person) p).getName().startsWith("A"))
            .collect(Collectors.toList());

        System.out.println(filtered);

    //Maps in streams

    Map<Integer, List<Person>> personsByAge = (Map<Integer, List<Person>>) persons
            .stream()
            .collect(Collectors.groupingBy(p -> ((Person) p).getAge()));

        personsByAge
                .forEach((age, p) -> Log.i("Streams: Mapping age", age + "," + p.toString()));

    //Collector and String Joiner

    Collector<Person, StringJoiner, String> personNameCollector =
            Collector.of(
                    () -> new StringJoiner(" | "),          // supplier
                    (j, p) -> j.add(p.getName().toUpperCase()),  // accumulator
                    (j1, j2) -> j1.merge(j2),               // combiner
                    StringJoiner::toString);                // finisher

    String names = (String) persons
            .stream()
            .collect(personNameCollector);

        System.out.println(names);


    //FlatMap
        Log.i("Flat Map:", testFlatMap());

    //Parallel Stream
    testparallelStreams();


}

    public static String testFlatMap(){
        List<Developer> team = new ArrayList<>();
        Developer polyglot = new Developer("esoteric");
        polyglot.add("clojure");
        polyglot.add("scala");
        polyglot.add("groovy");
        polyglot.add("go");

        Developer busy = new Developer("pragmatic");
        busy.add("java");
        busy.add("javascript");

        team.add(polyglot);
        team.add(busy);

        List<String> teamLanguages = team.stream().
                map(d -> d.getLanguages()).
                flatMap(l -> l.stream()).
                collect(Collectors.toList());

        String listString = teamLanguages.stream().map(Object::toString)
                .collect(Collectors.joining(", "));

        return listString;
    }


    public static void testparallelStreams(){
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    Log.i("Parallel Stream:filter:", s + Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    Log.i("Parallel Stream:map:", s + Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    Log.i("Parallel Stream:Sort:", s1 + s2 + Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    Log.i("filter: " , s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    Log.i("sort: " , s1 + "," + s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    Log.i("map: " , s);
                    return s.toUpperCase();
                })
                .forEach(s -> Log.i("forEach: " , s));
    }
}
