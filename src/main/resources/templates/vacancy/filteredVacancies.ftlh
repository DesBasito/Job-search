<#import "../layout.ftlh" as main>
<@main.layout;spring>
    <div class="row">
        <div class="col-md-8">
            <#list vacancies.content as vacancy>
                <div class="card mb-3">
                    <div class="card-body">
                        <a href="#" class="text-decoration-none text-dark">${vacancy.authorEmail}</a>
                        <h3 class="mt-2 mb-5"><a href="/vacancies/info/${vacancy.id}"
                                                 class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${vacancy.name}</a>
                        </h3>
                        <h5 class="card-title">${vacancy.description}</h5>
                        <p class="card-text"><@spring.message 'salary'/><br>${vacancy.salary}</p>
                        <p class="text-end">${vacancy.updateTime}</p>
                    </div>
                </div>
            </#list>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <#if page = 0>
                        <a class="page-link" href="/vacancies/filter?category=${category}&page=${page}" aria-label="Previous"/>
                        <#else >
                        <a class="page-link" href="/vacancies/filter?category=${category}&page=${page-1}" aria-label="Previous">
                        </#if>
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="page-item page-link">${page}</li>
                    <li class="page-item">
                        <a class="page-link" href="/vacancies/filter?category=${category}&page=${page+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message 'main.sort.vacancies'/></h5>
                    <form action="/vacancies/filter" method="get">
                        <div class="mb-3">
                            <label for="category" class="form-label"><@spring.message 'category'/>:</label>
                            <label for="category" class="form-label"><@spring.message 'main.category'/>:</label>
                            <select name="category" id="category" class="form-control">
                                <option selected>${category}</option>
                                <#list categories as category>
                                    <option value="${category.name}">${category.name}</option>
                                </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary"><@spring.message 'main.sort'/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>