package com.firrael.find;

import com.firrael.base.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class FindUserRepository implements UserRepository {

    private static Logger log = LoggerFactory.getLogger(FindUserRepository.class);

    private UserRepository userRepository;

    @Autowired
    public FindUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void log(String request, long result) {
        log.info(String.format(request + " request %d ms", TimeUnit.NANOSECONDS.toMillis(result)));
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public User findUserByToken(String token) {
        long startTime = System.nanoTime();
        User user = userRepository.findByToken(token);
        long endTime = System.nanoTime();
        log("findByToken", endTime - startTime);
        return user;
    }

    @Override
    public <S extends User> S save(S entity) {
        long startTime = System.nanoTime();
        S user = userRepository.save(entity);
        long endTime = System.nanoTime();
        log("save entity", endTime - startTime);
        return user;
    }

    @Override
    public <S extends User> Iterable<S> save(Iterable<S> entities) {
        long startTime = System.nanoTime();
        Iterable<S> users = userRepository.save(entities);
        long endTime = System.nanoTime();
        log("save entities", endTime - startTime);
        return users;
    }

    @Override
    public User findOne(Long aLong) {
        long startTime = System.nanoTime();
        User user = userRepository.findOne(aLong);
        long endTime = System.nanoTime();
        log("find one", endTime - startTime);
        return user;
    }

    @Override
    public boolean exists(Long aLong) {
        long startTime = System.nanoTime();
        boolean exists = userRepository.exists(aLong);
        long endTime = System.nanoTime();
        log("exists", endTime - startTime);
        return exists;
    }

    @Override
    public Iterable<User> findAll() {
        long startTime = System.nanoTime();
        Iterable<User> users = userRepository.findAll();
        long endTime = System.nanoTime();
        log("find all", endTime - startTime);
        return users;
    }

    @Override
    public Iterable<User> findAll(Iterable<Long> longs) {
        long startTime = System.nanoTime();
        Iterable<User> users = userRepository.findAll(longs);
        long endTime = System.nanoTime();
        log("find all ids", endTime - startTime);
        return users;
    }

    @Override
    public long count() {
        long startTime = System.nanoTime();
        long count = userRepository.count();
        long endTime = System.nanoTime();
        log("count", endTime - startTime);
        return count;
    }

    @Override
    public void delete(Long aLong) {
        long startTime = System.nanoTime();
        userRepository.delete(aLong);
        long endTime = System.nanoTime();
        log("delete id", endTime - startTime);
    }

    @Override
    public void delete(User entity) {
        long startTime = System.nanoTime();
        userRepository.delete(entity);
        long endTime = System.nanoTime();
        log("delete entity", endTime - startTime);
    }

    @Override
    public void delete(Iterable<? extends User> entities) {
        long startTime = System.nanoTime();
        userRepository.delete(entities);
        long endTime = System.nanoTime();
        log("delete entities", endTime - startTime);
    }

    @Override
    public void deleteAll() {
        long startTime = System.nanoTime();
        userRepository.deleteAll();
        long endTime = System.nanoTime();
        log("delete all", endTime - startTime);
    }
}