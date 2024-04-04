<#import "../layouts/layout.ftlh" as main>
<@main.layout>
        <#list vacancies as vacancy>
            <div class="card my-2" style="max-width: 850px">
                <div class="card-body">
                <a href="#" class="text-decoration-none text-dark">${vacancy.authorEmail}</a>
                    <h3 class="mt-2 mb-5"><a href="vacancies/info/${vacancy.id}" class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${vacancy.name}</a></h3>
                    <h5 class="card-title">${vacancy.description}</h5>
                    <p class="card-text">salary<br>${vacancy.salary}</p>
                   <p class="text-end">${vacancy.updateTime.getDayOfMonth()} - ${vacancy.updateTime.getMonth()}</p>
                </div>
            </div>
        </#list>
</@main.layout>