package ch.demo.entity;

import ch.demo.model.CompanyDTO;

public class Company {
    private Long id;
    private String code;
    private String name;

    public Company() {
    }

    public Company(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public void from(CompanyDTO companyDTO){
        this.id = companyDTO.getId();
        this.code = companyDTO.getCode();
        this.name = companyDTO.getName();
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
