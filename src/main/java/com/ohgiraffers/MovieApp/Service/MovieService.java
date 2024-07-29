package com.ohgiraffers.MovieApp.Service;

import com.ohgiraffers.MovieApp.Repository.MovieRepository;
import com.ohgiraffers.MovieApp.aggregate.Movie;

public class MovieService {

    private final MovieRepository mr = new MovieRepository();

    // 관리자 아이디와 비밀번호를 상수로 정의
    private static final String adminId = "admin";
    private static final String adminPwd = "1234";
    private boolean loggedIn = false;

    public MovieService() {
    }

    public boolean logIn(String inputId, String inputPwd) {
        if (adminId.equals(inputId) && adminPwd.equals(inputPwd)) {
            System.out.println("로그인 성공");
            loggedIn = true;
            return true;
        } else {
            System.out.println("로그인 실패");
            return false;
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void movieEnrollment(Movie movie) {
        mr.addMovie(movie);
        System.out.println("영화가 등록되었습니다.");
    }

    public void updateMovie(int movieId, String movieTitle, String[] genre, int runningTime, int moviePrice) {
        Movie updatedMovie = new Movie(movieId, movieTitle, genre, runningTime, moviePrice);
        mr.updateMovie(updatedMovie);
        System.out.println("영화 정보가 수정되었습니다.");
    }


    public Movie getMovieById(int movieId) {
        return mr.getMovieById(movieId);
    }

    public void deleteMovie(int movieId) {
        Movie movie = mr.getMovieById(movieId);
        if (movie != null) {
            mr.deleteMovie(movie);
            System.out.println("영화가 삭제되었습니다.");
        } else {
            System.out.println("해당 ID를 가진 영화를 찾을 수 없습니다.");
        }
    }

    public void movieInfo() {
        for (Movie movie : mr.movieInfo()) {
            System.out.println(movie);
        }
    }
}
