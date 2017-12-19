package com.mifan.quiz.service;

import org.moonframework.model.mybatis.criterion.Criterion;
import org.moonframework.model.mybatis.domain.BaseEntity;
import org.moonframework.model.mybatis.domain.Field;
import org.moonframework.model.mybatis.domain.Pair;
import org.moonframework.model.mybatis.repository.BaseDao;
import org.moonframework.model.mybatis.service.AbstractBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.mifan.quiz.service.BaseServiceAdapter.TRANSACTION_MANAGER;


/**
 * @author zyw
 * @version 1.0
 * @since 2017/12/12
 */
@Transactional(transactionManager = TRANSACTION_MANAGER)
public abstract class BaseServiceAdapter<T extends BaseEntity, E extends BaseDao<T>> extends AbstractBaseService<T, E> {

    protected static final String TRANSACTION_MANAGER = "quizTransactionManager";

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public T findOne(Long id) {
        return super.findOne(id);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public T findOne(Long id, Iterable<? extends Field> fields) {
        return super.findOne(id, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public T findOne(T entity) {
        return super.findOne(entity);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public T findOne(T entity, Iterable<? extends Field> fields) {
        return super.findOne(entity, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public List<T> findAll(T entity) {
        return super.findAll(entity);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public List<T> findAll(T entity, Iterable<? extends Field> fields) {
        return super.findAll(entity, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public List<T> findAll(Iterable<Long> ids) {
        return super.findAll(ids);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public List<T> findAll(Iterable<Long> ids, Iterable<? extends Field> fields) {
        return super.findAll(ids, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public List<T> findAll(String field, Iterable<Long> ids) {
        return super.findAll(field, ids);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public List<T> findAll(String field, Iterable<Long> ids, Iterable<? extends Field> fields) {
        return super.findAll(field, ids, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public List<T> findAll(Criterion criterion) {
        return super.findAll(criterion);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public List<T> findAll(Criterion criterion, Iterable<? extends Field> fields) {
        return super.findAll(criterion, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public Page<T> findAll(T entity, Pageable pageable) {
        return super.findAll(entity, pageable);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public Page<T> findAll(T entity, Pageable pageable, Iterable<? extends Field> fields) {
        return super.findAll(entity, pageable, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public Page<T> findAll(Criterion criterion, Pageable pageable) {
        return super.findAll(criterion, pageable);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public Page<T> findAll(Criterion criterion, Pageable pageable, Iterable<? extends Field> fields) {
        return super.findAll(criterion, pageable, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public Page<T> findAll(Criterion criterion, Pageable pageable, boolean count) {
        return super.findAll(criterion, pageable, count);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public Page<T> findAll(Criterion criterion, Pageable pageable, Iterable<? extends Field> fields, boolean count) {
        return super.findAll(criterion, pageable, fields, count);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public Page<Map<String, Object>> queryForList(Criterion criterion, Pageable pageable) {
        return super.queryForList(criterion, pageable);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public Page<Map<String, Object>> queryForList(Criterion criterion, Pageable pageable, Iterable<? extends Field> fields) {
        return super.queryForList(criterion, pageable, fields);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public <E1> Page<Map<String, Object>> queryForList(Iterable<E1> keys) {
        return super.queryForList(keys);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public <E1> Page<Map<String, Object>> queryForList(Iterable<E1> keys, int type) {
        return super.queryForList(keys, type);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public long count() {
        return super.count();
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public long count(T entity) {
        return super.count(entity);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public long count(Criterion criterion) {
        return super.count(criterion);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public boolean exists(Long id) {
        return super.exists(id);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public boolean exists(T entity) {
        return super.exists(entity);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public T queryForObject(Long id) {
        return super.queryForObject(id);
    }

    @Transactional(readOnly = true, transactionManager = TRANSACTION_MANAGER)
    @Override
    public T queryForObject(Long id, Iterable<? extends Field> fields) {
        return super.queryForObject(id, fields);
    }

    @Override
    public <S extends T> int saveOrUpdate(S insert, S update) {
        return super.saveOrUpdate(insert, update);
    }

    @Override
    public <S extends T> int saveOrUpdate(S entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    public <S extends T> int[] saveOrUpdate(Iterable<S> entities) {
        return super.saveOrUpdate(entities);
    }

    @Override
    public <S extends T> int save(S entity) {
        return super.save(entity);
    }

    @Override
    public <S extends T> int[] save(Iterable<S> entities) {
        return super.save(entities);
    }

    @Override
    public int update(Long id, List<Pair> increments, List<Pair> fields) {
        return super.update(id, increments, fields);
    }

    @Override
    public <S extends T> int update(S entity) {
        return super.update(entity);
    }

    @Override
    public <S extends T> int update(Iterable<S> entities) {
        return super.update(entities);
    }

    @Override
    public <S extends T> int update(S entity, Criterion criterion) {
        return super.update(entity, criterion);
    }

    @Override
    public int delete(Long id) {
        return super.delete(id);
    }

    @Override
    public int delete(T entity) {
        return super.delete(entity);
    }

    @Override
    public int[] delete(Iterable<Long> ids) {
        return super.delete(ids);
    }

    @Override
    public int remove(Long id) {
        return super.remove(id);
    }

    @Override
    public int remove(Long id, Criterion criterion) {
        return super.remove(id, criterion);
    }

    @Override
    public int remove(Iterable<Long> ids) {
        return super.remove(ids);
    }

    @Override
    public int remove(Iterable<Long> ids, Criterion criterion) {
        return super.remove(ids, criterion);
    }

    @Override
    public <U> U transactional(Supplier<U> supplier) {
        return super.transactional(supplier);
    }

}
