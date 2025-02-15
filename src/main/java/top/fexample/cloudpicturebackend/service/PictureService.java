package top.fexample.cloudpicturebackend.service;

import org.springframework.web.multipart.MultipartFile;
import top.fexample.cloudpicturebackend.model.dto.picture.PictureUploadRequest;
import top.fexample.cloudpicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import top.fexample.cloudpicturebackend.model.entity.User;
import top.fexample.cloudpicturebackend.model.vo.PictureVO;

/**
 * @author 19164
 * @description 针对表【picture(图片)】的数据库操作Service
 * @createDate 2025-02-15 13:50:51
 */
public interface PictureService extends IService<Picture> {
    /**
     * 上传图片
     *
     * @param multipartFile
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);
}
