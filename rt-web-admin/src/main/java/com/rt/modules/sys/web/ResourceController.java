package com.rt.modules.sys.web;

import com.alibaba.fastjson.JSON;
import com.rt.common.log.annotion.SysLog;
import com.rt.common.base.controller.BaseController;
import com.rt.common.page.R;
import com.rt.modules.sys.entity.Resource;
import com.rt.modules.sys.service.ResourceService;
import com.rt.modules.sys.vo.MenuVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ResourceController
 * @Description: 资源管理
 * @author: <a href="edeis@163.com">edeis</a>
 * @version: V1.0.0
 * @date: 2017-12-20
 */
@Controller
@RequestMapping("/admin/sys/resource")
public class ResourceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);
    private static String PREFIX = "admin/sys/resource/";
    @Autowired
    private ResourceService resourceService;


    @RequiresPermissions("system:resource:list")
    @RequestMapping("/index")
    public String index() {
        return PREFIX + "index";
    }


    @RequiresPermissions("system:resource:list")
    @GetMapping("/list")
    @ResponseBody
    public List<Resource> list() {
        List<Resource> list = resourceService.listAll();
        return list;
    }


    @RequiresPermissions("system:resource:add")
    @GetMapping("/add")
    public String add(Model model) {
        List<MenuVO> roleTree =  this.buildWithRoot(resourceService.listAllMenu());
        model.addAttribute("roleTree",JSON.toJSONString(roleTree));
        return PREFIX + "add";
    }

    @SysLog(value = "保存资源")
    @RequiresPermissions("system:resource:add")
    @PostMapping("/add")
    @ResponseBody
    public R add(Resource resource) {
        resourceService.save(resource);
        return R.ok();
    }

    @RequiresPermissions("system:resource:update")
    @GetMapping("/update")
    public String update(@RequestParam Long id, Model model) {
        List<MenuVO> roleTree =  this.buildWithRoot(resourceService.listAllMenu());
        Resource resource = resourceService.getById(id);
        model.addAttribute("roleTree",JSON.toJSONString(roleTree));
        model.addAttribute("resource",resource);
        return PREFIX + "update";
    }

    @SysLog(value = "修改资源")
    @RequiresPermissions("system:resource:update")
    @PostMapping("/update")
    @ResponseBody
    public R update(Resource resource) {
        resourceService.updateById(resource);
        return R.ok();
    }

    @RequiresPermissions("system:resource:delete")
    @PostMapping("/delete")
    @ResponseBody
    public R delete(@RequestParam Long id) {
        //判断是否有子菜单或按钮
        List<Resource> menuList = resourceService.listParentId(id);
        if (menuList.size() > 0) {
            return R.error("Please delete the sub Menu or button firstly");
        }
        resourceService.removeById(id);
        return R.ok();
    }


    private List<MenuVO> buildWithRoot( List<MenuVO> roleTree){
        //添加顶级菜单
        MenuVO root = new MenuVO();
        root.setId(0L);
        root.setName("TopMenu");
        root.setParentId(-1L);
        roleTree.add(root);
        return roleTree;
    }

}
