<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="row">
        <#list employers as employer>
            <div class="card text-center">
                <h5 class="card-header">${employer.name}</h5>
                <div class="card-body">
                    <h5 class="card-title">${employers.size}</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
                <div class="card-footer text-muted">
                    2 days ago
                </div>
            </div>
        </#list>
    </div>

</@main.layout>