package com.ohgiraffers.MovieApp.Repository;

import com.ohgiraffers.MovieApp.aggregate.Member;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberRepository {

    private ArrayList<Member> memberList = new ArrayList<>();



    // 주석. 멤버 레파지토리 생성자 -> 멤버 파일 생성하고, 리스트에 멤버 담기
    public MemberRepository() {
        File memberFile = new File("src/main/java/com/ohgiraffers/MovieApp/db/memberDB=-[.dat");     // 주석. member 정보 담은 file 생성

        // 주석. 생성자, 실행할 때 마다 덮어씌우지(초기화되지) 못하도록 조건문 작성 (파일이 없을 경우에만 초기 세팅이 되도록) -> exists 메소드
        if (!memberFile.exists()){
            ArrayList<Member> defaultMembers = new ArrayList<>(); //주석. 기존 회원 담는 ArrayList-> defaultMembers
            // 주석. 파일이 없으면 파일을 생성한 후 기존 회원의 목록 담기
            defaultMembers.add(new Member("회원1", "user01", "pass01", LocalDate.of(2001, 04, 15)));
            defaultMembers.add(new Member("회원2", "user02", "pass02", LocalDate.of(2002, 05, 17)));
            defaultMembers.add(new Member("회원3", "user03", "pass03", LocalDate.of(2003, 06, 19)));
            defaultMembers.add(new Member("회원4", "user04", "pass04", LocalDate.of(2004, 07, 13)));

            saveMembers(memberFile, defaultMembers);


        }

        loadMember(memberFile);

    }


    public void addMember(Member newMember) {
        memberList.add(newMember);
        saveMembers(new File("src/main/java/com/ohgiraffers/MovieApp/db/memberDB.dat"), memberList);
    }

    // 주석. MemberRepository 클래스 메소드 -> tryMemberLogin 메소드 : id & pw 모두 일치하면 해당 회원 반환
    public Member tryMemberLogin(String id, String pwd){

        for( Member member: memberList ){
            if(member.getId().equals(id) && member.getPwd().equals(pwd)){
                return member;
            }
        } return null;
    }


    // 주석. 멤버 가입 저장
    private static void saveMembers(File file, ArrayList<Member> members) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );

            /* 설명. 초기 회원 세명을 각각 객체 출력 내보내기 */
            for (Member member : members) {
                oos.writeObject(member);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    // 주석. 멤버 출력
    private void loadMember(File file) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)
                    )
            );

            while (true) {
                memberList.add((Member) ois.readObject());
            }
        } catch (EOFException e) {
            System.out.println("회원 정보 모두 로딩됨…");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }






}
