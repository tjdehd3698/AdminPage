package com.example.study.controller.api;

import com.example.study.ifs.CurdInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CurdInterface<UserApiRequest,UserApiResponse> {   //CurdInterface를 상속받아 CRUD를 무조건 구현

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")    // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")     // /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")     // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")  // /api/user/{id}
    public Header<UserApiResponse> delete(@PathVariable Long id) {
        return userApiLogicService.delete(id);
    }
}
