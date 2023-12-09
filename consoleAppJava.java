import java.util.*;
import java.util.regex.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Handler {
  Handler() {

  }

  //TODO: check code logic and do the necessary printing for user
  //TODO: params as param
  public void listMoviesBasedOnFlags(String[] tokens, List<String> flags, Map<String, Movie> movies) {
    boolean verbose = flags.contains("-v");
    boolean titleFilter = flags.contains("-t");
    boolean directorFilter = flags.contains("-d");
    boolean actorFilter = flags.contains("-a");
    boolean lengthAscending = flags.contains("-la");
    boolean lengthDescending = flags.contains("-ld");

    // TODO: set and index for iterating over params param[ind], ind++;
    // TODO: data consistency when applying multiple filters
    if (titleFilter) {
        String titleRegex = tokens[Arrays.asList(tokens).indexOf("-t") + 1].replace("\"", "");
    
        Iterator<Map.Entry<String, Movie>> iterator = movies.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Movie> entry = iterator.next();
            Movie movie = entry.getValue();
            if (!Pattern.matches(titleRegex, movie.title)) {
                iterator.remove();
            }
        }
    }

    if (directorFilter) {
        String directorRegex = tokens[Arrays.asList(tokens).indexOf("-d") + 1].replace("\"", "");
    
        Iterator<Map.Entry<String, Movie>> iterator = movies.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Movie> entry = iterator.next();
            Movie movie = entry.getValue();
            if (!Pattern.matches(directorRegex, movie.director.name)) {
                iterator.remove();
            }
        }
    }

    if (actorFilter) {
      String actorRegex = tokens[Arrays.asList(tokens).indexOf("-a") + 1].replace("\"", "");

      Iterator<Map.Entry<String, Movie>> iterator = movies.entrySet().iterator();
      while (iterator.hasNext()) {
          Map.Entry<String, Movie> entry = iterator.next();
          Movie movie = entry.getValue();
          if (movie.actors.stream().anyMatch(actor -> Pattern.matches(actorRegex, actor.name))) {
              // Keep the entry in the map
          } else {
              // Remove the entry from the map
              iterator.remove();
          }
      }
    }

    List<Map.Entry<String, Movie>> movieList = new ArrayList<>(movies.entrySet());
    if (lengthAscending || lengthDescending) {
        
        if (lengthDescending) {
            movieList.sort(Comparator.comparingInt(entry -> entry.getValue().getLengthInMinutes()).reversed());
        } else {
            movieList.sort(Comparator.comparingInt(entry -> entry.getValue().getLengthInMinutes()));
        }
      }


    

    for (Movie movie : movieList) {
      System.out.println(verbose ? movie.detailedString() : movie.toString());
    }
}

  // TODO: add code logic and do the necessary printing for user
  // TODO: add params list as param
  public void addEntry(String[] tokens, List<String> flags, Map<String, Movie> movies, Map<String, Movie> people) {

    boolean isAddPerson = flags.contains("-m");
    boolean isAddMovie = flags.contains("-p");

    if (isAddPerson) {

    }

    if (isAddMovie) {

    }
  }

  // TODO: check code logic and do the necessary printing for user
  private static void deletePerson(String[] token, Map<String, Movie> movies, Map<String, Person> people) {
    String personName = token[2];

    if (!people.containsKey(personName)) {
      System.out.println("Person not found in the database.");
      return;
    }

    Person personToDelete = people.get(personName);

    for (Movie movie : movies.values()) {
      if (movie.director.equals(personToDelete)) {
        System.out.println("Cannot delete a person who is a director of a movie.");
        return;
      }
      movie.actors.removeIf(actor -> actor.equals(personToDelete));
    }

    people.remove(personName);
    System.out.println("Person deleted successfully.");
  
  }
}



import java.util.*;
import java.util.regex.*;
import java.util.Map;
import java.util.ArrayList;

// TODO: add/modify class details
public class Person {

  // TODO: make them private and add getters and setters
  String name;
  int birthYear;

  Person(String name, int birthYear) {
      this.name = name;
      this.birthYear = birthYear;
  }
  
  @Override
  public String toString() {
    return name + ", " + birthYear;
  }
}

import java.util.*;
import java.util.regex.*;
import java.util.Map;
import java.util.ArrayList;

// TODO: add class details
public class Movie {

