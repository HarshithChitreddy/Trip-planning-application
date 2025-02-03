package com.tco.misc;

import java.util.ArrayList;

public class People extends ArrayList<Person> {

  public People() {

    add(person1());
    add(person2());
    add(person3());
    add(person4());
    add(person5());
  }

  Person person1() {
    final String name = "Team Member name";
    final String netid = "Team Member netid";
    final String hometown = "Team Member Hometown";
    final String bio = "Team Member bio";

    return new Person(name, netid, hometown, bio);
  }

  Person person2() {
    final String name = "Team Member name";
    final String netid = "Team Member netid";
    final String hometown = "Team Member Hometown";
    final String bio = "Team Member bio";

    return new Person(name, netid, hometown, bio);
  }

  Person person3() {
    final String name = "Team Member name";
    final String netid = "Team Member netid";
    final String hometown = "Team Member Hometown";
    final String bio = "Team Member bio";

    return new Person(name, netid, hometown, bio);
  }

  Person person4() {
    final String name = "Team Member name";
    final String netid = "Team Member netid";
    final String hometown = "Team Member Hometown";
    final String bio = "Team Member bio";

    return new Person(name, netid, hometown, bio);
  }

  Person person5() {
    final String name = "Team Member name";
    final String netid = "Team Member netid";
    final String hometown = "Team Member Hometown";
    final String bio = "Team Member bio";

    return new Person(name, netid, hometown, bio);
  }

}
