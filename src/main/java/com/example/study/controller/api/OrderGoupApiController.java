package com.example.study.controller.api;

import com.example.study.controller.CurdController;
import com.example.study.ifs.CurdInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.service.OrderGroupApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGoupApiController extends CurdController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}
