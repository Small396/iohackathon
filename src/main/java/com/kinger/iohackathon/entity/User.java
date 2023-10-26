package com.kinger.iohackathon.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: User
 * @Description: user表对应的实体类
 * @Author: lizg
 * @Date: 2023/10/26 11:20
 */
@Data
@Entity
@Table(name = "t_user")
/** 数据库中表名，如果不存在则提示：Table 'test.user' doesn't exist*/
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**逻辑主键自动生成主键值, 针对 mysql 选择此项，保存后，就可以获取到刚插入的数据的id*/
    @Column(name = "id")
    private int id;
    @Column(name = "name") /**数据库中的字段名称*/
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "create_time")
    private Date create_time;
    @Column(name = "update_time")
    private Date update_time = new Date();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {

    }
}

