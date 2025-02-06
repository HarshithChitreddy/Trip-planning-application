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
    final String netid = "crisc23";
    final String hometown = "Team Member Hometown";
    final String bio = "My name is Christian Calderon I am currently a student at CSU majoring in Computer Science. My goal is to one day be able to use my degree to make the world a better place. I also hope to one day provide for my family and obtain financial freedom. I have had a few impressive/proud achievements: I like going to the gym and I finally was able to bench 2 plates which I am proud of, another achievement is I made a working honey pot on my Raspberry Pi, and the last achievement is building my own website for my dad’s company. A quirky or maybe just ADHD fact about me is that I always have a pen on me to fidget with when I need to focus.";

    return new Person(name, netid, hometown, bio);
  }

  Person person2() {
    final String name = "Kjell Falk";
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
