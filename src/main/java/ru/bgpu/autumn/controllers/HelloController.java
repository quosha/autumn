package ru.bgpu.autumn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.bgpu.autumn.dto.HelloMessageDTO;
import ru.bgpu.autumn.services.HelloService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired private HelloService helloService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public HelloMessageDTO index(Authentication authentication) {
        return new HelloMessageDTO(
                authentication.getName(),
                helloService.message(
                        authentication.getAuthorities().stream().map(
                                g -> g.getAuthority()
                        ).collect(Collectors.toList()).toString()
                )
        );
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public HelloMessageDTO testAdmin() {
        return new HelloMessageDTO("Admin connect", "ADMIN");
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyAuthority({'MANAGER','ADMIN'})")
    public HelloMessageDTO testManager() {
        return new HelloMessageDTO("Manager connect", "MANAGER");
    }

}
