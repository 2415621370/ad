package com.itcanteen.search;

import com.itcanteen.common.annotation.IgnoreResponseAdvice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 14:18
 */
@Controller
public class temp {


    @Autowired
    SearchService searchService;


    @RequestMapping("/getList")
    @ResponseBody
    @IgnoreResponseAdvice
    public List getList(){
       return searchService.searchByKeyword("国庆");
    }
}
