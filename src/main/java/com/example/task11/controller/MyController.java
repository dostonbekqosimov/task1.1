package com.example.task11.controller;


import com.example.task11.entity.Page;
import com.example.task11.entity.Reaction;
import com.example.task11.entity.Story;
import com.example.task11.service.PageService;
import com.example.task11.service.ReactionService;
import com.example.task11.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private PageService pageService;

    @Autowired
    private StoryService storyService;

    @Autowired
    private ReactionService reactionService;

    @GetMapping("/pages")
    public List<Page> getAllPages(){
        return pageService.getAllPages();
    }

    @GetMapping("/stories")
    public List<Story> getAllStories(){
        return storyService.getAllStories();
    }

    @PostMapping("pages/{pageId}/reaction")
    public void reactToPage(@PathVariable Long pageId, @RequestBody Reaction reaction){
        reactionService.reactToPage(pageId,reaction);
    }

}
