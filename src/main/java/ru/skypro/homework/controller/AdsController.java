package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;
import ru.skypro.homework.service.AdsService;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "Ads controller")
@ApiResponses(
        value = {
                @ApiResponse(responseCode = "401", content = @Content()),
                @ApiResponse(responseCode = "403", content = @Content())
        }
)
public class AdsController {

//    private final AdsService adsService;
//
//    public AdsController(AdsService adsService) {
//        this.adsService = adsService;
//    }
//
//
//    /**
//     * GET /ads/{id}/comments : getComments
//     *
//     * @param id (required)
//     * @return OK (status code 200)
//     * or Not Found (status code 404)
//     */
//    @Operation(
//            operationId = "getComments",
//            summary = "Получить комментарии объявления",
//            tags = {"Комментарии"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "OK", content = {
//                            @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperCommentDto.class))
//                    }),
//                    @ApiResponse(responseCode = "404", description = "NotFound")
//            }
//    )
//    @GetMapping("/{id}/comments")
//    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable("id") int id) {
//        ResponseWrapperCommentDto comments = new ResponseWrapperCommentDto(adsService.getAdsComments(id));
//        return ResponseEntity.ok(comments);
//    }
//
//    /**
//     * GET /ads
//     *
//     * @return OK (status code 200)
//     */
////    @Operation(
////            operationId = "getALLAds",
////            tags = {"Объявления"},
////            responses = {
////                    @ApiResponse(responseCode = "200", description = "OK", content = {
////                            @Content(mediaType = "*/*", schema = @Schema(implementation = AddDefaultCharsetFilter.ResponseWrapper.class))
////                    })
////            }
////    )
////    @GetMapping()
////    public ResponseEntity<ResponseWrapperAdsDto> getAllAds(@RequestParam(required = false) String title) {
////        ResponseWrapperAdsDto ads = new ResponseWrapperAdsDto(adsService.getAllAds(title));
////        return ResponseEntity.ok(ads);
////    }
//
//    /**
//     * POST /ads : addAds
//     *
//     * @param properties (required)
//     * @param image      (required)
//     * @return Created (status code 201)
//     * or Not Found (status code 404)
//     * or Forbidden (status code 403)
//     * or Unauthorized (status code 401)
//     */
//    @Operation(
//            operationId = "addAds",
//            summary = "addAds",
//            tags = {"Объявления"},
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Created", content = {
//                            @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))
//                    }),
//                    @ApiResponse(responseCode = "404", description = "Not Found"),
//                    @ApiResponse(responseCode = "403", description = "Forbidden"),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized")
//            }
//    )
//    @PostMapping()
//    public ResponseEntity<AdsDto> addAds(@NotNull Authentication authentication,
//                                         @RequestPart("properties") CreateAdsDto properties,
//                                         @RequestPart("image") MultipartFile image) throws IOException {
//        return ResponseEntity.ok(adsService.save(properties,authentication, image));
//    }
//
//    /**
//     * GET /ads/{id} : getFullAd
//     *
//     * @param id (required)
//     * @return OK (status code 200)
//     * or Not Found (status code 404)
//     */
//    @Operation(
//            operationId = "getAds",
//            summary = "getFullAd",
//            tags = {"Объявления"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "OK", content = {
//                            @Content(mediaType = "*/*", schema = @Schema(implementation = FullAdsDto.class))
//                    }),
//                    @ApiResponse(responseCode = "404", description = "Not Found")
//            }
//    )
//    @GetMapping("/{id}")
//    public ResponseEntity<FullAdsDto> getAds(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok(adsService.getFullAds(id));
//    }
//
//    /**
//     * DELETE /ads/{id} : removeAds
//     *
//     * @param id (required)
//     * @return No Content (status code 204)
//     * or Unauthorized (status code 401)
//     * or Forbidden (status code 403)
//     */
//    @Operation(
//            operationId = "removeAds",
//            summary = "removeAds",
//            tags = {"Объявления"},
//            responses = {
//                    @ApiResponse(responseCode = "204", description = "No Content"),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
//                    @ApiResponse(responseCode = "403", description = "Forbidden")
//            }
//    )
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> removeAd(Authentication authentication, @PathVariable("id") Integer id) {
//        adsService.removeAds(id, authentication);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
//
//    /**
//     * PATCH /ads/{id} : updateAds
//     *
//     * @param id        (required)
//     * @param createAds (required)
//     * @return OK (status code 200)
//     * or Not Found (status code 404)
//     * or Forbidden (status code 403)
//     * or Unauthorized (status code 401)
//     */
//    @Operation(
//            operationId = "updateAds",
//            summary = "updateAds",
//            tags = {"Объявления"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "OK", content = {
//                            @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))
//                    }),
//                    @ApiResponse(responseCode = "404", description = "Not Found"),
//                    @ApiResponse(responseCode = "403", description = "Forbidden"),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized")
//            }
//    )
//    @PatchMapping("/{id}")
//    public ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id,
//                                         @RequestBody CreateAdsDto createAds,
//                                            Authentication authentication) {
//        return ResponseEntity.ok(adsService.updateAds(id, createAds, authentication));
//    }
//
//    /**
//     * GET /ads/me : getAdsMe
//     *
//     * @return OK (status code 200)
//     * or Unauthorized (status code 401)
//     * or Forbidden (status code 403)
//     * or Not Found (status code 404)
//     */
//    @Operation(
//            operationId = "getAdsMe",
//            summary = "getAdsMe",
//            tags = {"Объявления"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "OK", content = {
//                            @Content(mediaType = "*/*", schema = @Schema(implementation = AddDefaultCharsetFilter.ResponseWrapper.class))
//                    }),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
//                    @ApiResponse(responseCode = "403", description = "Forbidden"),
//                    @ApiResponse(responseCode = "404", description = "Not Found")
//            }
//    )
////    @GetMapping("/me")
////    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(@NotNull Authentication authentication) {
////        ResponseWrapperAdsDto ads = new  ResponseWrapperAdsDto(adsService.getAdsByUser(authentication.getName()));
////        return ResponseEntity.ok(ads);
////    }
//
//    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<byte[]> updateAdsImage(@PathVariable Integer id, @RequestParam MultipartFile image){
//        return null;
//    }
//
//    /**
//     * POST /ads/{id}/comments : addComment
//     *
//     * @param adsId (required)
//     * @param comment (required)
//     * @return Ok (status code 200)
//     * or Not Found (status code 404)
//     * or Forbidden (status code 403)
//     * or Unauthorized (status code 401)
//     */
//    @Operation(
//            operationId = "addComment",
//            summary = "Добавить комментарий к объявлению",
//            tags = {"Комментарии"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "OK", content = {
//                            @Content(mediaType = "*/*", schema = @Schema(implementation = CommentDto.class))
//                    }),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
//                    @ApiResponse(responseCode = "403", description = "Forbidden"),
//                    @ApiResponse(responseCode = "404", description = "Not Found")
//            }
//    )
//    @PostMapping("/{adsId}/comments")
//    public ResponseEntity<CommentDto> addComment (@PathVariable Integer adsId, @RequestBody CommentDto comment,
//                                                  Authentication authentication) {
//        return ResponseEntity.ok(adsService.addComment(adsId, comment, authentication));
//    }
//
//    /**
//     * DELETE /ads/{adId}/comments/{commentId} : deleteComment
//     *
//     * @param adsId (required)
//     * @param commentId (required)
//     * @return Ok (status code 200)
//     * or Not Found (status code 404)
//     * or Forbidden (status code 403)
//     * or Unauthorized (status code 401)
//     */
//    @Operation(
//            operationId = "deleteComment",
//            summary = "Удалить комментарий к объявлению",
//            tags = {"Комментарии"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "OK"),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
//                    @ApiResponse(responseCode = "403", description = "Forbidden"),
//                    @ApiResponse(responseCode = "404", description = "Not Found")
//            }
//    )
//
//    @DeleteMapping("/{adsId}/comments/{commentId}")
//    public ResponseEntity<Void> deleteComment (@PathVariable("adsId") Integer adsId, @PathVariable("commentId") Integer commentId, Authentication authentication){
//       adsService.deleteComment(adsId, commentId, authentication);
//       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
//
//    /**
//     * PATCH /ads/{adId}/comments/{commentId} : updateComment
//     *
//     * @param adsId (required)
//     * @param commentId (required)
//     * @param comment (required)
//     * @return Ok (status code 200)
//     * or Not Found (status code 404)
//     * or Forbidden (status code 403)
//     * or Unauthorized (status code 401)
//     */
//    @Operation(
//            operationId = "updateComment",
//            summary = "Обновить комментарий к объявлению",
//            tags = {"Комментарии"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "OK", content = {
//                            @Content(mediaType = "*/*", schema = @Schema(implementation = CommentDto.class))
//                    }),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
//                    @ApiResponse(responseCode = "403", description = "Forbidden"),
//                    @ApiResponse(responseCode = "404", description = "Not Found")
//            }
//    )
//    @PatchMapping("/{adsId}/comments/{commentId}")
//    public ResponseEntity<CommentDto> updateComment (@PathVariable("adsId") Integer adsId, @PathVariable("commentId") Integer commentId, @RequestBody CommentDto comment, Authentication authentication) {
//        return ResponseEntity.ok(adsService.updateComment(adsId, commentId, comment, authentication));
//    }

}
