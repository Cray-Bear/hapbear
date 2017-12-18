package com.fty1.ippool.web;

import com.fty1.ippool.service.IpProductCrawerService;
import com.fty1.ippool.vo.IpProductCrawlerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/ip")
public class IpProductCrawlerController {


    @Autowired
    private IpProductCrawerService ipProductCrawerService;


    @GetMapping(path="/pro/{num}")
    @ResponseBody
    public List<IpProductCrawlerVO> ProIps(@PathVariable(name = "num") Integer num) {
        int pageNum =num -1;
        if(pageNum < 0){
            pageNum = 0;
        }
        return ipProductCrawerService.findAll(new PageRequest(pageNum,5));
    }
}
