import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ахметханов Ринат", 902, 78.25));
        students.add(new Student("Волкова Оливия", 902, 71.125));
        students.add(new Student("Газизова Милана", 902, 79));
        students.add(new Student("Ганиев Ришат", 902, 95));
        students.add(new Student("Гилемзянов Карим", 902, 80.375));
        students.add(new Student("Гилязов Азат", 902, 83.5));
        students.add(new Student("Давлетшин Дамир", 902, 81.625));
        students.add(new Student("Динер Александр", 902, 85));
        students.add(new Student("Дьяконов Алексей", 902, 84));
        students.add(new Student("Евсикова Ангелина", 902, 86));
        students.add(new Student("Закиер Данияр", 902, 83.875));
        students.add(new Student("Калимуллин Искандер", 902, 70.25));
        students.add(new Student("Меметова Эвелина", 902, 77.75));
        students.add(new Student("Муксинова Дана", 902, 80.375));
        students.add(new Student("Мухина Дарья", 902, 78.25));
        students.add(new Student("Насибуллин Эрик", 902, 75.25));
        students.add(new Student("Нигматьянов Рифдар", 902, 82.5));
        students.add(new Student("Садритдинов Урал", 902, 72));
        students.add(new Student("Свидиров Кирилл", 902, 90.625));
        students.add(new Student("Степанищева Анастасия", 902, 81.125));
        students.add(new Student("Трофимов Даниил", 902, 86.75));
        students.add(new Student("Тыщенко Леонид", 902, 86.75));
        students.add(new Student("Фахрутдинов Булат", 902, 84.875));
        students.add(new Student("Фёдорова Алина", 902, 77.375));
        StudentSerialization.write(students,"src\\test.txt");
        System.out.println(StudentSerialization.read("src\\test.txt").toString());
    }
}
