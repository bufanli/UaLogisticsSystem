package com.rt.modules.uaslogistics.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rt.common.page.R;
import com.rt.modules.sys.dto.query.PageQueryDTO;
import com.rt.modules.uaslogistics.entity.Uav;
import com.rt.modules.uaslogistics.entity.dto.UavDTO;
import com.rt.modules.uaslogistics.entity.dto.query.UavQueryDTO;
import com.rt.modules.uaslogistics.service.IUavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 *  无人机相关
 *
 * @since 2020-05-12
 */
@Controller
@RequestMapping("/admin/uav")
public class UavController {

    private static String PREFIX = "/admin/uav/";

    @Autowired
    private IUavService uavService;

    /**
     * 跳转至无人机列表页面
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return PREFIX + "list";
    }

    /**
     * 无人机列表
     * @param pageQueryDTO
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public R list(@RequestBody PageQueryDTO<UavQueryDTO> pageQueryDTO) {
        IPage<Uav> pageData = uavService.page(pageQueryDTO.getPageNumber(), pageQueryDTO.getPageSize(), pageQueryDTO.getCondition());
        return R.ok().page(pageData);
    }

    /**
     * 跳转无人机新增页面
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return PREFIX + "add";
    }

    /**
     * 新增停车场
     * @param uavDTO
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public R add(@Valid UavDTO uavDTO) {
        boolean flag = true;
        try {
            flag = uavService.saveUav(uavDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag ? R.ok() : R.error();
    }

    /**
     * 使无人机有效
     * @return
     */
    @RequestMapping(value = "/enableUav/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R enableUav(@PathVariable Long id) {
        Uav uav = new Uav();
        uav.setId(id);
        uav.setIsValid(1);
        uavService.updateById(uav);
        return R.ok();
    }

    /**
     * 使无人机无效
     * @return
     */
    @RequestMapping(value = "/disableUav/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R disableUav(@PathVariable Long id) {
        Uav uav = new Uav();
        uav.setId(id);
        uav.setIsValid(0);
        uavService.updateById(uav);
        return R.ok();
    }
}





















