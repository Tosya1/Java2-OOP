import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GenTree {
    private Person person;
    private TreeMap<String, Set<Person>> family;

    public GenTree(Person person) {
        this.person = person;
        setFamily();
    }

    public void setFamily() {
        this.family = new TreeMap<String, Set<Person>>();
        this.family.put("Pодители", this.person.getParents());
        this.family.put("Муж/Женa", getSpouses());
        this.family.put("Дети", this.person.getChildren());
        this.family.put("Братья и сестры", getSiblings());
        this.family.put("Дедушки и бабушки", getGrandParents());
        this.family.put("Тесть/Теща (Свекр/Свекровь)", getParentsInLaw());
        this.family.put("Зять/Невестка", getChildrenInLaw());
        this.family.put("Внуки", getGrandChildren());
    }

    public Set<Person> getSiblings() {
        Set<Person> siblings = new HashSet<>();
        for (Person parent : this.person.getParents()) {
            if (parent.getChildren().size() > 1) {
                for (Person child : parent.getChildren()) {
                    if (!child.equals(this.person)) {
                        siblings.add(child);
                    }
                }
            }
        }
        return siblings;
    }

    public Set<Person> getGrandParents() {
        Set<Person> grPrnts = new HashSet<>();
        for (Person parent : this.person.getParents()) {
            for (Person grPrnt : parent.getParents()) {
                grPrnts.add(grPrnt);
            }
        }
        return grPrnts;
    }

    public Set<Person> getGrandChildren() {
        Set<Person> grChldrn = new HashSet<>();
        if (this.person.getIsParent() == true) {
            for (Person child : this.person.getChildren()) {
                if (child.getIsParent() == true) {
                    for (Person grChild : child.getChildren()) {
                        grChldrn.add(grChild);
                    }
                }
            }
        }
        return grChldrn;
    }

    public Set<Person> getSpouses() {
        Set<Person> spouses = new HashSet<>();
        if (this.person.getSpouse() != null) {
            spouses.add(this.person.getSpouse());
        }
        return spouses;
    }

    public Set<Person> getParentsInLaw() {
        if (this.person.getSpouse() != null) {
            return this.person.getSpouse().getParents();
        } else {
            return null;
        }
    }

    public Set<Person> getChildrenInLaw() {
        Set<Person> childrenInLaw = new HashSet<>();
        if (this.person.getChildren() != null) {
            for (Person child : this.person.getChildren()) {
                if (child.getSpouse() != null) {
                    childrenInLaw.add(child.getSpouse());
                }
            }
        }
        return childrenInLaw;
    }

    public Set<Person> getParents() {
        return this.person.getParents();
    }

    public Set<Person> getChildren() {
        return this.person.getChildren();
    }

    public Person getPerson() {
        return person;
    }

    public void printFamily() {
        System.out.println(this.person);
        for (Map.Entry<String, Set<Person>> item : this.family.entrySet()) {
            if (item.getValue() != null && !item.getValue().isEmpty()) {
                System.out.println(item.getKey() + ":");
                int count = 1;
                for (Person person : item.getValue()) {
                    System.out.println(count + ") " + person);
                    count++;
                }
            }
        }
    }

    public void writeToFile() {
        File dirCont = new File("familyInfo.txt");
        try {
            boolean created = dirCont.createNewFile();
            if (created) {
                System.out.printf("Файл %s создан", dirCont);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try (FileWriter fr = new FileWriter("familyInfo.txt", false)) {
            fr.write(this.person.toString() + "\n");
            for (Map.Entry<String, Set<Person>> item : this.family.entrySet()) {
                if (item.getValue() != null && !item.getValue().isEmpty()) {
                    fr.write(item.getKey() + ":" + "\n");
                    int count = 1;
                    for (Person person : item.getValue()) {
                        fr.write(count + ") " + person + "\n");
                        count++;
                    }
                }
            }
            fr.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
