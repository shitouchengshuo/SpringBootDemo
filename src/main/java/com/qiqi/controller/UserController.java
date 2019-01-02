package com.qiqi.controller;

import com.qiqi.model.Response;
import com.qiqi.model.VO.UserVO;
import com.qiqi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取用户
     * @param: [userId]
     * @return Response
     */
    @RequestMapping(value = "/get" , method = RequestMethod.GET)
    public Response getUser(@RequestParam(value = "user_id", required = true) String userId) {
        Response response = new Response();
        long id = Long.parseLong(userId);
        UserVO userVO = userService.getUser(id);

        response = new Response("0","获取成功",userVO);
        logger.info("获取的结果：id="+ userId + "  result="+response.toString());
        return response;
    }

    /**
     * 保存用户
     */
    @RequestMapping(value = "/save" , method = RequestMethod.GET)
    public Response saveUser(@RequestParam(value = "user_id", required = true) String userId,
                             @RequestParam(value = "age", required = true) String age,
                             @RequestParam(value = "name", required = true) String name) {
        Response response = new Response();
        long id = Long.parseLong(userId);
        userService.saveUser(id, Integer.parseInt(age), name);

        response = new Response("0","保存成功",null);
        logger.info("保存用户：id="+ userId + " age=" + age + "  name=" + name + "  result="+response.toString());
        return response;
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Response deleteUser(@RequestParam(value = "user_id", required = true) String userId,
                               @RequestParam(value = "age", required = true) String age,
                               @RequestParam(value = "name", required = true) String name) {
        Response response = new Response();
        long id = Long.parseLong(userId);
        int age_ = Integer.parseInt(age);
        userService.deleteUser(id, age_, name);

        response = new Response("0","删除用户成功",null);
        logger.info("删除用户：id="+ userId +"  name=" + name + "  result="+response.toString());
        return response;
    }


}
