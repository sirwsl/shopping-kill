package com.wsl.shoppingkill.controller.api;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.bo.ExposerBO;
import com.wsl.shoppingkill.service.GetUrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 秒杀地址获取Controller
 *
 * @author : WangShiLei
 * @date : 2020/12/31 11:08 上午
 **/

@RestController
@RequestMapping("/api")
public class GetUrlApi {

    @Resource
    private GetUrlService getUrlService;

    @GetMapping("/{id}/getUrl")
    public Result<ExposerBO> getUrl(@PathVariable("id") Long id) {
        if (id == null || id < 1) {
            return Result.error();
        }
        ExposerBO url = getUrlService.getUrl(id);
        if (Objects.nonNull(url)) {
            return Result.success(url);
        }
        return Result.error();
    }

}
