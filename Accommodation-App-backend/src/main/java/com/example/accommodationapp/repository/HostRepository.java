package com.example.accommodationapp.repository;

import com.example.accommodationapp.model.domain.Host;
import com.example.accommodationapp.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<HostProjection> findAllBy();
}
