package com.ohgiraffers.MovieApp.Repository;

import com.ohgiraffers.MovieApp.aggregate.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private final List<Movie> movies = new ArrayList<>();
    private static final String FILE_PATH = "src/main/java/com/ohgiraffers/MovieApp/db/MovieDB.dat";

    public MovieRepository() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            ArrayList<Movie> defaultMovies = new ArrayList<>();
            defaultMovies.add(new Movie(1, "데드풀과 울버린", new String[]{"액션", "판타지"}, 132, 20000));
            defaultMovies.add(new Movie(2, "슈퍼배드 4", new String[]{"애니메이션"}, 120, 10000));
            defaultMovies.add(new Movie(3, "인사이드 아웃2", new String[]{"애니메이션"}, 120, 15000));
            saveMovies(file, defaultMovies);
        }
        loadMovies(file);
    }

    private int getNextId() {
        return movies.size() == 0 ? 1 : movies.get(movies.size() - 1).getMovieId() + 1;
    }

    public void addMovie(Movie movie) {
        movie.setMovieId(getNextId());
        movies.add(movie);
        saveMovies(new File(FILE_PATH), new ArrayList<>(movies));
    }

    public void updateMovie(Movie updatedMovie) {
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getMovieId() == updatedMovie.getMovieId()) {
                movies.set(i, updatedMovie);
                saveMovies(new File(FILE_PATH), new ArrayList<>(movies));
                break;
            }
        }
    }

    public void deleteMovie(Movie movie) {
        movies.removeIf(m -> m.getMovieId() == movie.getMovieId());
        saveMovies(new File(FILE_PATH), new ArrayList<>(movies));
    }


    public Movie getMovieById(int movieId) {
        return movies.stream().filter(m -> m.getMovieId() == movieId).findFirst().orElse(null);
    }

    public List<Movie> movieInfo() {
        return new ArrayList<>(movies);
    }

    private void loadMovies(File file) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)
                    )
            );
            while (true) {
                movies.add((Movie) ois.readObject());
            }
        } catch (EOFException e) {
            System.out.println("회원 정보 모두 로딩됨...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveMovies(File file, ArrayList<Movie> movies) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );
            for (Movie movie : movies) {
                oos.writeObject(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
