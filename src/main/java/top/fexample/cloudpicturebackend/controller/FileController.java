package top.fexample.cloudpicturebackend.controller;

import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.utils.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.fexample.cloudpicturebackend.annotation.AuthCheck;
import top.fexample.cloudpicturebackend.common.BaseResponse;
import top.fexample.cloudpicturebackend.common.ResultUtils;
import top.fexample.cloudpicturebackend.constant.UserConstant;
import top.fexample.cloudpicturebackend.exception.BusinessException;
import top.fexample.cloudpicturebackend.exception.ErrorCode;
import top.fexample.cloudpicturebackend.manager.CosManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private CosManager cosManager;

    /**
     * 测试上传文件
     *
     * @param multipartFile 文件
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/test/upload")
    public BaseResponse<String> testUploadFile(@RequestPart("file") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = String.format("/test/%s", fileName);
        File file = null;
        try {
            file = File.createTempFile("temp", null);
            multipartFile.transferTo(file);
            cosManager.putObject(filePath, file);
            // 返回可访问的地址
            return ResultUtils.success(filePath);
        } catch (Exception e) {
            log.error("上传文件失败" + filePath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传文件失败");
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("删除临时文件失败" + filePath);
                }
            }
        }

    }

    /**
     * 测试文件下载
     *
     * @param filepath 文件路径
     * @param response 响应对象
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/test/download/")
    public void testDownloadFile(String filepath, HttpServletResponse response) throws IOException {
        COSObjectInputStream cosObjectInput = null;
        try {
            COSObject cosObject = cosManager.getObject(filepath);
            cosObjectInput = cosObject.getObjectContent();
            // 处理下载到的流
            byte[] bytes = IOUtils.toByteArray(cosObjectInput);
            // 设置响应头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filepath);
            // 写入响应
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("file download error, filepath = " + filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "下载失败");
        } finally {
            // 关闭流
            if (cosObjectInput != null) {
                cosObjectInput.close();
            }
        }
    }
}
