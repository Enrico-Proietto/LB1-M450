package ch.app.bookoasis.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class TestAbstractService<T> {
    private final JpaRepository<T, Long> repository;
    protected TestAbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }
    public List<T> getAll() {
        return repository.findAll();
    }
    public T getById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public T save(T entity) {
        return repository.save(entity);
    }
    public void delete(T entity) {
        repository.delete(entity);
    }
}
