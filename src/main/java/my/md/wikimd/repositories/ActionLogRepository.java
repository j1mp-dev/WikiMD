package my.md.wikimd.repositories;

import my.md.wikimd.models.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, UUID> {

}
