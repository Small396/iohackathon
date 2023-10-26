package com.kinger.iohackathon.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @ClassName: UserRepository
 * @Description: 继承JpaRepository<User,Integer>，User是实体类，Integer是主键
 * @Author: lizg
 * @Date: 2023/10/26 11:19
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Override
    Page<User> findAll(Pageable pageable);

}
