package io.pivotal.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.pivotal.demo.Schedule;

import java.util.List;

@FeignClient("RepairService")
public interface RepairClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ServiceOpenings/${dealerId}")
    List<Schedule> openings(@RequestParam("dealerId") Long dealerId);

}