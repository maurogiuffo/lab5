public class Location {
    private Integer id;
    private String name;


    private City city;


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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }



    public Location() {
    }

    public Location(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private City city;

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
        public Builder city(City city) {
            this.city = city;
            return this;
        }


        public Location build() {
            Location c = new Location();
            c.id = this.id;
            c.name = this.name;
            c.city = this.city;
            return c;
        }
    }
}
