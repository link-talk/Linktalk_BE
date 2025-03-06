package app.linktalk.backend.controller;

import app.linktalk.backend.service.InstaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;


@RestController
@RequestMapping("/api/insta")
public class InstaController {

    private final InstaService instaService;

    @Autowired
    public InstaController(InstaService instaService) {
        this.instaService = instaService;
    }

    @PostMapping("/comment")
    public ResponseEntity<String> postComment(@RequestParam String postUrl, @RequestParam String commentText) {
        String result = instaService.postComment(postUrl, commentText);
        return ResponseEntity.ok(result);
    }
}
