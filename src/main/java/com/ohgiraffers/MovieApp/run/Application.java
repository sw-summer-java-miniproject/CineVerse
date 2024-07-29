package com.ohgiraffers.MovieApp.run;

import com.ohgiraffers.MovieApp.Service.DiscountService;
import com.ohgiraffers.MovieApp.Service.MemberService;
import com.ohgiraffers.MovieApp.Service.MovieService;
import com.ohgiraffers.MovieApp.aggregate.Movie;

import java.util.Scanner;

public class Application {
    private static final MovieService ms = new MovieService();
    private static final MemberService mms = new MemberService();
    private static final DiscountService ds = new DiscountService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean isLoggedIn = false;

        while (true) {
            if (!isLoggedIn) {
                System.out.println("======= 관리자님 안녕하세요. 로그인 하시겠어요? ======");
                System.out.println("======= 1. 로그인 ======");
                System.out.println("======= 11. 로그아웃 ======");
                System.out.print("메뉴를 선택해 주세요: ");
                int input = sc.nextInt();
                sc.nextLine(); // consume newline

                if (input == 1) {
                    System.out.print("아이디를 입력하세요: ");
                    String id = sc.nextLine();
                    System.out.print("비밀번호를 입력하세요: ");
                    String pwd = sc.nextLine();
                    if (ms.logIn(id, pwd)) {
                        System.out.println("관리자님 안녕하세요. 무엇을 도와드릴까요?");
                        isLoggedIn = true;
                    } else {
                        System.out.println("로그인 실패. 다시 시도해 주세요.");
                    }
                } else if (input == 11) {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                } else {
                    System.out.println("번호를 잘못 입력하셨습니다.");
                }
            } else {
                System.out.println("======= 메뉴 =======");
                System.out.println("2. 영화 등록");
                System.out.println("3. 영화 정보 수정");
                System.out.println("4. 영화 정보 삭제");
                System.out.println("5. 영화 정보 보기");
                System.out.println("6. 회원 정보 확인");
                System.out.println("7. 할인 정보 등록");
                System.out.println("8. 할인 정보 수정");
                System.out.println("9. 할인 정보 삭제");
                System.out.println("10. 할인 정보 보기");
                System.out.println("11. 로그아웃");
                System.out.print("메뉴를 선택해 주세요: ");
                int input = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (input) {
                    case 2:
                        if (ms.isLoggedIn()) {
                            Movie movie = createMovie();
                            ms.movieEnrollment(movie);
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 3:
                        if (ms.isLoggedIn()) {
                            updateMovie();
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 4:
                        if (ms.isLoggedIn()) {
                            System.out.print("삭제할 영화의 ID를 입력하세요: ");
                            int movieId = sc.nextInt();
                            sc.nextLine(); // consume newline
                            ms.deleteMovie(movieId);
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 5:
                        if (ms.isLoggedIn()) {
                            ms.movieInfo();
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 6:
                        if (ms.isLoggedIn()) {
                            mms.memberInfo();
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 7:
                        if (ms.isLoggedIn()) {
                            ds.discountEnrollment();
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 8:
                        if (ms.isLoggedIn()) {
                            updateDiscount();
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 9:
                        if (ms.isLoggedIn()) {
                            ds.deleteDiscount();
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 10:
                        if (ms.isLoggedIn()) {
                            ds.viewDiscounts();
                        } else {
                            System.out.println("로그인 후 이용해 주세요.");
                        }
                        break;
                    case 11:
                        System.out.println("관리자님이 로그아웃 하였습니다.");
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println("번호를 잘못 입력하셨습니다.");
                }
            }
        }
    }

    private static Movie createMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("영화 제목을 입력하세요: ");
        String title = sc.nextLine();
        System.out.println("영화 장르를 입력하세요(, 사용): ");
        String[] genre = sc.nextLine().split(",");
        System.out.println("상영시간을 입력하세요(분 단위): ");
        int runningTime = sc.nextInt();
        System.out.println("영화 가격을 입력하세요: ");
        int price = sc.nextInt();
        sc.nextLine(); // consume newline
        return new Movie(title, genre, runningTime, price);
    }

    private static void updateMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 영화의 ID를 입력하세요: ");
        int movieId = sc.nextInt();
        sc.nextLine(); // consume newline

        boolean done = false;
        String title = null;
        String[] genre = null;
        int runningTime = 0;
        int price = 0;

        while (!done) {
            System.out.println("수정할 항목을 선택하세요:");
            System.out.println("1. 제목 수정");
            System.out.println("2. 장르 수정");
            System.out.println("3. 상영시간 수정");
            System.out.println("4. 가격 수정");
            System.out.println("5. 수정 완료");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("새로운 제목을 입력하세요: ");
                    title = sc.nextLine();
                    break;
                case 2:
                    System.out.println("새로운 장르를 입력하세요 (콤마로 구분): ");
                    genre = sc.nextLine().split(",");
                    break;
                case 3:
                    System.out.println("새로운 상영시간을 입력하세요 (분 단위): ");
                    runningTime = sc.nextInt();
                    sc.nextLine(); // consume newline
                    break;
                case 4:
                    System.out.println("새로운 가격을 입력하세요: ");
                    price = sc.nextInt();
                    sc.nextLine(); // consume newline
                    break;
                case 5:
                    done = true;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해 주세요.");
            }
        }
        Movie currentMovie = ms.getMovieById(movieId);
        if (title == null) title = currentMovie.getMovieTitle();
        if (genre == null) genre = currentMovie.getGenre();
        if (runningTime == 0) runningTime = currentMovie.getRunningTime();
        if (price == 0) price = currentMovie.getMoviePrice();

        ms.updateMovie(movieId, title, genre, runningTime, price);
    }

    private static void updateDiscount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 할인 ID를 입력하세요: ");
        int discountId = sc.nextInt();
        sc.nextLine(); // consume newline

        boolean done = false;

        while (!done) {
            System.out.println("수정할 항목을 선택하세요:");
            System.out.println("1. 할인율 수정");
            System.out.println("2. 적용 생일 수정");
            System.out.println("3. 수정 완료");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("새로운 할인율을 입력하세요: ");
                    double discountPercentage = sc.nextDouble();
                    sc.nextLine(); // consume newline
                    ds.updateDiscountPercentage(discountId, discountPercentage);
                    break;
                case 2:
                    System.out.println("새로운 할인 적용 생일을 입력하세요 (YYYY-MM-DD): ");
                    String birthDateInput = sc.nextLine();
                    ds.updateDiscountBirthday(discountId, birthDateInput);
                    break;
                case 3:
                    done = true;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해 주세요.");
            }
        }
    }
}
