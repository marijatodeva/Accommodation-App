package com.example.accommodationapp.listeners;

import com.example.accommodationapp.events.HostEvents;
import com.example.accommodationapp.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostEvent(HostEvents event) {
        String action = event.getAction();

        if ("save".equalsIgnoreCase(action) ||
                "update".equalsIgnoreCase(action) ||
                "delete".equalsIgnoreCase(action)) {

            this.hostService.refreshMaterializedView();
        }
    }
}