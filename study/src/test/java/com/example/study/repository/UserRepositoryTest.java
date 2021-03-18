package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired //의존성 주입 (직접 객체만들지 말고 스프링이 관리해줌)
    private UserRepository userRepository;

    @Test
    public void create() {

        String account = "test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";


        User user = new User();

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read() {

        User user =userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        if (user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("-----------주문묶음---------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());
                System.out.println("----------주문상세---------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : "+ orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println(" 주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착 예정일자 : " + orderDetail.getArrivalDate());

                });
            });
        }
        Assertions.assertNotNull(user);

    }

    @Test
    public void update() {
        //특정 유저를 select
        Optional<User> user = userRepository.findById(2L); //리드와 관련된 메소드 find

        //id를 가지고 한번더 select 그값을 가지고 update
        user.ifPresent((selectUser -> { //selectUser가 optional에 있는 경우 출력
            //selectUser.setId(3L); //-> 이떄는 3번 놈이 수정되므로 코딩 시 주의!
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            //update된 것인지 신규인지 업데이트 인지 인지하는건 id가 중요. id가 존재하면 업데이트로 인지
            userRepository.save(selectUser); //update된 내용을 sava
        }));
    }

    @Test
    @Transactional //쿼리를 실행하더라도 롤백을 시켜준다.
    public void delete() {
        Optional<User> user = userRepository.findById(3L); //리드와 관련된 메소드 find

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L); //리드와 관련된 메소드 find

        Assertions.assertFalse(deleteUser.isPresent());


    }
}
