package com.ohgiraffers.MovieApp.Service;

import com.ohgiraffers.MovieApp.Repository.DiscountRepository;
import com.ohgiraffers.MovieApp.aggregate.Discount;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DiscountService {
    private final DiscountRepository dr = new DiscountRepository();
    private final Scanner scanner = new Scanner(System.in);

    public void discountEnrollment() {
        try {
            System.out.println("할인율을 입력하세요:");
            double discountPercentage = scanner.nextDouble();
            scanner.nextLine();  // consume newline

            System.out.println("할인 적용 생일을 입력하세요 (YYYY-MM-DD):");
            String birthDateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthDateInput, formatter);

            int birthMonth = birthDate.getMonthValue();
            int birthDay = birthDate.getDayOfMonth();

            Discount discount = new Discount(0, discountPercentage, birthMonth, birthDay);
            dr.addDiscount(discount);
            System.out.println("할인이 등록되었습니다: " + discount);
        } catch (DateTimeParseException e) {
            System.out.println("생일 입력 형식이 잘못되었습니다. YYYY-MM-DD 형식으로 입력해주세요.");
        } catch (Exception e) {
            System.out.println("할인 등록 중 오류가 발생했습니다: " + e.getMessage());
            scanner.nextLine();  // consume newline in case of error
        }
    }

    public void updateDiscountPercentage(int discountId, double discountPercentage) {
        Discount discount = dr.findDiscountById(discountId);

        if (discount != null) {
            discount.setDiscountPercentage(discountPercentage);
            dr.updateDiscount(discount);
            System.out.println("할인율이 수정되었습니다: " + discount);
        } else {
            System.out.println("할인을 찾을 수 없습니다.");
        }
    }

    public void updateDiscountBirthday(int discountId, String birthDateInput) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthDateInput, formatter);

            int birthMonth = birthDate.getMonthValue();
            int birthDay = birthDate.getDayOfMonth();

            Discount discount = dr.findDiscountById(discountId);

            if (discount != null) {
                discount.setBirthMonth(birthMonth);
                discount.setBirthDay(birthDay);
                dr.updateDiscount(discount);
                System.out.println("적용 생일이 수정되었습니다: " + discount);
            } else {
                System.out.println("할인을 찾을 수 없습니다.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("생일 입력 형식이 잘못되었습니다. YYYY-MM-DD 형식으로 입력해주세요.");
        } catch (Exception e) {
            System.out.println("할인 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public void deleteDiscount() {
        try {
            System.out.println("삭제할 할인 ID를 입력하세요:");
            int discountId = scanner.nextInt();
            scanner.nextLine();  // consume newline
            Discount discount = dr.findDiscountById(discountId);

            if (discount != null) {
                dr.deleteDiscount(discountId);
                System.out.println("할인이 삭제되었습니다: " + discount);
            } else {
                System.out.println("할인을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("할인 삭제 중 오류가 발생했습니다: " + e.getMessage());
            scanner.nextLine();  // consume newline in case of error
        }
    }

    public void viewDiscounts() {
        System.out.println("현재 등록된 할인 정보:");
        for (Discount discount : dr.getAllDiscounts().values()) {
            System.out.println(discount);
        }
    }

    public double getDiscountForDateAndBirthday(LocalDate currentDate, LocalDate birthDate) {
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();
        int birthMonth = birthDate.getMonthValue();
        int birthDay = birthDate.getDayOfMonth();

        for (Discount discount : dr.getAllDiscounts().values()) {
            if (discount.getBirthMonth() == birthMonth && discount.getBirthDay() == birthDay) {
                return discount.getDiscountPercentage();
            }
        }
        return 0.0;
    }
}
