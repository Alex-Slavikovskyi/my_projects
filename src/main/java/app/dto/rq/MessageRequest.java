package app.dto.rq;

import app.annotations.Marker;
import app.model.Chat;
import app.model.UserModel;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "Chat request")
public class MessageRequest {

  @JsonView({Marker.forNew.class, Marker.forExisted.class})
  @NotNull(message = "Chat id must be specified", groups = {Marker.forNew.class, Marker.forExisted.class})
  private Long chatId;

  @JsonView({Marker.forNew.class, Marker.forExisted.class})
  @NotNull(message = "Message author must be specified", groups = {Marker.forNew.class, Marker.forExisted.class})
  private Long userId;

  @JsonView({Marker.forNew.class, Marker.forExisted.class})
  @Size(min = 1, max = 4096, message = "Message body length must be in range 1..4096 characters", groups = {Marker.forNew.class, Marker.forExisted.class})
  private String body;

  private LocalDateTime sent = LocalDateTime.now();

}
