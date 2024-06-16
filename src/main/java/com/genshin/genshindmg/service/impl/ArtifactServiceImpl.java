package com.genshin.genshindmg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genshin.genshindmg.entities.Artifact;
import com.genshin.genshindmg.service.ArtifactService;
import com.genshin.genshindmg.mapper.ArtifactMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【artifact】的数据库操作Service实现
* @createDate 2024-06-16 15:41:23
*/
@Service
public class ArtifactServiceImpl extends ServiceImpl<ArtifactMapper, Artifact>
    implements ArtifactService{

}




