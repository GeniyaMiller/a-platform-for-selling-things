package ru.skypro.homework.controller;

=======
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.dto.ads.ResponseWrapperCommentDto;
import ru.skypro.homework.service.AdsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    /**
     * GET /ads/{id}/comments : getComments
     *
     * @param id (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getComments",
            summary = "Получить комментарии объявления",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperCommentDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "NotFound")
            }
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable("id") int id) {
        ResponseWrapperCommentDto comments = new ResponseWrapperCommentDto(adsService.getAdsComments(id));
        return ResponseEntity.ok(comments);
    }
  
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
