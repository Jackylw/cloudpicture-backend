package top.fexample.cloudpicturebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.fexample.cloudpicturebackend.model.entity.User;
import top.fexample.cloudpicturebackend.service.UserService;
import top.fexample.cloudpicturebackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 19164
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-02-01 21:37:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




