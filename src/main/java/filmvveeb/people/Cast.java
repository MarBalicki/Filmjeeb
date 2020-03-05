package filmvveeb.people;

import java.util.List;

public class Cast {
    List<Actor> actors;

    public Cast(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return actors + ",";
    }
}
