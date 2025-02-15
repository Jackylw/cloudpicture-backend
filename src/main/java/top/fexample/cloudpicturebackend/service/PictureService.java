package top.fexample.cloudpicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;
import top.fexample.cloudpicturebackend.model.dto.picture.PictureQueryRequest;
import top.fexample.cloudpicturebackend.model.dto.picture.PictureUploadRequest;
import top.fexample.cloudpicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import top.fexample.cloudpicturebackend.model.entity.User;
import top.fexample.cloudpicturebackend.model.vo.PictureVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 19164
 * @description 针对表【picture(图片)】的数据库操作Service
 * @createDate 2025-02-15 13:50:51
 */
public interface PictureService extends IService<Picture> {
    /**
     * 上传图片
     *
     * @param multipartFile       图片文件
     * @param pictureUploadRequest 图片上传请求
     * @param loginUser           登录用户
     * @return  图片信息
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 获取图片查询条件
     *
     * @param pictureQueryRequest 图片查询请求
     * @return 图片查询条件
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 获取图片信息
     *
     * @param picture 图片
     * @param request 请求
     * @return 图片信息
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 获取图片信息分页
     *
     * @param picturePage 分页信息
     * @param request 请求
     * @return 图片信息分页
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 有效性校验
     *
     * @param picture 图片
     */
    void validPicture(Picture picture);
}
