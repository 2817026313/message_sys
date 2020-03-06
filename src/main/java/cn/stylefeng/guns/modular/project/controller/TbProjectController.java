package cn.stylefeng.guns.modular.project.controller;


import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.project.entity.TbProject;
import cn.stylefeng.guns.modular.project.service.TbProjectService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
    
    @Resource
    private TbProjectService projectService;

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
    public String projectUpdate(@RequestParam("projectId") String projectId) {

        if (ToolUtil.isEmpty(projectId)) {
            throw new RequestEmptyException();
        }

        //缓存项目修改前详细信息
        TbProject tbProject = projectService.getById(projectId);
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
    @ResponseBody
    public ResponseData add(TbProject project) {
        this.projectService.save(project);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有项目列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "projectName", required = false) String projectName) {
        Page page = LayuiPageFactory.defaultPage();
        QueryWrapper<TbProject> wrapper = new QueryWrapper<>();
        if (ToolUtil.isNotEmpty(projectName)) {
            wrapper.like("name", projectName);
        }
        IPage page1 = projectService.page(page, wrapper);
        return LayuiPageFactory.createPageInfo(page1);
    }

    /**
     * 项目详情
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/detail/{projectId}")
    @ResponseBody
    public Object detail(@PathVariable("projectId") String projectId) {
        TbProject tbProject = projectService.getById(projectId);
        return tbProject;
    }

    /**
     * 修改项目
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/update")
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
    @ResponseBody
    public ResponseData delete(@RequestParam String projectId) {
        TbProject tbProject = projectService.getById(projectId);
        tbProject.setIsDel(1);
        projectService.updateById(tbProject);
        return SUCCESS_TIP;
    }
}

