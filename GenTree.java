import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class GenTree <T extends Person> implements Printable, Writable {
    private T person;
    private TreeMap<String, Set<T>> family;

    public GenTree(T person) {
        this.person = person;
        setFamily();
    }

    public void setFamily() {
        this.family = new TreeMap<String, Set<T>>();
        this.family.put("Pодители", (Set<T>) this.person.getParents());
        this.family.put("Муж/Женa", getSpouses());
        this.family.put("Дети", (Set<T>) this.person.getChildren());
        this.family.put("Братья и сестры", getSiblings());
        this.family.put("Дедушки и бабушки", getGrandParents());
        this.family.put("Тесть/Теща (Свекр/Свекровь)", getParentsInLaw());
        this.family.put("Зять/Невестка", getChildrenInLaw());
        this.family.put("Внуки", getGrandChildren());
    }

    public Set<T> getSiblings() {
        Set<T> siblings = new HashSet<>();
        for (T parent : (Set<T>)this.person.getParents()) {
            if (parent.getChildren().size() > 1) {
                for (T child : (Set<T>)parent.getChildren()) {
                    if (!child.equals(this.person)) {
                        siblings.add(child);
                    }
                }
            }
        }
        return siblings;
    }

    public Set<T> getGrandParents() {
        Set<T> grPrnts = new HashSet<>();
        for (T parent : (Set<T>)this.person.getParents()) {
            for (T grPrnt : (Set<T>)parent.getParents()) {
                grPrnts.add(grPrnt);
            }
        }
        return grPrnts;
    }

    public Set<T> getGrandChildren() {
        Set<T> grChldrn = new HashSet<>();
        if (this.person.getIsParent() == true) {
            for (T child : (Set<T>)this.person.getChildren()) {
                if (child.getIsParent() == true) {
                    for (T grChild : (Set<T>)child.getChildren()) {
                        grChldrn.add(grChild);
                    }
                }
            }
        }
        return grChldrn;
    }

    public Set<T> getSpouses() {
        Set<T> spouses = new HashSet<>();
        if (this.person.getSpouse() != null) {
            spouses.add((T) this.person.getSpouse());
        }
        return spouses;
    }

    public Set<T> getParentsInLaw() {
        if (this.person.getSpouse() != null) {
            return (Set<T>) this.person.getSpouse().getParents();
        } else {
            return null;
        }
    }

    public Set<T> getChildrenInLaw() {
        Set<T> childrenInLaw = new HashSet<>();
        if (this.person.getChildren() != null) {
            for (Person child : this.person.getChildren()) {
                if (child.getSpouse() != null) {
                    childrenInLaw.add((T) child.getSpouse());
                }
            }
        }
        return childrenInLaw;
    }

    public Set<T> getParents() {
        return (Set<T>) this.person.getParents();
    }

    public Set<T> getChildren() {
        return (Set<T>) this.person.getChildren();
    }

    public T getPerson() {
        return person;
    }

    public void printInfo() {
        System.out.println(this.person);
        for (Map.Entry<String, Set<T>> item : this.family.entrySet()) {
            if (item.getValue() != null && !item.getValue().isEmpty()) {
                System.out.println(item.getKey() + ":");
                int count = 1;
                for (T person : item.getValue()) {
                    System.out.println(count + ") " + person);
                    count++;
                }
            }
        }
    }

    public void writeInfo() {
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
            for (Map.Entry<String, Set<T>> item : this.family.entrySet()) {
                if (item.getValue() != null && !item.getValue().isEmpty()) {
                    fr.write(item.getKey() + ":" + "\n");
                    int count = 1;
                    for (T person : item.getValue()) {
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
