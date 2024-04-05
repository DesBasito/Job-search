<#import "../layouts/layoutUsers.ftlh" as main>
<@main.layoutUsers>
<div class="container my-5 mx-auto" style="max-width: 1100px; background: white; border-radius: 15px">
    <div class="row g-2 my-3">
        <div class="col-md">
            <div class="card border-0">
                <img src="" alt="User image">
            </div>
        </div>
        <div class="col-md-6">
            <div class="card border-0">
                <h5 class="card-title">${user.name} ${user.surname}</h5>
                <p class="card-text">${user.age}</p>
                <p>${user.email}</p>
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
    <h6>резюме :</h6>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list resumes as resume>
            <div class="col mb-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><a class="text-decoration-none text-dark">${resume.name}</a></h5>
                        <p class="card-text">${resume.updateDate}</p>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    </#if>
</div>
</@main.layoutUsers>