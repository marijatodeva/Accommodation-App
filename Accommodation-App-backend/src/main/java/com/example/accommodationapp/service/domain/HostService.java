package com.example.accommodationapp.service.domain;

import com.example.accommodationapp.model.domain.Host;
import com.example.accommodationapp.model.projections.HostProjection;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(Host host);
    Optional<Host> update(Long id, Host host);
    void refreshMaterializedView();
    void deleteById(Long id);
    List<HostProjection>getAllHostNames();
}
