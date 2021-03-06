package com.atguigu.mapper;

import com.atguigu.pojo.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * UserMapper它的实现类是由Mybatis底层源码进行了实现(jdk动态代理)
 */
public interface UserMapper {
    /**
     * 根据id查询用户的信息
     * @param id
     * @return
     */
    User selectUserById(Integer id);

    /**
     * 查询全部
     * @return
     */
    List<User> queryUserAll();

    /**
     * 更新一个
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 删除一个
     * @param id
     */
    int deleteUserById(Integer id);

    /**
     * 添加一个
     * @param user
     * @return
     */
    int insertUser(User user);


    //查询数据
    //Map<k:某个字段,v:数据>
    @MapKey("sex")
    Map<Integer,User> queryUserMapById(Integer id);
    

}