  // TODO: make them private and add getters and setters
  String title;
  Person director;
  int releaseYear;
  List<Person> actors = new ArrayList<>();
  // TODO: change type of length (Time class or String)
  int lengthInMinutes;

  Movie(String title, Person director, int releaseYear, int lengthInMinutes) {
    this.title = title;
    this.director = director;
    this.releaseYear = releaseYear;
    this.lengthInMinutes = lengthInMinutes;
  }

  @Override
  public String toString() {
    return title + " by " + director.name + " in " + releaseYear + ", " + lengthInMinutes + " minutes";
  }

  // TODO: change string output if necessary
  public String detailedString() {
    StringBuilder result = new StringBuilder(toString());
    result.append("\nStarring:");
    for (Person actor : actors) {
      result.append("\n\t- ").append(actor.name).append(" at age ").append(releaseYear - actor.birthYear);
    }
    return result.toString();
  }
}

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;
import java.util.*;

class Main {

  public static void main(String[] args) {

    if (args.length == 0) {
      System.out.println("Please provide command-line arguments.");
      System.exit(1);
    }

    Map<String, Person> people = new HashMap<>();
    Map<String, Movie> movies = new HashMap<>();
    Handler handler = new Handler();
    String[] input = args.clone();
    // TODO: validate arguments
    processInput(input, handler, people, movies);

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Enter a new command (or type 'exit' to quit): ");
      String userInput = scanner.nextLine();

      if ("exit".equalsIgnoreCase(userInput)) {
        System.out.println("Exiting the program.");
        break;
      }

      // Split the user input into command-line arguments and process them
      String[] newArgs = userInput.split("\\s+");
      processInput(newArgs, handler, people, movies);
    }
    scanner.close();
  }

  private static void processInput(String[] input, Handler handler, Map<String, Person> people,
      Map<String, Movie> movies) {
    String[] tokens = input.clone();

    if (tokens.length == 0) {
      System.out.println("Invalid command");
      return;
    }

    ArrayList<String> flags = new ArrayList<>();
    String command = tokens[0];
    ArrayList<String> parameters = new ArrayList<>();

    // Check for flags
    // Note: we assume that the user will give one command at a time,
    // e.g. "l -v -t -d" or "a -a" and not "l -t a -d"
    // TODO: do the user input validation in main while reading or here
    // do format check for flags and display error message, i.e. they can only
    // be
    // [v, t, d] if command is l
    // [a, d] if command is a
    // [p] if command is d
    // TODO: also check for parametrized commands and display error message if user
    // input is invalid
    for (int i = 1; i < tokens.length; i++) {
      if (tokens[i].startsWith("-")) {
        flags.add(tokens[i]);
      } else {
        parameters.add(tokens[i]);
        /*
         * TODO: we need to handle parametrized commands I think by storing them in a
         * separate list similar to flags in listMovies handler method, we will check if
         * the flag needs to have parameter if yes, we take the value from params list
         * and increment the index so that the next flag can use the next parameter
         */

        break; // Stop parsing flags if a non-flag token is encountered
      }
    }

    // Process command and flags
    switch (command) {
      case "l":
        handler.listMoviesBasedOnFlags(Arrays.copyOfRange(tokens, flags.size() + 1, tokens.length), flags, movies);
        break;
      case "a":
        handler.addEntry(Arrays.copyOfRange(tokens, flags.size() + 1, tokens.length), flags, movies, people);
        break;
      case "d":
        handler.deletePerson(tokens, movies, people);
        break;
      default:
        System.out.println("Invalid command");
    }
  }

  // Placeholder for the Handler class
  static class Handler {
    // Assuming Handler class is defined with necessary methods
    void listMoviesBasedOnFlags(String[] tokens, ArrayList<String> flags, Map<String, Movie> movies) {
      // Implementation for listing movies based on flags
    }

    void addEntry(String[] tokens, ArrayList<String> flags, Map<String, Movie> movies, Map<String, Person> people) {
      // Implementation for adding an entry
    }

    void deletePerson(String[] tokens, Map<String, Movie> movies, Map<String, Person> people) {
      // Implementation for deleting a person
    }
  }

  // Placeholder for the Person and Movie classes
  static class Person {
    // Assuming Person class is defined with necessary attributes and methods
  }

  static class Movie {
    // Assuming Movie class is defined with necessary attributes and methods
  }
}

