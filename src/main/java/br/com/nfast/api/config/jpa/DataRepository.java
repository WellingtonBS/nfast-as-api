package br.com.nfast.api.config.jpa;

import br.com.nfast.api.utils.Cast;
import br.com.nfast.api.utils.Classes;
import br.com.nfast.api.utils.Reflections;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Lazy
@Transactional
@NoRepositoryBean
public abstract class DataRepository<T, ID> implements PagingAndSortingRepository<T, ID> {
    private final Class<T> model;
    @PersistenceContext
    protected EntityManager em;
    private Field fieldId;

    public DataRepository(Class<T> model) {
        super();
        this.model = model;
        fieldId = Reflections.findField(model, Id.class);
        if (fieldId == null)
            fieldId = Reflections.findField(model, EmbeddedId.class);
        if (fieldId == null)
            throw new RuntimeException("Missing ID Property in Domain Class " + model.getName());
    }

    @Override
    public <S extends T> S save(S entity) {
        if (isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            S merged = em.merge(entity);
            return merged;
        }
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        List<S> saved = new ArrayList<>();
        iterable.forEach(item -> {
            S entity = save(item);
            saved.add(entity);
        });
        return saved;
    }

    @Override
    public Optional<T> findById(ID id) {
        Assert.notNull(id, "Missing ID");
        return Optional.ofNullable(em.find(model, id));
    }

    @Override
    public boolean existsById(ID id) {
        Assert.notNull(id, "Missing ID");
        return findById(id).isPresent();
    }

