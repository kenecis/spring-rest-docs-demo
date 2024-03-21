package com.skill.springrestdocsdemo.service;

import com.skill.springrestdocsdemo.controller.dto.DemoView;

public interface DemoService {

    String create(String name, String description);

    DemoView get(String id);

    String update(String id, String name, String description);

    void delete(String id);
}
