package net.baumink.bzz.m326.db.pojo;

/**
 * Client POJO
 *
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 *
 */
public class Client {

    private int id;
    private String surname;
    private String firstName;
    private String adress;
    private String postcode;
    private String town;
    private String email;
    private String password;


    /**
     * Instantiates a new Client.
     *
     * @param surname   the surname
     * @param firstName the first name
     * @param adress    the adress
     * @param postcode  the postcode
     * @param town      the town
     * @param email     the email
     * @param password  the password
     */
    public Client(String surname, String firstName, String adress, String postcode, String town, String email, String password) {
        this.surname = surname;
        this.firstName = firstName;
        this.adress = adress;
        this.postcode = postcode;
        this.town = town;
        this.email = email;
        this.password = password;
    }

    /**
     * Instantiates a new Client.
     *
     * @param id        the id
     * @param surname   the surname
     * @param firstName the first name
     * @param adress    the adress
     * @param postcode  the postcode
     * @param town      the town
     * @param email     the email
     * @param password  the password
     */
    public Client(int id, String surname, String firstName, String adress, String postcode, String town, String email, String password) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.adress = adress;
        this.postcode = postcode;
        this.town = town;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets adress.
     *
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Sets adress.
     *
     * @param adress the adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Gets postcode.
     *
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets postcode.
     *
     * @param postcode the postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets town.
     *
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets town.
     *
     * @param town the town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (surname != null ? !surname.equals(client.surname) : client.surname != null) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (adress != null ? !adress.equals(client.adress) : client.adress != null) return false;
        if (postcode != null ? !postcode.equals(client.postcode) : client.postcode != null) return false;
        if (town != null ? !town.equals(client.town) : client.town != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        return password != null ? password.equals(client.password) : client.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", adress='" + adress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", town='" + town + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
