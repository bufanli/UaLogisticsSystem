package com.rt.modules.sys.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rt.common.base.controller.BaseController;
import com.rt.common.page.R;
import com.rt.modules.sys.entity.Role;
import com.rt.modules.sys.entity.User;
import com.rt.modules.sys.service.RoleService;
import com.rt.modules.sys.service.UserService;
import com.rt.config.shiro.ShiroAuthenticationManager;
import com.rt.modules.sys.dto.query.PageQueryDTO;
import com.rt.modules.sys.dto.query.UserQueryVO;
import com.rt.modules.sys.vo.UserVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/sys/user")
public class UserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static String PREFIX = "admin/sys/user/";
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;


    /**
     * 管理员列表页面
     * @return
     */
    @RequiresPermissions("system:user:list")
    @GetMapping(value = { "/", "/index" })
    public String index() {
        return PREFIX+"index";
    }

    /**
     * 管理员ajax列表
     * @param pageQueryDTO
     * @return
     */
    @RequiresPermissions("system:user:list")
    @PostMapping("list")
    @ResponseBody
    public R list(@RequestBody PageQueryDTO<UserQueryVO> pageQueryDTO) {
        UserQueryVO userQueryVO = pageQueryDTO.getCondition();
        IPage<User> pageData = userService.page(pageQueryDTO.getPageNumber(), pageQueryDTO.getPageSize(),userQueryVO);
        return R.ok().page(pageData);
    }

    /**
     * 跳转到查看管理员添加的页面
     */
    @GetMapping("/add")
    public String add(Model model) {
        List<Role> roles = roleService.listAll();
        model.addAttribute("roles",roles);
        return PREFIX + "add";
    }

    /**
     * 管理员保存
     * @param userVO
     * @return
     */
    @RequiresPermissions("system:user:add")
    @PostMapping("/add")
    @ResponseBody
    public R add(@Valid UserVO userVO){
        userService.saveOrUpdate(userVO);
        return R.ok();
    }

    @RequiresPermissions("system:user:update")
    @GetMapping("/update")
    public String update(@RequestParam Long id,Model model) {
        User user = userService.getById(id);
        List<Role> selected = roleService.listByUserId(id);
        List<Role> roles = roleService.listAll();
        model.addAttribute("roles",roles);
        model.addAttribute("user",user);
        model.addAttribute("selected",selected);
        return PREFIX + "update";
    }

    @RequiresPermissions("system:user:update")
    @PostMapping("/update")
    @ResponseBody
    public R update(@Valid UserVO userVO){
        userService.saveOrUpdate(userVO);
        ShiroAuthenticationManager.clearUserAuthByUserId(userVO.getId());
        return R.ok();
    }

    @RequiresPermissions("system:user:delete")
	@PostMapping("/delete")
    @ResponseBody
	public R delete(@RequestParam Long id) {
        User currentUser = ShiroAuthenticationManager.getUserEntity();
        if (currentUser.getId().intValue() == id) {
            return R.error("当前用户不能自我删除");
        }
        userService.removeById(id);
		return R.ok();
	}
}
