<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col-md-9">
            <div class="card my-2" style="max-width: 900px">
                <div class="card-body">
                    <h3 class="mt-2 mb-5"><a href="#"
                                             class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${vacancy.name}</a>
                    </h3>
                    <a href="#" class="text-decoration-none text-dark">company: ${vacancy.authorEmail}</a>
                    <h5 class="card-title">${vacancy.description}</h5>
                    <p class="card-text">${vacancy.salary}</p>
                    <#if applicant?? && resumes??>
                        <div class="dropend mt-4">
                            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                Apply to this vacancy
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <#list resumes as resume>
                                    <li>
                                        <form action="/vacancies/applyToVacancy/${vacancy.id}?resumeId=${resume.id}"
                                              method="post">
                                            <button type="submit" class="dropdown-item">
                                                ${resume.name} (${resume.category})
                                            </button>
                                        </form>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                        <#if respId??>
                            <button type="button" class="btn btn-success mt-3"
                                    onclick="window.location.href='/chat/${respId}?email=${vacancy.authorEmail}'">
                                write a message to company
                            </button>
                        </#if>
                    </#if>
                    <p class="text-end">${vacancy.updateTime.getDayOfMonth()} - ${vacancy.updateTime.getMonth()}</p>
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
                        <p class="card-text">${employer.salary}₽</p>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</@main.layout>
