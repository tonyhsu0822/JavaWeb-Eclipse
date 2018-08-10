package example.shawn.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Message {
	private String username;
    private Long millis;
    private String content;
    
    public Message(String username, Long millis, String content) {
        this.username = username;
        this.millis = millis;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public Long getMillis() {
        return millis;
    }

    public String getContent() {
        return content;
    }
    
    public LocalDateTime getLocalDateTime() {
        return Instant.ofEpochMilli(millis)
                      .atZone(ZoneId.of("Asia/Taipei"))
                      .toLocalDateTime();
    }
}
