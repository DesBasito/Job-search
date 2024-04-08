<#import "../layouts/layoutUsers.ftlh" as main>
<@main.layoutUsers>
<div class="container my-5 mx-auto" style="max-width: 1100px; background: white; border-radius: 15px">
    <div class="row g-2 my-3 py-3">
        <div class="col-md-3">
            <div class="card border-0">
                <img src="../../../../../data/images/${user.avatar}" alt="User image">
            </div>
        </div>
        <div class="col-md-6">
            <div class="card border-0">
                <h5 class="card-title">${user.name} ${user.surname}</h5>
                <p class="card-text">${user.age}</p>
                <div class="dropend">
                    <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        Edit
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="/applicant/edit/${user.email}">Edit profile</a></li>
                        <li><a class="dropdown-item" href="/applicant/changePassword/${user.email}">Change password</a></li>
                        <li><a class="dropdown-item" href="/applicant/uploadImage/${user.email}">Set a new image</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md">
            <div class="card border-0 align-items-center text-align-center">
                <a href="#" class="btn btn-danger res-btn text-decoration-none" >+ Создать резюме</a>
            </div>
        </div>
        <div class="col-md">
            <div class="card border-0 align-items-center">
                <a href="#" class="btn btn-success res-btn text-decoration-none">Отклики </a>
            </div>
        </div>
    </div>
    <#if resumes?has_content>
    <h6 class="mt-5">резюме :</h6>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list resumes as resume>
            <div class="col mb-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><a href="/resume/${resume.id}" class="text-decoration-none text-dark">${resume.name}</a></h5>
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