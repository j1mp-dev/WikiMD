package my.md.wikimd.repositories;

import my.md.wikimd.models.User;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u from User AS u WHERE u.username = :username AND u.password = :password")
    User getUserLogin(@Param("username") String username,@Param("password") String password);

    @Query(value="SELECT u.perm_level, u.created_at, count(*) FROM public.tb_user AS u\n" +
            "FULL OUTER JOIN tb_note AS n ON u.id = n.created_by\n" +
            "WHERE u.id = :id\n" +
            "GROUP BY u.id", nativeQuery = true)
    Tuple getUserData(@Param("id") UUID id);

}
