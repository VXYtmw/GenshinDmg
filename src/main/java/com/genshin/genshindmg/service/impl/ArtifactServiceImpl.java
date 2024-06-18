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
        if (inputVo.getSubStat().size() != 4) {
            log.info("圣遗物词条数目不符合要求");
            throw new ShSystemException(ResultEnum.ARTIFACT_INVALID);
        }
        if (!TableMappingUtil.hasSetString(inputVo.getSet())) {
            log.info("圣遗物套装不存在");
            throw new ShSystemException(ResultEnum.ARTIFACT_INVALID);
        }
        if (!TableMappingUtil.hasSlotString(inputVo.getSlot())) {
            log.info("圣遗物部位不存在");
            throw new ShSystemException(ResultEnum.ARTIFACT_INVALID);
        }
        if (!TableMappingUtil.hasStatString(inputVo.getMainStat().getName()) ||
                !TableMappingUtil.hasStatString(inputVo.getSubStat().get(0).getName()) ||
                !TableMappingUtil.hasStatString(inputVo.getSubStat().get(1).getName()) ||
                !TableMappingUtil.hasStatString(inputVo.getSubStat().get(2).getName()) ||
                !TableMappingUtil.hasStatString(inputVo.getSubStat().get(3).getName())) {
            log.info("圣遗物属性名错误");
            throw new ShSystemException(ResultEnum.ARTIFACT_INVALID);
        }

        // 保存圣遗物数据
        Artifact a = new Artifact();
        a.setUserId(user.getUserId());
        a.setSuit(TableMappingUtil.getSetInt(inputVo.getSet()));
        a.setPart(TableMappingUtil.getSlotInt(inputVo.getSlot()));
        a.setPrimaryAttribute(TableMappingUtil.getStatInt(inputVo.getMainStat().getName()));
        a.setSecondaryAttribute1(TableMappingUtil.getStatInt(inputVo.getSubStat().get(0).getName()));
        a.setSecondaryAttribute2(TableMappingUtil.getStatInt(inputVo.getSubStat().get(1).getName()));
        a.setSecondaryAttribute3(TableMappingUtil.getStatInt(inputVo.getSubStat().get(2).getName()));
        a.setSecondaryAttribute4(TableMappingUtil.getStatInt(inputVo.getSubStat().get(3).getName()));
        a.setPrimaryValue(inputVo.getMainStat().getValue());
        a.setSecondaryValue1(inputVo.getSubStat().get(0).getValue());
        a.setSecondaryValue2(inputVo.getSubStat().get(1).getValue());
        a.setSecondaryValue3(inputVo.getSubStat().get(2).getValue());
        a.setSecondaryValue4(inputVo.getSubStat().get(3).getValue());
        if (1 != artifactMapper.insert(a)) {
            throw new ShSystemException(ResultEnum.INSERT);
        }
        return true;
    }
}

