package cinema.model;

import lombok.*;

import java.util.Set;
import java.util.TreeSet;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
@ToString(of = {"title", "year"})
// @EqualsAndHashCode(of = {"title", "year"})
public class Movie {
    private String title;
    private int year;
    private Integer duration;
    private String synopsis;

    @Singular
    // @Builder.Default // si pas @Singular
    private Set<String> genres = new TreeSet<>();

}
