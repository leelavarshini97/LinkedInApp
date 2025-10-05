package org.linkedin.application.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Job extends Post {
    String roleName;
    String companyName;
    int yearsOfExperiance;
    String responsibilities;

    public String getDetails(){
        return this.getCompanyName() + this.getRoleName() +
                this.getResponsibilities() + this.getYearsOfExperiance();
    }



}
