package top.fexample.cloudpicturebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.fexample.cloudpicturebackend.annotation.AuthCheck;
import top.fexample.cloudpicturebackend.common.BaseResponse;
import top.fexample.cloudpicturebackend.common.ResultUtils;
import top.fexample.cloudpicturebackend.constant.UserConstant;
import top.fexample.cloudpicturebackend.model.dto.picture.PictureUploadRequest;
import top.fexample.cloudpicturebackend.model.entity.User;
import top.fexample.cloudpicturebackend.model.vo.PictureVO;
import top.fexample.cloudpicturebackend.service.PictureService;
import top.fexample.cloudpicturebackend.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    /**
     * 上传图片（可重新上传）
     */
    @PostMapping("/upload")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<PictureVO> uploadPicture(
            @RequestPart("file") MultipartFile multipartFile,
            PictureUploadRequest pictureUploadRequest,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        PictureVO pictureVO = pictureService.uploadPicture(multipartFile, pictureUploadRequest, loginUser);
        return ResultUtils.success(pictureVO);
    }
}