    @Override
    public Iterable<T> findAll() {
        TypedQuery<T> query = em.createQuery("SELECT a FROM " + model.getSimpleName() + " a", model);
        return query.getResultList();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> iterable) {
        StringBuilder items = new StringBuilder();
        iterable.forEach(id -> {
            if (items.length() > 0) items.append(",");
            items.append(id);
        });

        if (items.length() > 0) {
            TypedQuery<T> query = em.createQuery("SELECT a FROM " + model.getSimpleName() + " a WHERE a." + fieldId.getName() + " IN(" + items.toString() + ")", model);
            return query.getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM " + model.getSimpleName()).append(" a ");
        int count = 0;
        for (Sort.Order order : sort) {
            count++;
            jpql.append(count == 1 ? "ORDER BY " : ", ");
            jpql.append(order.getProperty()).append(" ").append(order.getDirection());
        }

        TypedQuery<T> query = em.createQuery(jpql.toString(), model);
        return query.getResultList();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM " + model.getSimpleName()).append(" a ");
        int count = 0;
        for (Sort.Order order : pageable.getSort()) {
            count++;
            jpql.append(count == 1 ? "ORDER BY " : ", ");
            jpql.append(order.getProperty()).append(" ").append(order.getDirection());
        }

        int limit = pageable.getPageSize();
        int offset = pageable.getPageNumber() * pageable.getPageSize();

        TypedQuery<T> query = em.createQuery(jpql.toString(), model);
        if (limit > 0) query.setMaxResults(limit);
        if (offset > 0) query.setFirstResult(offset);

        return new PageImpl<>(query.getResultList(), pageable, count());
    }

    @Override
    public long count() {
        Query query = em.createQuery("SELECT COUNT(a) FROM " + model.getSimpleName() + " a");
        return (Long) query.getSingleResult();
    }

    @Override
    public void deleteById(ID id) {
        Assert.notNull(id, "Missing ID");
        findById(id).ifPresent(this::delete);
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        ids.forEach(id -> findById(id).ifPresent(this::delete));
    }

    @Override
    public void delete(T entity) {
        if (entity != null)
            em.remove(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        iterable.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM " + model.getSimpleName() + " a");
        query.executeUpdate();
    }

    public void deleteAllByItemId(Iterable<? extends T> entities) {
        entities.forEach(item -> {
            ID id = Cast.of(Classes.getValue(item, fieldId));
            findById(id).ifPresent(this::delete);
        });
    }

    public <V> V findValue(Consumer<QueryBuilder> event) {
        QueryBuilder search = new QueryBuilder();
        event.accept(search);

        Query query = em.createQuery(search.toString());
        for (String param : search.getParams().keySet()) {
            Object value = search.getParams().get(param);
            query.setParameter(param, value);
        }

        return Cast.of(query.getSingleResult());
    }

    public T find(Consumer<QueryBuilder> event) {
        List<T> list = findAll(event);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<T> findAll(Consumer<QueryBuilder> event) {
        QueryBuilder search = new QueryBuilder();
        event.accept(search);
        return findAll(search);
    }

    public List<T> findAll(QueryBuilder search) {
        TypedQuery<T> query = em.createQuery(search.toString(), model);
        for (String param : search.getParams().keySet()) {
            Object value = search.getParams().get(param);
            query.setParameter(param, value);
        }

        if (search.getLimit() > 0) query.setMaxResults(search.getLimit());
        if (search.getOffset() > 0) query.setFirstResult(search.getOffset());

        return query.getResultList();
    }

    public T findBy(Object... params) {
        return find(query -> {
            query.add("SELECT a FROM " + model.getSimpleName() + " a ");

            int index = -1;
            String param = null;
            for (Object item : params) {
                index++;
                if (index % 2 == 0) {
                    param = item.toString();
                    query.add((index == 0 ? "WHERE" : "AND") + " a." + param + " = :" + param);
                } else {
                    query.set(param, item);
                }
            }
        });
    }

    public List<T> findAllBy(Object... params) {
        return findAll(query -> {
            query.add("SELECT a FROM " + model.getSimpleName() + " a ");

            int index = -1;
            String param = null;
            for (Object item : params) {
                index++;
                if (index % 2 == 0) {
                    param = item.toString();
                    query.add((index == 0 ? "WHERE" : "AND") + " a." + param + " = :" + param);
                } else {
                    query.set(param, item);
                }
            }
        });
    }

    public T nativeFind(Consumer<QueryBuilder> event) {
        List<T> list = nativeFindAll(event);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<T> nativeFindAll(Consumer<QueryBuilder> event) {
        QueryBuilder search = new QueryBuilder();
        event.accept(search);
        return nativeFindAll(search);
    }

    public List<T> nativeFindAll(QueryBuilder search) {
        Query query = em.createNativeQuery(search.toString(), model);
        for (String param : search.getParams().keySet()) {
            Object value = search.getParams().get(param);
            query.setParameter(param, value);
        }

        if (search.getLimit() > 0) query.setMaxResults(search.getLimit());
        if (search.getOffset() > 0) query.setFirstResult(search.getOffset());

        return Cast.of(query.getResultList());
    }

    public <V> V nativeFind(Consumer<QueryBuilder> event, Class<V> type) {
        List<V> list = nativeFindAll(event, type);
        return list.size() > 0 ? list.get(0) : null;
    }

    public <V> List<V> nativeFindAll(Consumer<QueryBuilder> event, Class<V> type) {
        QueryBuilder search = new QueryBuilder();
        event.accept(search);
        return nativeFindAll(search, type);
    }

    public <V> List<V> nativeFindAll(QueryBuilder search, Class<V> type) {
        Query query = em.createNativeQuery(search.toString(), type);
        for (String param : search.getParams().keySet()) {
            Object value = search.getParams().get(param);
            query.setParameter(param, value);
        }

        if (search.getLimit() > 0) query.setMaxResults(search.getLimit());
        if (search.getOffset() > 0) query.setFirstResult(search.getOffset());

        return Cast.of(query.getResultList());
    }

    public <V> V nativeFindValue(String sql) {
        Query q = em.createNativeQuery(sql);
        List<V> list = q.getResultList();
        return list.size() > 0 ? Cast.of(list.get(0)) : null;
    }

    public <V> V nativeFindValue(Consumer<QueryBuilder> event) {
        QueryBuilder query = new QueryBuilder();
        event.accept(query);
        Query q = em.createNativeQuery(query.toString());
        for (String param : query.getParams().keySet()) {
            Object value = query.getParams().get(param);
            q.setParameter(param, value);
        }

        List<V> list = q.getResultList();
        return list.size() > 0 ? Cast.of(list.get(0)) : null;
    }

    public void executeNative(String query) {
        Query q = em.createNativeQuery(query);
        q.executeUpdate();
    }

    public void executeNative(Consumer<QueryBuilder> event) {
        QueryBuilder query = new QueryBuilder();
        event.accept(query);
        Query q = em.createNativeQuery(query.toString());
        for (String param : query.getParams().keySet()) {
            Object value = query.getParams().get(param);
            q.setParameter(param, value);
        }
        q.executeUpdate();
    }

    public boolean isNew(T entity) {
        return Classes.getValue(entity, fieldId) == null;
    }

}
