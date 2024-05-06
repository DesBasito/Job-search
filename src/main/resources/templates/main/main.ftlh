<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <div class="col-md-8">
            <#list vacancies.content as vacancy>
                <div class="card mb-3">
                    <div class="card-body">
                        <a href="#" class="text-decoration-none text-dark">${vacancy.authorEmail}</a>
                        <h3 class="mt-2 mb-5"><a href="vacancies/info/${vacancy.id}"
                                                 class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${vacancy.name}</a>
                        </h3>
                        <h5 class="card-title">${vacancy.description}</h5>
                        <p class="card-text">salary<br>${vacancy.salary}</p>
                        <p class="text-end">${vacancy.updateTime.getDayOfMonth()} - ${vacancy.updateTime.getMonth()}</p>
                    </div>
                </div>
            </#list>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <#if page - 1 == 0>
                        <#assign page=2>
                    </#if>
                    <li class="page-item"><a class="page-link" href="/?page=${page - 1}">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="/?page=1">1</a></li>
                    <li class="page-item"><a class="page-link" href="/?page=2">2</a></li>
                    <li class="page-item"><a class="page-link" href="/?page=3">3</a></li>
                    <li class="page-item"><a class="page-link" href="/?page=${page + 1}">Next</a></li>
                </ul>
            </nav>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Filter Vacancies</h5>
                    <form action="/vacancies/filter" method="get">
                        <div class="mb-3">
                            <label for="category" class="form-label">Category:</label>
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="category"
                                   placeholder="name of category">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value=${category.name}>
                                    </#list>
                            </datalist>
                        </div>
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>