package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
    @Autowired  //DI(Dependancy Injection)직접 객체를 만들지 않고 spring이 직접 관리, 주입 = new 해줄 필요 없다.
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test03";
        String password = "Test03";
        UserStatus status = UserStatus.REGISTERED;
        String email = "TEST01@gmail.com";
        String phoneNumber= "010-3333-3333";
        LocalDateTime registeredAt= LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user= new User();
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setAccount(account);
        user.setRegisteredAt(registeredAt);
        user.setStatus(status);
        user.setPassword(password);

        /*@Builder 사용 시 가능 , 매개변수 몇개만 가지고 사용하는 생성자 생성, 요즘 많이 사용
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status).build();*/

        User newUser=userRepository.save(user);
        Assertions.assertNotNull(newUser);

        /*//String sql= insert into user(%s,%s,%d) value(account, email, age);
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-2111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser03");

        User newUser = userRepository.save(user);
        System.out.println(newUser);*/
    }

    @Test
    @Transactional
    public void read(){

        User user= userRepository.findFirstByPhoneNumberOrderByIdDesc("010-2222-3333");

        /*@Accessors(chain=true)의 사용법
        user
                .setEmail("")
                .setStatus("")
                .setPhoneNumber("");

         User u = new User().setAccount("").setEmail("").setPassword();*/


        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("가격 :"+orderGroup.getTotalPrice());

            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("주문의 상태 :"+orderDetail.getStatus());

                System.out.println("주문상품 :"+orderDetail.getItem().getName());

                System.out.println("파트너사이름 :"+orderDetail.getItem().getPartner().getName());

                System.out.println("파트너사 카테고리 :"+orderDetail.getItem().getPartner().getCategory().getTitle());

            });
        });

        Assertions.assertNotNull(user);
        /*//select * from user where id = ?
        //Optional<User> user= userRepository.findById(7L);

        Optional<User> user= userRepository.findByAccount("TestUser03");

        user.ifPresent(selectUser->{
            selectUser.getOrderDetailList().stream().forEach(orderDetail -> {
                Item item= orderDetail.getItem();
                System.out.println(item);
            });
        });*/
    }

    @Test
    public void update(){
        Optional<User> user= userRepository.findById(2L);

        user.ifPresent(selectUser->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional  //실행은 되지만 DB에 적용되지는 않는다. 테스트 시 사용 가능
    public void delete(){
        Optional<User> user= userRepository.findById(4L);

        Assertions.assertTrue(user.isPresent());    //반드시 2번이라는 데이터가 DB에 존재하여야 한다. 값이 들어있는지 확인

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser= userRepository.findById(2L);

        /*if(deleteUser.isPresent()){
            //데이터 있음
        }
        else{
            //데이터 없음
        }*/
        Assertions.assertFalse(deleteUser.isPresent()); //삭제된 데이터가 false 인지 확인한다.
    }

}
