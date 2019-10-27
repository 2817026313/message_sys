package cn.stylefeng.guns.modular.project.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.project.entity.TbProject;
import cn.stylefeng.guns.modular.project.service.ITbProjectService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 项目表
 * @since 2019-10-27
 */
@Controller
@RequestMapping("/tbProject")
public class TbProjectController extends BaseController {

    private String PREFIX = "/modular/project/project/";
    
    @Autowired
    private ITbProjectService projectService;

    /**
     * 跳转到项目管理首页
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "project.html";
    }

    /**
     * 跳转到添加项目
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/project_add")
    public String projectAdd() {
        return PREFIX + "project_add.html";
    }

    /**
     * 跳转到修改项目
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @Permission
    @RequestMapping("/project_update")
    public String projectUpdate(@RequestParam("projectId") Long projectId) {

        if (ToolUtil.isEmpty(projectId)) {
            throw new RequestEmptyException();
        }

        //缓存项目修改前详细信息
        TbProject tbProject = projectService.selectById(projectId);
        LogObjectHolder.me().set(tbProject);

        return PREFIX + "project_edit.html";
    }

    /**
     * 新增项目
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public ResponseData add(TbProject project) {
        ShiroUser user = ShiroKit.getUser();
        this.projectService.insert(project);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有项目列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(TbProject project) {
        EntityWrapper<TbProject> wrapper = new EntityWrapper<>();
        return projectService.selectList(wrapper);
    }

    /**
     * 项目详情
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/detail/{projectId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("projectId") Long projectId) {
        TbProject tbProject = projectService.selectById(projectId);
        return tbProject;
    }

    /**
     * 修改项目
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public ResponseData update(TbProject project) {
        projectService.updateById(project);
        return SUCCESS_TIP;
    }

    /**
     * 删除项目
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam Long projectId) {
        TbProject tbProject = projectService.selectById(projectId);
        tbProject.setIsDel(1);
        projectService.updateById(tbProject);
        return SUCCESS_TIP;
    }
}

