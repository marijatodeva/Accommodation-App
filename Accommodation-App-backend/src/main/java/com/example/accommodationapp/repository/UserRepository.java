package com.example.accommodationapp.repository;
import com.example.accommodationapp.model.domain.User;
import com.example.accommodationapp.model.enumerations.Role;
//import com.example.accommodationapp.model.projections.UserProjection;
import com.example.accommodationapp.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"temporaryReservations"}
    )
    @Query("SELECT u FROM User u")
    List<User> findAllWithoutTemporaryReservationsFetched();

    UserProjection findByRole(Role role);

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

}
