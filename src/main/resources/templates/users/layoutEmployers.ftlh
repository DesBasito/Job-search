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
                    <p class="card-text">age: ${user.age}</p>
                    <button type="button" class="btn btn-success" style="width: 200px">Edit</button>
                </div>
            </div>
            <div class="col-md">
                <div class="card border-0 align-items-center text-align-center">
                    <a href="#" class="btn btn-danger res-btn text-decoration-none" >+ Создать вакансию</a>
                </div>
            </div>
        </div>
        <h6>Созданные вакансии :</h6>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <#list vacancies as vacancy>
                <div class="col mb-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">
                                <a href="/vacancies/info/${vacancy.id}" class="text-decoration-none text-dark">${vacancy.name}</a>
                            </h5>
                            <p class="card-text">${vacancy.updateTime}</p>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between gx-2 my-2">
                        <button type="button" class="btn btn-primary col-6" onclick="handleUpdate(${vacancy.id})">Update</button>
                        <button type="button" class="btn btn-success col-6" onclick="handleEdit(${vacancy.id})">Edit</button>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@main.layoutUsers>