package ch.demo.model;

public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer version;
    private CompanyDTO companyDTO;

    public EmployeeDTO(Long id, String firstName, String lastName, Integer version, CompanyDTO companyDTO) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.version = version;
        this.companyDTO = companyDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }
}
