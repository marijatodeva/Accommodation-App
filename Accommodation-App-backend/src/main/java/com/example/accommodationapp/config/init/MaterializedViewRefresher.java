package com.example.accommodationapp.config.init;

import com.example.accommodationapp.repository.AccommodationsPerHostViewRepository;
import com.example.accommodationapp.repository.HostsPerCountryViewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewRefresher {
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    public MaterializedViewRefresher(AccommodationsPerHostViewRepository accommodationsPerHostViewRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository) {
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
    }


    @PostConstruct
    public void init() {
        accommodationsPerHostViewRepository.refreshMaterializedView();
        hostsPerCountryViewRepository.refreshMaterializedView();
    }
}
