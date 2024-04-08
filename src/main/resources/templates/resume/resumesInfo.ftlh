<#import "../layouts/layout.ftlh" as main>
<@main.layout>

    <div class="container py-5">
        <div class="row">
            <div class="col-md-4">
                <img src="" class="img-fluid rounded-circle shadow-lg mb-3" alt="Applicant image">
                <div class="user-info">
                    <h4>${user.name} ${user.surname}</h4>
                    <p class="text-muted"><strong>Age:</strong> ${user.age}</p>
                    <p class="text-muted"><strong>Phone Number:</strong> ${user.phoneNumber}</p>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card border-0 shadow-lg">
                    <div class="card-body">
                        <h3 class="card-title mb-4">${resume.name}</h3>
                        <p class="card-text text-muted"><strong>Category:</strong> ${resume.category}</p>
                        <p class="card-text text-muted"><strong>Salary:</strong> ${resume.salary}</p>
                        <p class="card-text text-muted"><strong>Created Date:</strong> ${resume.createdDate}</p>
                        <p class="card-text text-muted"><strong>Email:</strong> ${resume.userEmail}</p>
                        <p class="card-text text-muted">
                            <strong>Active:</strong> ${resume.isActive ? string('Yes', 'No')}</p>

                        <hr class="my-4"> <!-- Horizontal line -->

                        <h5 class="card-subtitle mb-3 text-muted">Work Experience</h5>
                        <div class="work-experience">
                            <#list works as work>
                                <div class="mb-3">
                                    <h6>${work.position} - ${work.companyName}</h6>
                                    <p>${work.responsibilities}</p>
                                    <p class="text-muted">Worked: ${work.years}-ears</p>
                                </div>
                            </#list>
                        </div>

                        <hr class="my-4"> <!-- Horizontal line -->

                        <h5 class="card-subtitle mb-3 text-muted">Education</h5>
                        <div class="education">
                            <#list educations as edu>
                                <div class="mb-3">
                                    <h6>${edu.degree}, ${edu.institution}</h6>
                                    <p>${edu.program}</p>
                                    <p class="text-muted">${edu.startDate} - ${edu.endDate}</p>
                                </div>
                            </#list>
                        </div>
                        <#if resume.updateDate.getYear() == 2024>
                            <p class="card-text mt-4 text-muted text-end">
                                <small>Last
                                    updated: ${resume.updateDate.getDayOfMonth()}
                                    - ${resume.updateDate.getMonth().name()}</small>
                            </p>
                        <#else>
                            <p class="card-text mt-4 text-muted text-end">
                                <small>Last
                                    updated: ${resume.updateDate.getDayOfMonth()}
                                    - ${resume.updateDate.getMonth().name()} (${resume.updateDate.getYear()})</small>
                            </p>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <style>
        .user-info h4 {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .user-info p {
            font-size: 16px;
            margin-bottom: 5px;
        }

        .work-experience h6,
        .education h6 {
            color: #007bff;
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 8px;
        }

        .work-experience p,
        .education p {
            font-size: 16px;
            margin-bottom: 6px;
        }

        .card {
            background-color: #f8f9fa;
        }

        .card-body {
            font-size: 16px;
        }

        .card-subtitle {
            font-size: 1.1rem;
        }
    </style>

</@main.layout>