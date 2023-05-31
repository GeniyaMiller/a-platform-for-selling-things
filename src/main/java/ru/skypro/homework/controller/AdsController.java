package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateAds;
import ru.skypro.homework.dto.ads.FullAds;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;

@Slf4j
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@CrossOrigin(value = {"http://localhost:3000", "http://localhost:3000"})
public class AdsController {
    @GetMapping()
    public ResponseEntity<ResponseWrapperAds> getAllAds(@RequestParam(required = false) String title) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<Ads> addAds(@RequestPart("properties") CreateAds properties,
                                      @RequestPart("image") MultipartFile image){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable("id") Integer id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable("id") Integer id) {
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable("id") Integer id,
                                         @RequestBody CreateAds createAds) {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe() {
        return null;
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable Integer id, @RequestParam MultipartFile image){
        return null;
    }
}
