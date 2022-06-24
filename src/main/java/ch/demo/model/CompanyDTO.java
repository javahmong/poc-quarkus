package ch.demo.model;

import ch.demo.entity.Company;

public class CompanyDTO {
    private Long id;
    private String code;
    private String name;

    public CompanyDTO() {
    }

    public CompanyDTO(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public void from(Company company){
        this.id = company.getId();
        this.code = company.getCode();
        this.name = company.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
