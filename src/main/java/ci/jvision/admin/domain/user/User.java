package ci.jvision.admin.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Company {

        SK("에스케이"),
        LG("엘지"),
        KT("케이티"),
        SAMSUNG("삼성"),
        APPLE("애플");

        private final String value;

        Company(String value)
        {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    @Builder
    public User(String name, String email,String picture,Role role)
    {
        this.name = name;
        this.email =email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture)
    {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey()
    {
        return  this.role.getKey();
    }
}
