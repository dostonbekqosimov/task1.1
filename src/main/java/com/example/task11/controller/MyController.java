package com.example.task11.controller;


import com.example.task11.entity.*;
import com.example.task11.service.PageService;
import com.example.task11.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private PageService pageService;

    @Autowired
    private StoryService storyService;

    @Autowired
    private MessageSource messageSource;


    // GET ALL PAGES
    @GetMapping("/pages")
    public List<PageTranslation> getAllPages(@RequestParam(name = "lang", defaultValue = "uz") String language) {
        Locale locale = Locale.forLanguageTag(language);
        return pageService.getAllPages(locale);
    }


    // GET ALL STORIES
    @GetMapping("/stories")
    public List<StoryTranslation> getAllStories(@RequestParam(name = "lang", defaultValue = "uz") String language) {
        Locale locale = Locale.forLanguageTag(language);
        return storyService.getAllStories(locale);
    }



    // REACT TO A PAGE
    @PostMapping("pages/{pageId}/reaction")
    public void reactToPage(@PathVariable Long pageId, @RequestBody PageReactionDTO reaction,
                            @RequestParam(name = "lang", defaultValue = "uz") String language) {
        Locale locale = Locale.forLanguageTag(language);
        pageService.updatePageReaction(pageId, locale, reaction);
    }


}
