package com.example.study.service;

import com.example.study.ifs.CurdInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse,User> {


    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1.read data
        UserApiRequest userApiRequest = request.getData();

        //2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser= baseRepository.save(user);

        // 3. 생성된 데이터 ->userApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //id -> repository getOne, getById
        Optional<User> optional = baseRepository.findById(id);

        //user-> userApiResponse return
        return optional
                .map(user->response(user))
                .orElseGet(()->Header.ERROR("데이터없음"));

        /*
        람다식으로 줄인 버전
        return userRepository.findById(id)
                .map(user->response(user))
                .orElseGet(()->Header.ERROR("데이터없음"));
        */
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data
        UserApiRequest userApiRequest = request.getData();

        // 2. id->user 데이터를 찾고
        Optional<User> optional= baseRepository.findById(userApiRequest.getId());

        return optional.map(user->{
            // 3. data -> update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setStatus(userApiRequest.getStatus())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
            // 4. userApiResponse
        })
                .map(user->baseRepository.save(user))
                .map(updateUser->response(updateUser))
        .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository ->user
        Optional<User> optional = baseRepository.findById(id);

        // 2. repository ->delete
        return optional.map(user->{
            baseRepository.delete(user);
            // 3. response return
            return Header.Ok();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user){
        // user -> suserApiResponse

        UserApiResponse userApiReponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.Ok(userApiReponse);
    }
}
