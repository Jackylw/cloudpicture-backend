package top.fexample.cloudpicturebackend.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class PictureTagCategory {

    /**
     * 标签分类
     */
    private List<String> tagList;

    /**
     * 分类列表
     */
    private List<String> categoryList;
}
