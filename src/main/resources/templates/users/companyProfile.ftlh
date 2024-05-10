<#import "../layout.ftlh" as main>
<@main.layout>
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
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item" href="/employer/edit">Edit profile</a></li>
                                <li><a class="dropdown-item" href="/employer/changePassword">Change password</a></li>
                                <li><a class="dropdown-item" href="/employer/uploadImage">Set a new image</a></li>
                            </ul>
                    </div>
                </div>
            </div>
            <div class="col-md">
                <div class="card border-0 align-items-center text-align-center">
                    <a href="/vacancies/create" class="btn btn-danger res-btn text-decoration-none">+ Создать
                        ваканчсию</a>
                </div>
            </div>
            <div class="col-md">
                <div class="card border-0 align-items-center">
                    <a href="#" class="btn btn-success res-btn text-decoration-none text-center">Отклики </a>
                    <#if respondents??>
                        <span class="badge bg-warning rounded-pill position-absolute top-0 end-0 mt-5 me-2">${respondents}</span>
                    </#if>
                </div>
            </div>
        </div>


            <#if vacancies?has_content>
                <h6 class="mt-5">Вакансии :</h6>
                <div class="row row-cols-1 row-cols-md-3 g-4" id="employer">
                    <#list vacancies.content as vacancy>
                        <div class="col mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title"><a href="/vacancies/info/${vacancy.id}"
                                                              class="text-decoration-none text-dark">${vacancy.name}</a>
                                    </h5>
                                    <p class="card-text">Last update: ${vacancy.getUpdateTime()}</p>
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between gx-2 my-2">
                                <div>
                                    <main>
                                        <form action="/vacancies/updateVacancy?id=${vacancy.id}" method="post">
                                            <button type="submit" class="btn btn-primary" style="width: 150px">Update
                                            </button>
                                        </form>
                                    </main>
                                </div>

                                <div>
                                    <button type="button" class="btn btn-success " style="width: 150px"
                                            onclick="window.location.href='/vacancies/edit?id=${vacancy.id}'">
                                        Edit
                                    </button>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            </#if>


        <div class="mb-2" id="pagination-profile">
            <nav class="mb-2" aria-label="Page navigation example" id="applicant-pageButtons">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="/profile?page=${page - 1}">Previous</a></li>
                    <li class="page-item page-link">${page}</li>
                    <li class="page-item"><a class="page-link" href="/profile?page=${page + 1}">Next</a></li>
                </ul>
            </nav>
        </div>
    </div>

</@main.layout>