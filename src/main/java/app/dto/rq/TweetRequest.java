package app.dto.rq;


import app.annotations.Marker;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Set;

@Data
public class TweetRequest {
  @JsonView(Marker.Update.class)
  private Long id;

  @JsonView({Marker.Update.class, Marker.New.class, Marker.Retweet.class})
  private String body;

  @JsonView({Marker.Update.class, Marker.New.class, Marker.Retweet.class})
  private Set<String> attachmentsImages;

  @JsonView({Marker.Retweet.class})
  private Long parentTweetId;
}
