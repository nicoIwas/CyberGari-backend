package com.cybergari.mvp.controller;

import com.cybergari.mvp.tag.TagService;
import com.cybergari.mvp.tag.vos.FileTagUpdateVO;
import com.cybergari.mvp.tag.vos.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class TagController {

    @Autowired
    private TagService service;
    @CrossOrigin
    @PutMapping("/tags/files/{userId}")
    public void updateFileTags(@RequestBody final List<FileTagUpdateVO> request, @PathVariable final String userId) {
        service.updateFileTags(request, userId);
    }

    @CrossOrigin
    @GetMapping("/tags/{userId}")
    public Set<Tag> findAll(@PathVariable final String userId){ return service.findAll(userId);}

    @CrossOrigin
    @DeleteMapping("/tags/{userId}/{tagName}")
    public void deleteTag(@PathVariable final String tagName,@PathVariable final String userId){ service.delete(tagName, userId);}

    @CrossOrigin
    @PostMapping("/tags/{userId}")
    public void saveTag(@RequestBody final Tag tag, @PathVariable final String userId){ service.save(tag, userId);}
}
