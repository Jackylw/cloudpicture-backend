package top.fexample.cloudpicturebackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用删除请求
 */
@Data
public class DeleteRequest implements Serializable {

    private static final long serialVersionUID = -701600594509035608L;

    /**
     * id
     */
    private Long id;
}