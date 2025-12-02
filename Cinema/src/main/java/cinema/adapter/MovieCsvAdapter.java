package cinema.adapter;

import cinema.model.Movie;

public class MovieCsvAdapter {
    public static Movie movieFromCsvLine(String[] line){
        //id	title	year	duration	synopsis	poster_uri
        String title = line[1];
        String year = line[2];
        String duration = line[3];
        String synopsis = line[4];

        //Movie movie =
        return Movie.builder()
                .title(title)
                .year(Integer.parseInt(year))
                .duration(duration.isEmpty() ? null : Integer.parseInt(duration))
                .synopsis(synopsis)
                .build();

        //return movie;
    }
}