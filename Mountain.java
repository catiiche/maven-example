package jpa.entity.coursework4;

import javax.persistence.*;

@Entity
// 3. Getting a list of Mountains by country's name
@NamedQueries({
        @NamedQuery(name = "Mountain.getMountainByCountryName",
                query = "SELECT a FROM Mountain a WHERE a.country = :country"),
})
public class Mountain extends AutoPrimaryKey {
    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String country;

    @Column(nullable = false, length = 5)
    private int height;

    public Mountain(String name, String country, int height) {
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    public Mountain() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 4)
            throw new IllegalArgumentException("name должен быть не меньше 4");
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.length() < 4)
            throw new IllegalArgumentException("country должен быть не меньше 4");
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 100)
            throw new IllegalArgumentException("height не должен быть меньше 100");
        this.height = height;
    }
}
