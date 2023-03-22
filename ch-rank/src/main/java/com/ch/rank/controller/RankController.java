package com.ch.rank.controller;

import com.ch.rank.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RankController {

    private final RankService rankService;

    @GetMapping("/search/rank")
    public ResponseEntity<?> getSearchRank() {

        return rankService.getSearchRank();
    }

}
