package backend.jangbogoProject.review;

import backend.jangbogoProject.review.entity.*;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {
    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    void createReview() throws Exception {
        // given
        ReviewRequestDTO.Create request = createRequest();
        ReviewResponseDTO.Info response = ReviewResponseDTO.Info.of(request.toEntity());

        doReturn(response).when(reviewService)
                .createReview(any(ReviewRequestDTO.Create.class));

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/review/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();

        response = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ReviewResponseDTO.Info.class);
        assertThat(response.getContent()).isEqualTo("test content");
    }
/*
    @Test
    void editReview() throws Exception{
        // given
        ReviewRequestDTO.Edit editRequest = editRequest();

        ReviewResponseDTO.Info info = ReviewResponseDTO.Info.builder()
                .review_id(1L)
                .content("test content")
                .build();

        doReturn(info).when(reviewService)
                .editReview(any(ReviewRequestDTO.Edit.class));


        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/review/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(editRequest))
        );

        // then
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();

        ReviewResponseDTO.Info response = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ReviewResponseDTO.Info.class);
        assertThat(response.getContent()).isEqualTo("edit content");
    }
*/
    private ReviewRequestDTO.Create createRequest(){
        return ReviewRequestDTO.Create.builder()
                .market_id(1L)
                .user_id(1L)
                .content("test content")
                .build();
    }

    private ReviewRequestDTO.Edit editRequest(){
        return new ReviewRequestDTO.Edit(1L, "edit content");
    }
}
