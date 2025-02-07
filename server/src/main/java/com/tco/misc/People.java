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
    final String netid = "chrisc23";
    final String hometown = "Denver, Colorado";
    final String bio = "My name is Christian Calderon I am currently a student at CSU majoring in Computer Science. My goal is to one day be able to use my degree to make the world a better place. I also hope to one day provide for my family and obtain financial freedom. I have had a few impressive/proud achievements: I like going to the gym and I finally was able to bench 2 plates which I am proud of, another achievement is I made a working honey pot on my Raspberry Pi, and the last achievement is building my own website for my dad’s company. A quirky or maybe just ADHD fact about me is that I always have a pen on me to fidget with when I need to focus.";


    return new Person(name, netid, hometown, bio);
  }

  Person person2() {
    final String name = "Kjell Falk";
    final String netid = "kjell";
    final String hometown = "Fort Collins, Colorado";
    final String bio = "My name is Kjell, and I am studying for a degree in Computer Science after an emergent medical condition disabled me out of my ten year-long career in semiconductor manufacturing. I am a husband, and father of two daughters, and a veteran of Operation Iraqi Freedom, having served as a tank crewman (19K) in the US Army. I enjoy spending time with my daughters, and find the process of teaching them things very rewarding and fulfilling. My name is Swedish, and sounds like \"shell\" or \"chell\", but \"kell\" can be easier for some people to pronounce so I can go by that, too.";


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
    final String name = "Lennox Nutall";
    final String netid = "Team Member netid";
    final String hometown = "Brownsburg, Indiana";
    final String bio = "Hello my name is Lennox. I am all about people and finding real, authentic connections. Everybody has something that they're passionate about and I absolutely love allowing them to express that through conversation. The ability to transform your feelings into words and then into tangible ideas through individuality and a creative mind is invaluable. I truly believe that every human possesses this attribute. Currently, I am a Computer Science/Spanish Language Student at Colorado State University and one of my life's passions is videogames. All of my life I have been on the consumer side of this field where I am enjoying the finished product that someone else has built for me. Now that I'm older, I feel driven to develop my own video game. Not only for myself to appreciate, but for others to as well. It would grant me an abundance of satisfaction to see myself be able to create my ideas and share them with others.";

    return new Person(name, netid, hometown, bio);
  }

  Person person5() {
    final String name = "Harshith Reddy Chitreddy";
    final String netid = "reddy17";
    final String hometown = "Team Member Hometown";
    final String bio = "I am an international student from India, studying Computer Science at CSU. I have experience in cybersecurity and full-stack development, working with the MERN stack to build responsive and user-friendly applications. I’m passionate about using my skills to solve real-world problems and contribute to meaningful projects. I aspire to be a Software Engineer and constantly work on improving my skills. In my free time, I watch YouTube and also practice LeetCode to get better at problem-solving.";

    return new Person(name, netid, hometown, bio);
  }

}
