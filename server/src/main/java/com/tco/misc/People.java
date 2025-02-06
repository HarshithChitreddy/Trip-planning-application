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
    final String name = "Christian Calderon";
    final String netid = "Chrisc23";
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
    final String name = "Dakota Weathers";
    final String netid = "dnweath";
    final String hometown = "Rapid City, South Dakota";
    final String bio = "My name is Dakota, and I am working towards a degree in Computer Science. I originally went to school at South Dakota State University for Microbiology, and worked in agricultural biotech startups for 6 years focusing on improving animal care and crop production while limiting negative environmental impacts. I hope to expand my skillset by obtaining this degree, and continue in a career to help improve the environment and positively impact the livelihood of others.";

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
