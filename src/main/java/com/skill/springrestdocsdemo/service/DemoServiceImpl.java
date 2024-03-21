package com.skill.springrestdocsdemo.service;

import com.skill.springrestdocsdemo.controller.dto.DemoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    private Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    private final static String id = "demo-test-id";

    @Override
    public String create(String name, String description) {
        LOGGER.info("Creating a new demo with name: {} and description: {}", name, description);
        return id;
    }

    @Override
    public DemoView get(String id) {
        LOGGER.info("Reading demo with id: {}", id);
        return new DemoView(id, "demo-test-name", "demo-test-description");
    }

    @Override
    public String update(String id, String name, String description) {
        LOGGER.info("Updating demo with id: {} with name: {} and description: {}", id, name, description);
        return id;
    }

    @Override
    public void delete(String id) {
        LOGGER.info("Deleting demo with id: {}", id);
    }
}
