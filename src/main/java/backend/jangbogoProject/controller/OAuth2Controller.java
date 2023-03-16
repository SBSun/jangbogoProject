package backend.jangbogoProject.controller;

import backend.jangbogoProject.dto.DataResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class OAuth2Controller {
    private final RestTemplate restTemplate;

    @GetMapping(value = "/callback")
    public DataResponseDTO<String> callback(@RequestParam String code, @RequestParam String clientName) {
        log.info("Request param code = " + code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:8080/login/oauth2/code/" + clientName);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response
                = restTemplate.postForEntity("http://localhost:8080/login/oauth2/code/" + clientName, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            return DataResponseDTO.of("성공");
        }else {
            return DataResponseDTO.of("실패");
        }
    }
}
