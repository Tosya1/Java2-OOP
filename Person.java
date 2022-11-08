import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Person implements Printable, Writable {
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private Integer age;
    private Integer id;
    private Boolean isParent;
    private Boolean isMarried;
    private Boolean isMan;
    private Person spouse;
    private Set<Person> children = new HashSet<>();
    private Set<Person> parents = new HashSet<>();

    public Person(String name, String lastName, Integer id, String birthDate, Boolean isMan) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = formatBirthDate(birthDate);
        this.id = id;
        this.age = calculateAge();
        this.isMan = isMan;
        this.isParent = false;
        this.isMarried = false;
    }

    private LocalDate formatBirthDate(String date) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate bd = LocalDate.parse(date, dtf);
            return bd;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    private Integer calculateAge() {
        try {
            return LocalDate.now().compareTo(this.birthDate);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setIsMarried(Person spouse) {
        this.isMarried = true;
        this.spouse = spouse;
        if (this.spouse != null) {
            this.spouse.spouse = this;
            this.spouse.isMarried = true;
        }
    }

    public Person getSpouse() {
        return spouse;
    }

    public Boolean getIsMarried() {
        return isMarried;
    }

    public void addChild(Person child, Person parent2) {
        this.isParent = true;
        children.add(child);
        child.parents.add(this);
        if (parent2 != null) {
            parent2.isParent = true;
            child.parents.add(parent2);
            parent2.addChild(child, null);
        }
    }

    public Set<Person> getChildren() {
        return children;
    }

    public Set<Person> getParents() {
        return parents;
    }

    public Boolean getIsMan() {
        return isMan;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return name + " " + lastName + ", " + age + " лет, дата рождения: " + birthDate.format(dtf);
    }

    @Override
    public void printInfo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println(name + " " + lastName + ":\nвозраст: " + age + " лет,\nдата рождения: " + birthDate.format(dtf) + ",");
        if (isMan) {
            System.out.println("пол: мужской,");
        } else {
            System.out.println("пол: женский,");
        }
        
        if (isMarried && isMan) {
            System.out.println("семейное положение: женат");
        } else if (isMarried && !isMan) {
            System.out.println("семейное положение: замужем");
        } else if (!isMarried && !isMan) {
            System.out.println("семейное положение: не замужем");
        } else {
            System.out.println("семейное положение: не женат");
        }
        
    }

    public void writeInfo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        File dirCont = new File("personInfo.txt");
        try {
            boolean created = dirCont.createNewFile();
            if (created) {
                System.out.printf("Файл %s создан\n", dirCont);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try (FileWriter fr = new FileWriter("personInfo.txt", false)) {
            fr.write(name + " " + lastName + ":\nвозраст: " + age + " лет,\nдата рождения: " + birthDate.format(dtf) + ",\n");
            if (isMan) {
                fr.write("пол: мужской,\n");
            } else {
                fr.write("пол: женский,\n");
            }
            
            if (isMarried && isMan) {
                fr.write("семейное положение: женат");
            } else if (isMarried && !isMan) {
                fr.write("семейное положение: замужем");
            } else if (!isMarried && !isMan) {
                fr.write("семейное положение: не замужем");
            } else {
                fr.write("семейное положение: не женат");
            }
            fr.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
