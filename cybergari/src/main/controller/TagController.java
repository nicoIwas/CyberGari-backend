package main.controller;

import main.tag.vos.Tag;
import main.tag.TagService;
import main.tag.vos.FileTagUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class TagController {

    @Autowired
    private TagService service;
    @PutMapping("/tags/files/{userId}")
    public void updateFileTags(@RequestBody final List<FileTagUpdateVO> request, @PathVariable final String userId) {
        service.updateFileTags(request, userId);
    }

    @GetMapping("/tags/{userId}")
    public Set<Tag> findAll(@PathVariable final String userId){ return service.findAll(userId);}

    @DeleteMapping("/tags/{userId}/{tagName}")
    public void deleteTag(@PathVariable final String tagName,@PathVariable final String userId){ service.delete(tagName, userId);}

    @PostMapping("/tags/{userId}")
    public void saveTag(@RequestBody final Tag tag, @PathVariable final String userId){ service.save(tag, userId);}
}
