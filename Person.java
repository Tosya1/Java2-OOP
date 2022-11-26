import java.util.ArrayList;
import java.util.Arrays;

public class Person implements Model {
    private String name;
    private String password;
    private String email;
    private String birthdate;

    public Person() {
        new ArrayList<String>(Arrays.asList(name, password, email, birthdate));
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
        
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
        
    }

    @Override
    public void setBirthDate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getBirthDate() {
        return this.birthdate;
    }

}
