package me.rin.devroom.repository;

import me.rin.devroom.stats.DungeonUserStatistics;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DungeonRepository extends Repository {

    public DungeonRepository() {
        super("devroom");
        super.annotatedClasses(DungeonUserStatistics.class);
        super.connect();
    }

    public void save(DungeonUserStatistics statistics) {
        super.session.getTransaction().begin();
        super.session.persist(statistics);
        super.session.getTransaction().commit();
    }

    public void delete(DungeonUserStatistics statistics) {
        super.session.getTransaction().begin();
        super.session.remove(statistics);
        super.session.getTransaction().commit();
    }

    public Optional<DungeonUserStatistics> findByUID(UUID uid) {
        return Optional.ofNullable(super.session.get(DungeonUserStatistics.class, uid));
    }

    public DungeonUserStatistics getByUID(UUID uid) {
        return this.findByUID(uid).orElseThrow(() -> new RuntimeException("users not found!"));
    }

    public List<DungeonUserStatistics> findAll() {
        return super.session.createQuery("SELECT a FROM Users a", DungeonUserStatistics.class).getResultList();
    }
}