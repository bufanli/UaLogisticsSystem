package com.rt.modules.common.web;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.common.collect.Sets;
import com.rt.common.Constant;
import com.rt.common.base.controller.BaseController;
import com.rt.common.page.R;
import com.rt.common.utils.io.FileUtil;
import com.rt.config.shiro.ShiroAuthenticationManager;
import com.rt.modules.sys.entity.Role;
import com.rt.modules.sys.entity.User;
import com.rt.modules.sys.service.ResourceService;
import com.rt.modules.sys.service.RoleService;
import com.rt.modules.sys.vo.MenuVO;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@Controller
public class CommonController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Value("${spring.servlet.multipart.location}")
    private String mImagesPath;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleService roleService;


    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @Autowired
    private Producer producer;

    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroAuthenticationManager.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
    }




    @RequestMapping(value = "/admin/common/main", method = RequestMethod.GET)
    public String main() {
        return "/admin/common/main";
    }


    @RequestMapping("/admin/common/unauthorized")
    public String unauthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            response.addHeader("loginStatus", "unauthorized");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        return "/admin/common/unauthorized";
    }



    /**
     * 获取登录的用户信息
     */
    @GetMapping("/admin/common/userinfo")
    @ResponseBody
    public R info() {
        return R.ok().put("user", ShiroAuthenticationManager.getUserEntity());
    }


    @PostMapping("/admin/common/nav")
    @ResponseBody
    public R nav() {
        User user = ShiroAuthenticationManager.getUserEntity();
        List<Role> roles = roleService.listByUserId(user.getId());
        Set<MenuVO> resourceSet = Sets.newTreeSet();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                List<MenuVO> menuVOList = resourceService.listMenu(role.getId());
                for (MenuVO resource : menuVOList) {
                    if (resource.getType() < Constant.MenuType.BUTTON.getValue()) {
                        resourceSet.add(resource);
                    }
                }
            }
        }
        return R.ok().put("menuList", resourceSet);
    }

    @PostMapping("/admin/common/upload")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile uploadfile) {
        logger.debug(mImagesPath);

        if (uploadfile.isEmpty()) {
            return R.error("please select a file!");
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = uploadfile.getBytes();
            String filePath = DateTime.now().toString("yyyy/MM/dd/");
            String fileName = DateTime.now().toString("HHmmssms")+"."+FileUtil.getFileExtension(uploadfile.getOriginalFilename());
            Path path = Paths.get(mImagesPath + filePath+fileName);
            FileUtil.makesureDirExists(mImagesPath + filePath);
            Files.write(path, bytes);
            return R.ok().withResult(filePath+fileName);

        } catch (IOException e) {
            return R.error();
        }
    }


    @GetMapping("/admin/common/download")
    public String downLoad(HttpServletResponse response) {
        String filename = "2.jpg";
        File file = new File(mImagesPath + "/" + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
