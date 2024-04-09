<#import "layoutUsers.ftlh" as main>
<@main.layoutUsers>
    <div class="container my-5 mx-auto" style="max-width: 1100px; background: white; border-radius: 15px">
        <div class="row g-2 my-3 py-3">
            <div class="col-md-3">
                <div class="card border-0">
                    <img src="/images/${user.avatar}" class="avatar-img" alt="User image" style="border-radius: 30%;
                width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                </div>
            </div>
            <div class="col-md-6">
                <div class="card border-0 ps-3">
                    <h5 class="card-title">${user.name} ${user.surname}</h5>
                    <p class="card-text">${user.age}</p>
                    <div class="dropend">
                        <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton1"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            Edit
                        </button>
                        <#if user.accType = 'employer'>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" href="/employer/edit">Edit profile</a></li>
                            <li><a class="dropdown-item" href="/employer/changePassword">Change password</a></li>
                            <li><a class="dropdown-item" href="/employer/uploadImage">Set a new image</a></li>
                        </ul>
                            <#else >
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><a class="dropdown-item" href="/application/edit">Edit profile</a></li>
                                    <li><a class="dropdown-item" href="/applicant/changePassword">Change password</a></li>
                                    <li><a class="dropdown-item" href="/applicant/uploadImage">Set a new image</a></li>
                                </ul>
                        </#if>
                    </div>
                </div>
            </div>
            <#if user.accType = 'employer'>
            <div class="col-md">
                <div class="card border-0 align-items-center text-align-center">
                    <a href="/vacancies/create" class="btn btn-danger res-btn text-decoration-none">+ Создать
                        ваканчсию</a>
                </div>
            </div>
            <div class="col-md">
                <div class="card border-0 align-items-center">
                    <a href="#" class="btn btn-success res-btn text-decoration-none">Отклики </a>
                </div>
            </div>
        </div>
        <#else>
            <div class="col-md">
                <div class="card border-0 align-items-center text-align-center">
                    <a href="/resume/create" class="btn btn-danger res-btn text-decoration-none">+ Создать
                        резюме</a>
                </div>
            </div>
        </#if>

        <#if user.accType=='employer'>
            <#if vacancies?has_content>
                <h6 class="mt-5">Вакансии :</h6>
                <div class="row row-cols-1 row-cols-md-3 g-4">
                    <#list vacancies as vacancy>
                        <div class="col mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title"><a href="/vacancies/info/${vacancy.id}"
                                                              class="text-decoration-none text-dark">${vacancy.name}</a>
                                    </h5>
                                    <p class="card-text">${vacancy.updateTime}</p>
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between gx-2 my-2">
                                <button type="button" class="btn btn-primary col-6" onclick="window.location.href='/vacancies/update?id=${vacancy.id}'">Update</button>
                                <button type="button" class="btn btn-success col-6" onclick="window.location.href='/vacancies/update?id=${vacancy.id}'">Edit</button>
                            </div>
                        </div>
                    </#list>
                </div>
            </#if>
        <#else>

        </#if> <#if resumes?has_content>
            <h6 class="mt-5">резюме :</h6>
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <#list resumes as resume>
                    <div class="col mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><a href="/resume/${resume.id}"
                                                          class="text-decoration-none text-dark">${resume.name}</a></h5>
                                <p class="card-text">${resume.updateDate}</p>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between gx-2 my-2">
                            <button type="button" class="btn btn-primary col-6">Update</button>
                            <button type="button" class="btn btn-success col-6">Edit</button>
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
    </div>
</@main.layoutUsers>