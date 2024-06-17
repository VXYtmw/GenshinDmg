package com.genshin.genshindmg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genshin.genshindmg.dto.vo.artifact.InputVo;
import com.genshin.genshindmg.entities.Artifact;
import com.genshin.genshindmg.entities.User;
import com.genshin.genshindmg.mapper.UserMapper;
import com.genshin.genshindmg.service.ArtifactService;
import com.genshin.genshindmg.mapper.ArtifactMapper;
import com.genshin.genshindmg.service.ex.ShSystemException;
import com.genshin.genshindmg.utils.ResultEnum;
import com.genshin.genshindmg.utils.TableMappingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author DELL
* @description 针对表【artifact】的数据库操作Service实现
* @createDate 2024-06-16 15:41:23
*/
@Service
@Slf4j
public class ArtifactServiceImpl extends ServiceImpl<ArtifactMapper, Artifact>
    implements ArtifactService{
    private final ArtifactMapper artifactMapper;

    private final UserMapper userMapper;

    @Autowired
    ArtifactServiceImpl(ArtifactMapper artifactMapper, UserMapper userMapper) {
        this.artifactMapper = artifactMapper;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public Boolean inputArtifact(InputVo inputVo) {
        // 获取用户ID，检查用户是否存在
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.eq(User::getUserName, inputVo.getUserName());
        User user = userMapper.selectOne(q);
        if (user == null) {
            // 用户不存在则抛出异常
            log.info("用户不存在");
            throw new ShSystemException(ResultEnum.USER_NOT_FOUND);
        }

        // 检查圣遗物数据是否符合要求
        if (inputVo.getAttributes().size() != 5) {
            log.info("圣遗物词条数目不符合要求");
            throw new ShSystemException(ResultEnum.ARTIFACT_INVALID);
        }
        if (!TableMappingUtil.hasSuitString(inputVo.getSuit())) {
            log.info("圣遗物套装不存在");
            throw new ShSystemException(ResultEnum.ARTIFACT_INVALID);
        }
        if (!TableMappingUtil.hasPartString(inputVo.getPart())) {
            log.info("圣遗物部位不存在");
            throw new ShSystemException(ResultEnum.ARTIFACT_INVALID);
        }
        // attribute检查

        // 保存圣遗物数据
        Artifact a = new Artifact();
        a.setUserId(user.getUserId());
        a.setSuit(TableMappingUtil.getSuitInt(inputVo.getSuit()));
        a.setPart(TableMappingUtil.getPartInt(inputVo.getPart()));
        a.setPrimaryAttribute(1);
        a.setSecondaryAttribute1(2);
        a.setSecondaryAttribute2(3);
        a.setSecondaryAttribute3(4);
        a.setSecondaryAttribute4(5);
        a.setPrimaryValue(inputVo.getAttributes().get(0).getValue());
        a.setSecondaryValue1(inputVo.getAttributes().get(1).getValue());
        a.setSecondaryValue2(inputVo.getAttributes().get(2).getValue());
        a.setSecondaryValue3(inputVo.getAttributes().get(3).getValue());
        a.setSecondaryValue4(inputVo.getAttributes().get(4).getValue());
        if (1 != artifactMapper.insert(a)) {
            throw new ShSystemException(ResultEnum.INSERT);
        }
        return true;
    }
}




