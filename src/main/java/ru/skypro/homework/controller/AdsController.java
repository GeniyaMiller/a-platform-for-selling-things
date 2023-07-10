package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;
import ru.skypro.homework.service.impl.AdsService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;


    public AdsController(AdsService adsService) { this.adsService = adsService; }

    /**
     * GET /ads
     *
     * @return OK (status code 200)
     */
    @Operation(
            operationId = "getALLAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = AddDefaultCharsetFilter.ResponseWrapper.class))
                    })
            }
    )
    @GetMapping()
    public ResponseEntity<?> getAllAds() {
        return ResponseEntity.ok(adsService.getAll());
    }

    /**
     * POST /ads : addAds
     *
     * @param properties (required)
     * @param image      (required)
     * @return Created (status code 201)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "addAds",
            summary = "addAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@RequestPart("image") MultipartFile imageFile,
                                         @Valid
                                         @RequestPart("properties") CreateAdsDto createAds,
                                         Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adsService.create(imageFile, createAds, authentication));
    }

    /**
     * GET /ads/{id} : getFullAd
     *
     * @param id (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getAds",
            summary = "getFullAd",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = FullAdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(adsService.getById(id));
    }

    /**
     * DELETE /ads/{id} : removeAds
     *
     * @param id (required)
     * @return No Content (status code 204)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
            operationId = "removeAds",
            summary = "removeAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("@adsService.getById(#id).getEmail()" +
            "== authentication.principal.username or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> removeAd(@PathVariable Integer id) {
        adsService.remove(id);
        return ResponseEntity.ok().build();
    }
    @Operation(hidden = true)
    @GetMapping(value = "/search")
    public ResponseEntity<?> searchByTitle(@RequestParam(required = false) String title) {
        return ResponseEntity.ok(adsService.search(title));
    }

    /**
     * PATCH /ads/{id} : updateAds
     *
     * @param id        (required)
     * @param createAds (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "updateAds",
            summary = "updateAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PatchMapping("/{id}")
    @PreAuthorize("@adsService.getById(#id).getEmail()" +
            "== authentication.principal.username or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateAds(@PathVariable Integer id, @RequestBody CreateAdsDto createAds) {
        return ResponseEntity.ok(adsService.update(id, createAds));
    }


    /**
     * GET /ads/me : getAdsMe
     *
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getAdsMe",
            summary = "getAdsMe",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = AddDefaultCharsetFilter.ResponseWrapper.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @GetMapping("/me")
    public ResponseEntity<?> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adsService.getMeAll(authentication));
    }

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


    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("@adsService.getById(#id).getEmail()" +
            "== authentication.principal.username or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateImage(@PathVariable Integer id, @RequestParam("image") MultipartFile avatar) {
        return ResponseEntity.ok(adsService.updateImage(id, avatar));
    }





    @Operation(hidden = true)
    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] showImage(@PathVariable("id") Integer id) {
        return adsService.showImage(id);
    }


//    @GetMapping("/{id}/comments")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable("id") Integer id) {
//        ResponseWrapperCommentDto comments = new ResponseWrapperCommentDto(adsService.getAdsComments(id));
//        return ResponseEntity.ok(comments);
//    }



//    @GetMapping(value = "/{id}/getImage", produces = {MediaType.IMAGE_PNG_VALUE})
//    public ResponseEntity<byte[]> getImage(@PathVariable int id) throws IOException {
//        return ResponseEntity.ok(imageService.getImage(id));
//    }



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
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
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
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
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
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public ResponseEntity<CommentDto> updateComment (@PathVariable("adsId") Integer adsId, @PathVariable("commentId") Integer commentId, @RequestBody CommentDto comment, Authentication authentication) {
//        return ResponseEntity.ok(adsService.updateComment(adsId, commentId, comment, authentication));
//    }

}
