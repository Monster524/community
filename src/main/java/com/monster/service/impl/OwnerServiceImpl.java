package com.monster.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.entity.Owner;
import com.monster.mapper.OwnerMapper;
import com.monster.service.OwnerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements OwnerService {

    @Autowired
    OwnerMapper ownerMapper;

    @Override
    public List<Owner> list(Integer id){
        return  ownerMapper.list(id);
    }
    @Override
    public List<Owner> selectByMap(Map<String, Object> map) {
        return  ownerMapper.selectByMap(map);
    }

    @Override
    public Owner getById(Integer id) {
        return ownerMapper.getById(id);
    }

    @Override
    public boolean updatePassword(Owner owner) {
        /*
        包含姓名、身份证号和密码
        根据身份证号查找姓名
         */
        String id = owner.getOwnerId();
        Owner owner1 = ownerMapper.selectOne(new QueryWrapper<Owner>().eq("owner_id",id));
        if(owner1!=null&&owner1.getOwnerName().equals(owner.getOwnerName())){
            ownerMapper.updatePassword(owner);
            return true;
        }
        return false;
    }
}
