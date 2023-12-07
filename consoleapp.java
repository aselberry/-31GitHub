for (Movie movie : movieList) {
                System.out.println("Title: " + movie.title);
                System.out.println("Director: " + movie.director);
                System.out.println("Release Year: " + movie.releaseYear);
                System.out.println("Length: " + movie.length);
                System.out.println("Actors: " + String.join(", ", movie.actors));
                System.out.println(); // Add a newline for better readability
            }


class FileModifier {
    public void modifyFile(Path filePath){
        try {
            String fileContent = Files.readString(filePath);
            Files.writeString(filePath, fileContent);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
