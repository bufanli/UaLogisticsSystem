package com.rt.modules.sys.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rt.common.base.controller.BaseController;
import com.rt.common.page.R;
import com.rt.common.utils.collection.CollectionUtil;
import com.rt.modules.sys.entity.Resource;
import com.rt.modules.sys.entity.Role;
import com.rt.modules.sys.entity.UserRole;
import com.rt.modules.sys.service.ResourceService;
import com.rt.modules.sys.service.RoleService;
import com.rt.modules.sys.service.UserRoleService;
import com.rt.config.shiro.ShiroAuthenticationManager;
import com.rt.modules.sys.dto.query.PageQueryDTO;
import com.rt.modules.sys.dto.query.RoleQueryVO;
import com.rt.modules.sys.vo.RoleVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sys/role")
public class RoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    private static String PREFIX = "admin/sys/role/";

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;

    @Autowired
    private UserRoleService userRoleService;

    @RequiresPermissions("system:role:list")
    @GetMapping(value = { "/", "/index" })
    public String index() {
        return PREFIX+"index";
    }

    @RequiresPermissions("system:role:list")
	@PostMapping("/list")
    @ResponseBody
    public R list(@RequestBody PageQueryDTO<RoleQueryVO> pageQueryDTO) {
        IPage<Role> pageData = roleService.page(pageQueryDTO.getPageNumber(), pageQueryDTO.getPageSize(), pageQueryDTO.getCondition());
        return R.ok().page(pageData);
    }


    @RequiresPermissions("system:role:add")
    @GetMapping("/add")
    public String add(Model model) {
        List<Resource> roleTree = resourceService.listAll();
        model.addAttribute("roleTree",JSON.toJSON(roleTree).toString());
        return PREFIX + "add";
    }

    @RequiresPermissions("system:role:add")
    @PostMapping("/add")
    @ResponseBody
    public R add( RoleVO roleVO){
        roleService.save(roleVO);
        return R.ok();
    }



    @RequiresPermissions("system:role:update")
	@GetMapping("/update")
	public String update(@RequestParam Long id, Model model) {
		Role role = roleService.getById(id);
        List<Resource> roleTree = resourceService.listAll();
        List<Resource> selected = resourceService.listByRoleId(id);
        model.addAttribute("roleTree", JSON.toJSON(roleTree).toString());
        model.addAttribute("selected", JSON.toJSON(selected).toString());
        model.addAttribute("role",role);
        return PREFIX + "update";
	}

    @RequiresPermissions("system:role:update")
	@PostMapping("/update")
    @ResponseBody
	public R update(RoleVO roleVO){
		roleService.update(roleVO);
        ShiroAuthenticationManager.clearUserAuthByUserId(userRoleService.listUserIdsByRoleId(roleVO.getId()));
		return R.ok();
	}

    @RequiresPermissions("system:role:delete")
    @PostMapping("/delete")
    @ResponseBody
    public R delete(@RequestParam Long id) {
        List<UserRole> userRoles = userRoleService.listUserRoleByRoleId(id);
        if(CollectionUtil.isNotEmpty(userRoles)){
            return R.error("请先清除关联用户角色!");
        }
        roleService.removeById(id);
        ShiroAuthenticationManager.clearUserAuthByUserId(userRoleService.listUserIdsByRoleId(id));
        return R.ok();
    }

}
