package com.skill.springrestdocsdemo.controller;

import com.skill.springrestdocsdemo.controller.dto.DemoForm;
import com.skill.springrestdocsdemo.controller.dto.DemoView;
import com.skill.springrestdocsdemo.service.DemoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    private DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping(value = "")
    public String create(@Valid @RequestBody DemoForm demoDto) {

        return this.demoService.create(demoDto.getName(), demoDto.getDescription());
    }

    @GetMapping(value = "/{id}")
    public DemoView get(@PathVariable("id") String id) {

        return this.demoService.get(id);
    }

    @PutMapping(value = "/{id}")
    public String update(@PathVariable("id") String id, @Valid @RequestBody DemoForm demoDto) {

        return this.demoService.update(id, demoDto.getName(), demoDto.getDescription());
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {

        this.demoService.delete(id);
    }
}
