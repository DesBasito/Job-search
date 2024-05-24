<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col-md-9">
            <div class="card my-2" style="max-width: 900px">
                <div class="card-body">
                    <h3 class="mt-2 mb-5"><a href="#"
                                             class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${vacancy.name}</a>
                    </h3>
                    <a href="#" class="text-decoration-none text-dark"><@spring.message "company"/>: ${vacancy.authorEmail}</a>
                    <h5 class="card-title">${vacancy.description}</h5>
                    <p class="card-text"> <#if vacancy.salary??>
                            ${vacancy.salary}₽
                        <#else >
                            <@spring.message "negotiable"/>
                        </#if></p>
                    <#if applicant?? && resumes??>
                        <div class="dropend mt-4">
                            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <@spring.message "applyToCompany"/>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <#list resumes as resume>
                                    <li>
                                        <#if _csrf??>
                                            <form action="/vacancies/applyToVacancy/${vacancy.id}?resumeId=${resume.id}"
                                                  method="post">
                                                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                                       value="${(_csrf.token)!'--no-token--'}"/>
                                                <button type="submit" class="dropdown-item">
                                                    ${resume.name} (${resume.category})
                                                </button>
                                            </form>
                                        </#if>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                        <#if respId??>
                            <button type="button" class="btn btn-success mt-3"
                                    onclick="window.location.href='/chat/${respId}'">
                                <@spring.message "chat.type.message"/>
                            </button>
                        </#if>
                    </#if>
                    <p class="text-end">${vacancy.updateTime}</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <h3> ${vacancy.authorEmail} </h3>
            <div class="card" style="max-width: 450px">
                <#list employers as employer>
                    <div class="card-body border-bottom-2">
                        <a href="/vacancies/info/${employer.id}"
                           class="text-decoration-none text-dark">${employer.name}</a>
                        <p class="card-text">
                            <#if vacancy.salary??>
                            ${employer.salary}₽
                                <#else >
                            <@spring.message "negotiable"/>
                            </#if>
                            </p>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</@main.layout>
