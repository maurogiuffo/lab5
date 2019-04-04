import java.util.Optional;

public class Event {

    private Integer id;
    private String name;
    private Location location;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public Event() {

    }


    public Event(Integer id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }


    public static class Builder {
        private Integer id;
        private String name;
        private Location location;

        public Builder() {

        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder location(Location location) {
            this.location = location;
            return this;
        }

        public Event build() {
            Event c = new Event();
            c.id = this.id;
            c.name = this.name;
            c.location = this.location;
            return c;
        }
    }

    /*
    public String toString()
    {
        return this.name + " - " + this.location.getName() + " - " + this.location.getCity().getName();
    }*/

    public String toString(){
        return  this.name + " - " +
                 Optional.ofNullable(this.location).map(Location::getName).orElse("Undefined Location") + " - " +
                 Optional.ofNullable(this.location).map(Location::getCity).map(City::getName).orElse("Undefined City");
    }


}
