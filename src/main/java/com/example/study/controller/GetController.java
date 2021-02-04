package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")     //Localhost::8080/api
public class GetController {

    @RequestMapping(method= RequestMethod.GET, path="/getMethod")    //localhist:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter")    //Localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam String password){
        //String password=":bbbb";

        System.out.println("id : "+id);
        System.out.println("password : "+ password);

        return id+password;
    }

    //Localhost:8080/api/multiParameter?account=abcd&email=study@gmail.com&page=10
    //parameter가 많아지면 객체로 만들어 받는 방법이 더 편하다.
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){

        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //{"account : " "","email" : "","page" : 0}
        return searchParam;     //json 형태로 return 된다.
    }

    @GetMapping("/header")
    public Header getHeader(){

        //{"result" : "OK" , "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}