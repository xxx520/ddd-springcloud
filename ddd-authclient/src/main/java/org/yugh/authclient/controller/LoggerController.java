package org.yugh.authclient.controller;

import com.alibaba.fastjson.JSON;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugh.authclient.entity.auth.QUserEntity;
import org.yugh.authclient.entity.auth.UserEntity;
import org.yugh.authclient.repository.auth.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

/**
 * //sl4j官方日志声明推荐测试
 *
 * @author: 余根海
 * @creation: 2019-05-08 18:27
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@RestController
@RequestMapping("/log")
public class LoggerController {

    /**
     * logback 官方建议
     */
    static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * jpa测试
     */
    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;


    private JPAQueryFactory jpaQueryFactory;


    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * queryDsl 实现
     *
     * @return
     */
    @GetMapping("testUser")
    public Optional<QUserEntity> testUser() {

        QUserEntity qUserEntity = QUserEntity.userEntity;

        //Predicate predicate = qUserEntity.name.eq("yugh");
        Predicate predicate = qUserEntity.id.eq(16L);

        return userRepository.findOne(predicate);
    }


    /**
     * JPAQueryFactory
     * 查询整表
     * 或者set一个条件，传对象查询
     *
     * @return
     */
    @GetMapping("testUser1")
    public QueryResults<UserEntity> testUser1() {
        QUserEntity qUserEntity = QUserEntity.userEntity;

        /**
         * QueryResults 包含分页数据
         * List则只返回一个集合
         */
        //List<UserEntity> list = jpaQueryFactory.selectFrom(qUserEntity).fetch();

        qUserEntity.id.eq(16L);
        QueryResults<UserEntity> list = jpaQueryFactory.selectFrom(qUserEntity).fetchResults();

        return list;
    }


    /**
     * 条件查询
     *
     * @param name
     * @return
     */
    @GetMapping("testUser2")
    public UserEntity testUser2(String name) {
        QUserEntity qUserEntity = QUserEntity.userEntity;

        name = "yugh";

        long id = 16;

        UserEntity userEntity = jpaQueryFactory.selectFrom(qUserEntity)
                .where(qUserEntity.name.eq(name)
                        .and(qUserEntity.id.eq(id))).
                        fetchOne();

        return userEntity;
    }


    /**
     * 条件查询
     *
     * @return
     */
    @GetMapping("testUser3")
    public UserEntity testUser3() {

        String name = "yugh";
        long id = 16;

        QUserEntity qUserEntity = QUserEntity.userEntity;

        return jpaQueryFactory.select(qUserEntity)
                .from(qUserEntity)
                .where(qUserEntity.name.eq(name).and(qUserEntity.id.eq(id)))
                .fetchOne();
    }


    /**
     * @return
     */
    @GetMapping("testUser4")
    public List<UserEntity> testUser4() {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        QUserEntity name = new QUserEntity("1");


        logger.info(JSON.toJSONString(name));
        return jpaQueryFactory.selectFrom(qUserEntity)
                .innerJoin(qUserEntity)
                .on(qUserEntity.id.intValue()
                        .eq(name.id.intValue()))
                .fetch();
    }


    @GetMapping("testUser5")
    public List<UserEntity> testUser5(){
        QUserEntity qUserEntity = QUserEntity.userEntity;


        return jpaQueryFactory.selectFrom(qUserEntity)
                .orderBy(qUserEntity.id.desc())
                .fetch();
    }

}
