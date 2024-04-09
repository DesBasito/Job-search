<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <div class="col-md-8">
            <#list resumes as resume>
                <div class="card mb-3">
                    <div class="card-body">
                        <a href="#" class="text-decoration-none text-dark">${resume.userEmail}</a>
                        <h3 class="mt-2 mb-5"><a href="/resume/${resume.id}"
                                                 class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${resume.name}</a>
                        </h3>
                        <h2 class="card-text"> ${resume.category}</h2>
                        <p class="card-text">salary: <br> ${resume.salary}</p>
                        <p class="text-end">${resume.updateDate.getDayOfMonth()} - ${resume.updateDate.getMonth()}</p>
                    </div>
                </div>
            </#list>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Filter Resumes</h5>
                    <form action="/resume/filter" method="get">
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
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary:</label>
                            <input type="number" class="form-control" id="salary" name="salary"
                                   placeholder="salary amount">
                        </div>
                        <div class="mb-3">
                            <label for="applicant" class="form-label">Applicant:</label>
                            <input type="email" class="form-control" id="applicant" name="email"
                                   placeholder="by applicant email">
                        </div>
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>