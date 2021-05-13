package com.monster.service.impl;

import com.monster.entity.Pay;
import com.monster.mapper.PayMapper;
import com.monster.service.PayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService {

}
