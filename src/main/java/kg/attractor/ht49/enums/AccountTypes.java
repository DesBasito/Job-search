package kg.attractor.ht49.enums;

public enum AccountTypes {
    EMPLOYEE("employee"),EMPLOYER("employer");

    private  final String accType;

    AccountTypes(String accType) {
        this.accType = accType;
    }

    @Override
    public String toString() {
        return accType;
    }
}
