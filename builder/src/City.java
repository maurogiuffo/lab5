public class City {
    private Integer id;
    private String name;

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

    public City() {
    }

    /*
    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }*/

    public static class Builder {
        private Integer id;
        private String name;

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

        public City build() {
            City c = new City();
            c.id = this.id;
            c.name = this.name;
            return c;
        }
    }
}
