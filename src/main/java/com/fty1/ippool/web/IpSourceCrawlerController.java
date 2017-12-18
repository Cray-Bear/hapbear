package com.fty1.ippool.web;

import com.fty1.ippool.service.IpSourceCrawlerService;
import com.fty1.ippool.vo.IpSourceCrawlerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/ippool")
public class IpSourceCrawlerController {


    @Autowired
    private IpSourceCrawlerService ipSourceCrawlerService;

    @PostMapping(path="/ipcrawler")
    @ResponseBody
    public String gatherIpCrawler(@RequestBody IpSourceCrawlerVO ipSrcCrawlerVO) {
        ipSourceCrawlerService.gatherIpCrawler(ipSrcCrawlerVO.getIp(), ipSrcCrawlerVO.getPort());
        return "Saved";
    }
}
