package com.kinger.iohackathon;

import com.kinger.iohackathon.entity.User;
import com.kinger.iohackathon.entity.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class IoHackathonApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        User user = new User("running", "123456");
        user = userRepository.save(user);
        if (log.isInfoEnabled()) {
            log.info("插入数据的id为：【{}】", user.getId());
        } else {
            log.error("23232插入数据的id为：【{}】", user.getId());
        }
    }

//    @Test
//    void findByUserName() {
//        User user = (User) userRepository.findByUserName("river");
//        assertNotEquals(user, null);
//        log.info(user.getPassword());
//    }
//
//    @Test
//    void update() {
//        User user = (User) userRepository.findByUserName("river");
//        user.setPassword("123456");
//        userRepository.save(user);
//        assertNotEquals(user, null);
//        log.info(user.getPassword());
//    }
//
//    @Test
//    void findByUserNameEndsWith() {
//        List userList = userRepository.findByUserNameEndsWith("ver3");
//        assertNotEquals(userList.size(), 1);
//    }
//
//    @Test
//    void findByUserNameStartsWitgh() {
//        ArrayList<User> userList = userRepository.findByUserNameStartsWith("river");
//        assertNotEquals(userList.size(), 0);
//        log.info(userList.get(0).getPassword());
//    }
//
//    @Test
//    void findByUserNameContains() {
//        List userList = userRepository.findByUserNameContains("3");
//        assertNotEquals(userList.size(), 1);
//        //log.info(userList.get(0).getpassowrd());
//
//    }
//
//    @Test
//    void findByUserNameLike() {
//        List userList = (List) userRepository.findByUserNameLike("river%");
//        assertNotEquals(userList.size(), 0);
//        log.info("size:{}", userList.size());
//        userList = userRepository.findByUserNameLike("river_");
//        assertNotEquals(userList.size(), 0);
//        log.info("size:{}", userList.size());
//    }
}
