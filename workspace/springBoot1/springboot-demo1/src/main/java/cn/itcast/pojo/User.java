package cn.itcast.pojo;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix="user")
@Component
/*
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
*/
@Data
public class User {
    private String username;
    private int age;
    private List<String> girls;
}
