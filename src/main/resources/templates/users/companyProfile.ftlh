<#import "../layout.ftlh" as main>
<@main.layout;spring>

    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><@spring.message 'profile.applies'/></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table">
                        <thead>
                        <tr class="table-active">
                            <th scope="col"><@spring.message 'profile.modal.vacancy'/></th>
                            <th scope="col"><@spring.message 'profile.modal.resume'/></th>
                            <th scope="col"><@spring.message 'profile.modal.chat'/></th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list respondents as respondent>
                            <tr>
                                <th><a class="nav-link text-decoration-none text-dark"
                                       href="/vacancies/info/${respondent.vacancy.id}">${respondent.vacancy.name}</a>
                                </th>
                                <td><a class="nav-link text-decoration-none text-dark"
                                       href="/resume/${respondent.resume.id}">${respondent.resume.name}</a></td>
                                <td>
                                    <button type="button" class="btn btn-success"
                                            onclick="window.location.href='/chat/${respondent.id}'">
                                        <@spring.message 'profile.chat.to'/>
                                    </button>
                                </td>
                            </tr>
                        </#list>

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><@spring.message 'profile.modal.close'/></button>
                </div>
            </div>
        </div>
    </div>






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
                            <@spring.message 'profile.edit'/>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" href="/employer/edit"> <@spring.message 'profile.edit.profile'/></a></li>
                            <li><a class="dropdown-item" href="/employer/changePassword"> <@spring.message 'profile.change.password'/></a></li>
                            <li><a class="dropdown-item" href="/employer/uploadImage"> <@spring.message 'profile.change.image'/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md">
                <div class="card border-0 align-items-center text-align-center">
                    <a href="/vacancies/create" class="btn btn-danger res-btn text-decoration-none">+ <@spring.message 'profile.create.vacancy'/></a>
                </div>
            </div>
            <div class="col-md">
                <div class="card border-0 align-items-center">
                    <button class="btn btn-success res-btn text-decoration-none text-center" data-bs-toggle="modal"
                            data-bs-target="#exampleModal"><@spring.message 'profile.applies'/>
                    </button>
                    <#if respondents??>
                        <span class="badge bg-warning rounded-pill position-absolute top-0 end-0 mt-5 me-2">${respondents?size}</span>
                    </#if>
                </div>
            </div>
        </div>


        <#if vacancies?has_content>
            <h6 class="mt-5"><@spring.message 'profile.vacancy'/> :</h6>
            <div class="row row-cols-1 row-cols-md-3 g-4" id="employer">
                <#list vacancies.content as vacancy>
                    <div class="col mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><a href="/vacancies/info/${vacancy.id}"
                                                          class="text-decoration-none text-dark">${vacancy.name}</a>
                                </h5>
                                <p class="card-text"><@spring.message 'profile.last.update'/>: <span id="${vacancy.id}_last-update">${vacancy.getUpdateTime()}</span></p>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between gx-2 my-2">
                            <div>
                                <button onclick="updateVacancy(${vacancy.id})" class="btn btn-primary" style="width: 150px"><@spring.message 'profile.update'/>
                                </button>

                            </div>

                            <div>
                                <button type="button" class="btn btn-success " style="width: 150px"
                                        onclick="window.location.href='/vacancies/edit?id=${vacancy.id}'">
                                    <@spring.message 'profile.edit'/>
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
                    <li class="page-item"><a class="page-link" href="/profile?page=${page - 1}">&laquo;</a></li>
                    <li class="page-item page-link">${page}</li>
                    <li class="page-item"><a class="page-link" href="/profile?page=${page + 1}">&raquo;</a></li>
                </ul>
            </nav>
        </div>
    </div>

    <script>
        const csrfToken = document.querySelector("meta[name='_csrf_token']").content;
        const csrfHeader = document.querySelector("meta[name='_csrf_header']").content;
        const headers = {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        };

        async function updateVacancy(id) {
            const lastUpdateElement = document.getElementById(id + '_last-update');
            try {
                const response = await fetch(`/api/vacancies/update/` + id, {
                    method: 'PUT',
                    headers: headers
                });

                if (response.ok) {
                    const vacancy = await response.json();
                    lastUpdateElement.textContent = vacancy.updateTime;
                } else {
                    console.error('Failed to update vacancy');
                }
            } catch (error) {
                console.error('Error updating vacancy:', error);
            }
        }
    </script>

</@main.layout>