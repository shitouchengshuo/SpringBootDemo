package com.qiqi.service;

import com.qiqi.config.ProductionConfig;
import com.qiqi.controller.UserController;
import com.qiqi.dao.UserMongoRepository;
import com.qiqi.dao.UserRepository;
import com.qiqi.model.VO.UserVO;
import com.qiqi.model.entity.UserBean;
import com.qiqi.model.entity.UserMongoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @date 2018/12/20 13:39
 */
@Service
public class UserService {

    @Autowired
    private UserMongoRepository userMongoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 通过user_id获取用户
     * @param userId
     * @return
     */
    public UserVO getUser(long userId){
        UserVO userVO = new UserVO();
        UserBean user = userRepository.findTop1ById(userId);
        //userMongoRepository.findTop1ByUserId(2);
        if (user != null){
            userVO.setUserId(user.getId());
            userVO.setAge(user.getAge());
            userVO.setName(user.getName());
        }
        return userVO;
    }

    /**
     * 保存用户
     * @param userId
     * @return
     */
    public void saveUser(long userId, int age, String name){
        UserBean userBean = new UserBean(userId, age, name);
        userRepository.save(userBean);
        UserMongoBean userMongoBean = new UserMongoBean(userId, age, name);
        userMongoRepository.save(userMongoBean);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public void deleteUser(long userId, int age, String name){
        //先查询数据是否存在，再删除
        // if (userRepository.existsById(userId)){
             userRepository.deleteById(userId);
        // }else {
        //     logger.error("userId：" + userId +": 用户不存在，不能删除");
        // }
        // if (userRepository.existsByName(name)){
            userRepository.deleteAllByName(name);
        // }else {
        //     logger.error("userName：" + name +": 用户不存在，不能删除");
        // }
        //
        // userRepository.deleteAllByAge(age);
        System.out.println("------"+name+"-------");
        userMongoRepository.deleteAllByUserId(userId);
        userMongoRepository.deleteAllByAge(age);
        userMongoRepository.deleteAllByName(name);
    }

}
