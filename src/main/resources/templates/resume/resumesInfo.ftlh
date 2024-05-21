<#import "../layout.ftlh" as main>
<@main.layout;spring>
    <div class="container py-5">
        <div class="row">
            <div class="col-md-4">
                <#if user.avatar??>
                    <img src="/api/users/image/${user.avatar}" class="avatar-img" alt="avatar" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                <#else >
                    <img src="/api/users/image/anon.jpeg" class="avatar-img" alt="avatar" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                </#if>
                <div class="user-info">
                    <h4>${user.name} ${user.surname}</h4>
                    <p class="text-muted"><strong><@spring.message 'age'/>:</strong> ${user.age}</p>
                    <p class="text-muted"><strong><@spring.message 'phone.number'/>:</strong> ${user.phoneNumber}</p>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card border-0 shadow-lg">
                    <div class="card-body">
                        <h3 class="card-title mb-4">${resume.name}</h3>
                        <p class="card-text text-muted"><strong><@spring.message 'category'/>:</strong> ${resume.category.name}</p>
                        <p class="card-text text-muted"><strong><@spring.message 'salary'/>:</strong> ${resume.salary}</p>
                        <p class="card-text text-muted"><strong><@spring.message 'email'/>:</strong> ${resume.applicant.email}</p>
                        <p class="card-text text-muted">
                            <strong><@spring.message 'active'/>:</strong> ${resume.isActive ? string('Yes','No')}</p>

                        <hr class="my-4">

                        <h5 class="card-subtitle mb-3 text-muted"><@spring.message 'resume.work.experience'/></h5>
                        <div class="work-experience">
                            <#list works as work>
                                <div class="mb-3">
                                    <h6>${work.position} - ${work.companyName}</h6>
                                    <p>${work.responsibilities}</p>
                                    <p class="text-muted"><@spring.message 'Worked'/>: ${work.years}-<@spring.message 'years'/></p>
                                </div>
                            </#list>
                        </div>

                        <hr class="my-4"> <!-- Horizontal line -->

                        <h5 class="card-subtitle mb-3 text-muted"><@spring.message 'resume.education'/></h5>
                        <div class="education">
                            <#list educations as edu>
                                <div class="mb-3">
                                    <h6>${edu.degree}, ${edu.institution}</h6>
                                    <p>${edu.program}</p>
                                    <p class="text-muted">${edu.startDate} - ${edu.endDate}</p>
                                </div>
                            </#list>
                        </div>

                        <hr class="my-4"> <!-- Horizontal line -->

                        <h5 class="card-subtitle mb-3 text-muted"><@spring.message 'resume.contacts'/></h5>
                        <div class="work-experience">
                            <#list contacts as contact>
                                <div class="mb-3">
                                    <h6>${contact.type}: ${contact.infoValue} </h6>
                                </div>
                            </#list>
                        </div>

                        <hr class="my-4">
                        <#if resume.updateDate.getYear() == 2024>
                            <p class="card-text mt-4 text-muted text-end">
                                <small><@spring.message 'profile.last.update'/>: ${resume.updateDate.getDayOfMonth()}
                                    - ${resume.updateDate.getMonth().name()}</small>
                            </p>
                        <#else>
                            <p class="card-text mt-4 text-muted text-end">
                                <small><@spring.message 'profile.last.update'/>: ${resume.updateDate.getDayOfMonth()}
                                    - ${resume.updateDate.getMonth().name()} (${resume.updateDate.getYear()})</small>
                            </p>
                        </#if>
                </div>
            </div>
        </div>
    </div>
    <link rel="stylesheet" href="/resumeInfo.css">
</@main.layout>