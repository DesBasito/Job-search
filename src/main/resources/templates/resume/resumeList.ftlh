<#import "../layout.ftlh" as resumes>
<@resumes.layout; spring>
    <div class="row">
        <div class="col-md-8">
            <form id="searchResume" class="d-flex mb-3">
                <input class="form-control" type="search" placeholder="<@spring.message 'live.search'/>" aria-label="Search" name="resume" data-resume>
            </form>
            <div id="resume-pageList"></div>
            <nav aria-label="Page navigation example" id="resume-pageButtons">
            </nav>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message 'resume.sort.resumes'/></h5>
                        <form action="/resume/filter" method="get">
                            <div class="mb-3">
                                <label for="category" class="form-label"><@spring.message 'resume.sort.by.category'/>:</label>
                                <select name="category" id="category" class="form-control">
                                    <option selected></option>
                                    <#list categories as category>
                                        <option value="${category.name}">${category.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary"><@spring.message 'resume.sort'/></button>
                        </form>
                </div>
                <div class="card card-body mt-2">
                        <form action="/" method="get">
                            <h5 class="card-title"><@spring.message 'resume.filter'/></h5>
                            <div class="my-3">
                                <select class="form-select" name="filter" aria-label="Default select example">
                                    <option value="ascDate"><@spring.message 'resume.date.ascending'/></option>
                                    <option value="descDate"><@spring.message 'resume.date.descending'/></option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary"><@spring.message 'resume.filter'/></button>
                        </form>
                </div>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
        const resumeSortingBy = '${filter}'
        const salaryResume = '<@spring.message 'salary'/>'
        const resumePage = '<@spring.message 'page'/>'
    </script>
    <script src="/js/resumesPage.js"></script>
</@resumes.layout>