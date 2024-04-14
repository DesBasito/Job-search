<#import "layoutUsers.ftlh" as main>
<@main.layoutUsers>
    <div class="container my-5 mx-auto" style="max-width: 1100px; background: white; border-radius: 15px">
        <div class="row g-2 my-3 py-3">
            <div class="col-md-3">
                <div class="card border-0">
                    <#if user.avatar??>
                        <img src="api/users/image/${user.avatar}" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                    <#else >
                        <img src="api/users/image/anon.jpeg" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                    </#if>
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
                                <li><a class="dropdown-item" href="/applicant/edit">Edit profile</a></li>
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
                    <a href="#" class="btn btn-success res-btn text-decoration-none text-center">Отклики </a>
                    <span class="badge bg-warning rounded-pill position-absolute top-0 end-0 mt-5 me-2">14</span>
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
                                    <p class="card-text">Last update: ${vacancy.getUpdateTime().getDayOfMonth()}
                                        -${vacancy.updateTime.getMonth()} (${vacancy.getUpdateTime().getYear()})</p>
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between gx-2 my-2">
                                <div>
                                    <main>
                                        <form action="/vacancies/changeActivation?id=${vacancy.id}" method="post">
                                            <#if vacancy.isActive == true>
                                                <button type="submit" class="btn btn-danger" style="width: 150px">Deactivate
                                                </button>
                                            <#else>
                                                <button type="submit" class="btn btn-primary" style="width: 150px">
                                                    Activate
                                                </button>
                                            </#if>
                                        </form>
                                    </main>
                                </div>

                                <div>
                                    <button type="button" class="btn btn-success " style="width: 150px"
                                            onclick="window.location.href='/vacancies/update?id=${vacancy.id}'">
                                        Edit
                                    </button>
                                </div>
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
                                <hr>
                                <p class="card-text">Last update: ${resume.updateDate.getDayOfMonth()}
                                    -${resume.updateDate.getMonth()} (${resume.updateDate.getYear()})</p>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between gx-2 my-2">
                            <div>
                                <main>
                                    <form action="/resume/changeState?id=${resume.id}" method="post">
                                        <#if resume.isActive == true>
                                        <button type="submit" class="btn btn-danger" style="width: 150px">Deactivate
                                        </button>
                                    <#else>
                                        <button type="submit" class="btn btn-primary" style="width: 150px">
                                            Activate
                                        </button>
                                    </#if>
                                    </form>
                                </main>
                            </div>

                            <div>
                                <button type="button" class="btn btn-success " style="width: 150px"
                                        onclick="window.location.href='/resume/update?id=${resume.id}'">
                                    Edit
                                </button>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
    </div>

<#--    <script>var dropdownElementList = [].slice.call(document.querySelectorAll('.dropdown-toggle'))-->
<#--        var dropdownList = dropdownElementList.map(function (dropdownToggleEl) {-->
<#--            return new bootstrap.Dropdown(dropdownToggleEl)-->
<#--        })</script>-->
</@main.layoutUsers>