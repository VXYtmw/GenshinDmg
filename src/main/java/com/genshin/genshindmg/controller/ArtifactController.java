package com.genshin.genshindmg.controller;

import com.genshin.genshindmg.dto.vo.artifact.InputVo;
import com.genshin.genshindmg.service.ArtifactService;
import com.genshin.genshindmg.utils.JsonResult;
import com.genshin.genshindmg.utils.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artifact")
@Slf4j
public class ArtifactController extends BaseController {

    private final ArtifactService artifactService;

    @Autowired
    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    // 圣遗物输入
    @PostMapping("/input")
    public JsonResult<Boolean> inputArtifact(@RequestBody InputVo inputVo) {
        try {
            Boolean res = artifactService.inputArtifact(inputVo);
            return new JsonResult<>(ResultEnum.OK, res);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e);
            return handleException(e);
        }
    }


}
