import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private String name;
    private int group;
    private double rating;

    public  Student() {
        this.name = "Unknown student";
    }

    public Student(String name, int group, double rating) {
        this.name = name;
        this.group = group;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return group == student.group &&
                Double.compare(student.rating, rating) == 0 &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group, rating);
    }

    @Override
    public String toString() {
        return "{Name: " + name + ", group: " + group + ", rating: " + rating + "}\n";
    }

}
